package game;

import game.clipboard.items.bread.BlandBread;
import game.clipboard.items.bread.BrownBread;
import game.clipboard.items.weapon.Gun;
import game.entities.rooms.Floor;
import game.entities.rooms.ProductRoom;
import game.entities.rooms.Room;
import org.newdawn.slick.Graphics;
import game.entities.rooms.ResearchFloor;
import game.clipboard.menus.HiringMenu;

import java.util.ArrayList;

import static game.World.getWar;

public class RoomManager {
    private static ArrayList<ProductRoom[]> rooms;
    private static ArrayList<ProductRoom[]> floors;
    private static ResearchFloor resRoom;
    private static int curFloor;
    private static int curRoom;
    private static int curBasement;
    private static final int ROOMS_IN_FLOOR = 2;
    public static final int FLOOR_UNLOCK_ROOMS = 5;
    public RoomManager(ResourceManager resourceManager)
    {

        ProductRoom.addProduct(new BlandBread());
        //ProductRoom.addProduct(new Gun());
        rooms = new ArrayList<>();
        floors = new ArrayList<>();
        resRoom = new ResearchFloor();
        rooms.add(new ProductRoom[ROOMS_IN_FLOOR]);
        curRoom =0;
        curFloor = 1;
        curBasement = -1;
        addRoom();
        ResourceManager.setRooms(rooms);
    }

    //Doesn't include ResearchRoom
    public static int getTotalRooms(){
        int ret = floors.size() + (rooms.size()-1)*2;
        for (ProductRoom r: rooms.getLast())
        {
            if (r!= null)
            {
                ret++;
            }
        }
        System.out.println(ret);
        return ret;


    }
    public void render(Graphics g)
    {
        for(ProductRoom[] r : rooms)
        {
            for(int i = 0; i < ROOMS_IN_FLOOR; i++)
            {
                if(r[i] != null)
                {
                    r[i].render(g);
                }
            }
        }
        resRoom.render(g);
        for (ProductRoom[] f : floors)
        {
            f[0].render(g);
        }
        g.setColor(org.newdawn.slick.Color.black);

    }
    public void update()
    {
        for (ProductRoom[] r :rooms)
        {
            for (int i = 0; i<ROOMS_IN_FLOOR; i++)
            {
                if (r[i] != null)
                {
                    r[i].update();
                }
            }
        }
        resRoom.update();
        for (ProductRoom[] f :floors)
        {
            f[0].update();
        }

        ResourceManager.update(rooms, floors);

        if (curFloor < FLOOR_UNLOCK_ROOMS)
        {


        }
        else if ( !getWar())
        {
            //g.drawString("PRESS [2] to add FLOOR!!!" + ResourceManager.getFloorPrice(curFloor), 10,580);
            Clipboard.getHireMenu().addFloorButton();
        }
        else if (getWar()) {

//            g.drawString("PRESS [2] to add FLOOR!!!"+ ResourceManager.getFloorPrice(curFloor), 10,580);
//            g.drawString("PRESS [3] to add BASEMENT!!!"+ ResourceManager.getFloorPrice(curBasement), 10,600);
            Clipboard.getHireMenu().addBasement();

        }
    }
    public static void keyPressed(int key, char c)
    {
        if (c == '1' && ResourceManager.getFunds()>ResourceManager.getRoomPrice(curFloor, curRoom)) {
            addRoom();
            ResourceManager.withdraw(ResourceManager.getRoomPrice(curFloor, curRoom));
        }
        if (c == '2'&& curFloor>=FLOOR_UNLOCK_ROOMS && ResourceManager.getFunds()>=ResourceManager.getFloorPrice(curFloor))
        {
            floors.add(new Floor[]{new Floor (curFloor)});
            curFloor ++;
            ResourceManager.withdraw(ResourceManager.getFloorPrice(curFloor));
        }
        if (c == '3' && World.getWar()
                && ResourceManager.getFunds()>=ResourceManager.getFloorPrice(curBasement))
        {
            floors.add(0,new Floor[]{new Floor (curBasement)});
            curBasement --;
            ResourceManager.withdraw(ResourceManager.getFloorPrice(curBasement));
        }
    }

    public String getRoomInfo(int x, int y)
    {
        String ret = "";
        for (Room[] r: rooms)
        {
            for (Room room: r)
            {
                if (room != null && room.mouseOver(x,y))
                {
                    ret = room.getInfo(x, y);
                }
            }
        }
        for (Room[] r: floors)
        {
            for (Room room: r)
            {
                if (room != null && room.mouseOver(x,y))
                {
                    ret = room.getInfo(x, y);
                }
            }
        }
        if (resRoom.mouseOver(x,y))
        {
            ret = resRoom.getInfo(x,y);
        }
        return ret;
    }

    public static void addFloor() {
        if (curFloor>=FLOOR_UNLOCK_ROOMS && ResourceManager.getFunds()>=ResourceManager.getFloorPrice(curFloor))
        {
            floors.add(new Floor[]{new Floor (curFloor)});
            ResourceManager.withdraw(ResourceManager.getFloorPrice(curFloor));
            curFloor ++;
        }
    }

    public static void addBasement() {
        if (curFloor>=FLOOR_UNLOCK_ROOMS && ResourceManager.getFunds()>=ResourceManager.getFloorPrice(curFloor))
        {
            floors.add(new Floor[]{new Floor (curBasement)});
            ResourceManager.withdraw(ResourceManager.getFloorPrice(curBasement));
            curBasement --;
        }
    }

    public static int getCurBasement() { return curBasement;}
    public static int getCurRoomPrice()
    {
        return ResourceManager.getRoomPrice(curFloor, curRoom);
    }
    public static int getCurFloorPrice()
    {
        return ResourceManager.getFloorPrice(curFloor);
    }
    public static int getCurBasementPrice()
    {
        return ResourceManager.getFloorPrice(curFloor);
    }
    public void mousePressed(int button, int mX, int mY)
    {
        for (ProductRoom[] r :rooms)
        {
            for (int i = 0; i<r.length; i++)
            {
                if (r[i] != null && r[i].isOver(mX, mY))
                {
                    r[i].mousePressed(button, mX, mY);
                    ResourceManager.updateRoom(rooms.indexOf(r),i, r[i].getNumDucks());
                }
            }
        }
        if (resRoom.isOver(mX, mY))
        {
            resRoom.mousePressed(button, mX, mY);
        }
        for (ProductRoom[] f :floors)
        {
            if (f[0].isOver(mX,mY))
            {
                f[0].mousePressed(button, mX, mY);
            }

        }
    }
    public static void addRoom()
    {
        if(curFloor < FLOOR_UNLOCK_ROOMS)
        {
            rooms.getLast()[curRoom] = new ProductRoom(curFloor, curRoom);
            if(spaceOnFloor()) {
                curRoom++;
            }else
            {
                curFloor++;
                curRoom = 0;

                ProductRoom[] newFloor = new ProductRoom[ROOMS_IN_FLOOR];
                rooms.add(newFloor);
                ResourceManager.addFloor(curFloor, curRoom);
            }
        }
    }
    //checks if there are 2 to a floor.

    public static boolean spaceOnFloor()
    {
        int count = 0;
        for(int i = 0; i < rooms.getLast().length; i++)
        {
            if(rooms.getLast()[i] != null)
            {
                count++;
            }
        }
        return (count < ROOMS_IN_FLOOR);
    }
    public int getCurFloor()
    {
        return curFloor;
    }
}
