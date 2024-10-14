package game;

import game.clipboard.items.bread.BlandBread;
import game.clipboard.items.bread.BrownBread;
import game.clipboard.items.weapon.Gun;
import game.entities.rooms.*;
import org.newdawn.slick.Graphics;
import game.clipboard.menus.HiringMenu;

import java.util.ArrayList;

import static game.World.getWar;

public class RoomManager {
    private static ArrayList<ProductRoom[]> rooms;
    private static ArrayList<ProductRoom[]> floors;
    private static ResearchFloor resRoom;
    private static AdRoom adRoom;
    private static int curFloor;
    private static int curRoom;
    private static int curBasement;
    private static final int ROOMS_IN_FLOOR = 2;
    public static final int FLOOR_UNLOCK_ROOMS = 5;
    public static int roomAvailability;
    public RoomManager(ResourceManager resourceManager)
    {

        ProductRoom.addProduct(new BlandBread());
        rooms = new ArrayList<>();
        floors = new ArrayList<>();
        resRoom = new ResearchFloor();
        adRoom = new AdRoom();

        rooms.add(new ProductRoom[ROOMS_IN_FLOOR]);
        curRoom =0;
        curFloor = 2;
        curBasement = -1;
        addRoom();
        ResourceManager.setRooms(rooms);
    }

    public void render(Graphics g)
    {
        //draws all rooms
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
        adRoom.render(g);
        for (ProductRoom[] f : floors)
        {
            f[0].render(g);
        }

    }
    public void update()
    {
        //update all rooms
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
        adRoom.update();
        for (ProductRoom[] f :floors)
        {
            f[0].update();
        }

        //update all of the funds
        ResourceManager.update(rooms, floors);

        //tells clipboard whether to allow users toa dd floors/basement
        if ( !getWar() && curFloor> FLOOR_UNLOCK_ROOMS)
        {
            Clipboard.getHireMenu().addFloorButton();
        }
        else if (curFloor > FLOOR_UNLOCK_ROOMS){
            Clipboard.getHireMenu().addBasement();
        }
    }
    public static int getRoomAvailability()
    {
        roomAvailability = 0;
        for(Room[] r : rooms)
        {
            for(Room room : r)
            {
                if(room != null) roomAvailability += room.getRoomSize();
            }
        }
        for(Room[] f : floors)
        {
            for(Room floor : f)
            {
                if(floor != null) roomAvailability += floor.getRoomSize();
            }
        }
        if(resRoom != null) roomAvailability += resRoom.getRoomSize();
        if(adRoom != null) roomAvailability += adRoom.getRoomSize();

        return roomAvailability;
    }

//ACCESSOR
    //Counts total number of rooms  (used to calculate property taxes
    public static int getTotalRooms(){
        int ret = floors.size() + (rooms.size()-1)*2 +1;
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

    //used to display information about the room
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
        if (adRoom.mouseOver(x,y))
        {
            ret = adRoom.getInfo(x,y);
        }
        return ret;
    }

    //returns what "floor" was the last placed
    public static int getCurBasement() { return curBasement;}
    public int getCurFloor()
    {
        return curFloor;
    }


    //calls the resource manager to get the price
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
        return ResourceManager.getFloorPrice(-curBasement);
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


//MUTATOR

    //tells each room its clicked.
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
        if (adRoom.isOver(mX, mY))
        {
            adRoom.mousePressed(button, mX, mY);
        }
        for (ProductRoom[] f :floors)
        {
            if (f[0].isOver(mX,mY))
            {
                f[0].mousePressed(button, mX, mY);
            }

        }
    }

    //add room for hotkeys!
    public static void keyPressed(int key, char c)
    {
        if (c == '1' && ResourceManager.getFunds()>ResourceManager.getRoomPrice(curFloor, curRoom)) {
            addRoom();
            ResourceManager.withdraw(ResourceManager.getRoomPrice(curFloor, curRoom));
        }
        if (c == '2'&& curFloor>=FLOOR_UNLOCK_ROOMS && ResourceManager.getFunds()>=ResourceManager.getFloorPrice(curFloor))
        {
            addFloor();
//            floors.add(new Floor[]{new Floor (curFloor)});
//            curFloor ++;
//            ResourceManager.withdraw(ResourceManager.getFloorPrice(curFloor));
        }
        if (c == '3' && World.getWar()
                && ResourceManager.getFunds()>=ResourceManager.getFloorPrice(curBasement))
        {
            addBasement();
//            floors.add(0,new Floor[]{new Floor (curBasement)});
//            curBasement --;
//            ResourceManager.withdraw(ResourceManager.getFloorPrice(curBasement));
        }
    }


    //adds respective type of room.
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
            floors.add(0, new Floor[]{new Floor (curBasement)});
            ResourceManager.withdraw(ResourceManager.getFloorPrice(curBasement));
            curBasement --;
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

}
