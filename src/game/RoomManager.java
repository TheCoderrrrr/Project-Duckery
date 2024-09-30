package game;

import game.clipboard.items.bread.BrownBread;
import game.clipboard.items.weapon.Gun;
import game.entities.rooms.Floor;
import game.entities.rooms.Room;
import org.newdawn.slick.Graphics;
import game.entities.rooms.ResearchFloor;

import java.util.ArrayList;

public class RoomManager {
    private static ArrayList<Room[]> rooms;
    private static ArrayList<Room[]> floors;
    private static Room resRoom;
    private static int curFloor;
    private static int curRoom;
    private static int curBasement;
    private static final int ROOMS_IN_FLOOR = 2;
    public static final int FLOOR_UNLOCK_ROOMS = 5;
    private static final int BASEMENT_UNLOCK_FLOOR = 7;
    private ResourceManager wallet;
    public RoomManager(ResourceManager resourceManager)
    {
        Room.addProduct(new BrownBread());
        Room.addProduct(new Gun());
        wallet = resourceManager;
        rooms = new ArrayList<>();
        floors = new ArrayList<>();
        resRoom = new ResearchFloor();
        rooms.add(new Room[ROOMS_IN_FLOOR]);
        curRoom =0;
        curFloor = 1;
        curBasement = -1;
        addRoom();
        wallet.setRooms(rooms);
    }

    //Doesn't include ResearchRoom
    public static int getTotalRooms(){
        int ret = floors.size() + (rooms.size()-1)*2;
        for (Room r: rooms.getLast())
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
        for(Room[] r : rooms)
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
        for (Room[] f : floors)
        {
            f[0].render(g);
        }
        g.setColor(org.newdawn.slick.Color.black);
        if (curFloor < FLOOR_UNLOCK_ROOMS)
        {
            //g.drawString("PRESS [1] to add room| Price:" + wallet.getRoomPrice(currFloor, currBasement), 10,580);
            g.drawString("PRESS [1] to add room| Price: " + wallet.getRoomPrice(curFloor, curRoom), 10,580);
        }
        else if (curFloor < BASEMENT_UNLOCK_FLOOR)
        {
            g.drawString("PRESS [2] to add FLOOR!!!" + wallet.getFloorPrice(curFloor), 10,580);
        }
        else {

            g.drawString("PRESS [2] to add FLOOR!!!"+ wallet.getFloorPrice(curFloor), 10,580);
            g.drawString("PRESS [3] to add BASEMENT!!!"+ wallet.getFloorPrice(curBasement), 10,600);

        }
    }
    public void update()
    {
        for (Room[] r :rooms)
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
        for (Room[] f :floors)
        {
            f[0].update();
        }

        wallet.update(rooms, floors);
    }
    public void keyPressed(int key, char c)
    {
        if (c == '1' && ResourceManager.getFunds()>wallet.getRoomPrice(curFloor, curRoom)) {
            addRoom();
            ResourceManager.withdraw(wallet.getRoomPrice(curFloor, curRoom));
        }
        if (c == '2'&& curFloor>=FLOOR_UNLOCK_ROOMS && ResourceManager.getFunds()>=wallet.getFloorPrice(curFloor))
        {
            floors.add(new Floor[]{new Floor (curFloor)});
            curFloor ++;
            ResourceManager.withdraw(wallet.getFloorPrice(curFloor));
        }
        if (c == '3'&& curFloor>= BASEMENT_UNLOCK_FLOOR
                && ResourceManager.getFunds()>=wallet.getFloorPrice(curBasement))
        {
            floors.add (new Floor[]{new Floor (curBasement)});
            curBasement --;
            ResourceManager.withdraw(wallet.getFloorPrice(curBasement));
        }

    }
    public void mousePressed(int button, int mX, int mY)
    {
        for (Room[] r :rooms)
        {
            for (int i = 0; i<r.length; i++)
            {
                if (r[i] != null && r[i].isOver(mX, mY))
                {
                    r[i].mousePressed(button, mX, mY);
                    wallet.updateRoom(rooms.indexOf(r),i, r[i].getNumDucks());
                }
            }
        }
        if (resRoom.isOver(mX, mY))
        {
            resRoom.mousePressed(button, mX, mY);
        }
        for (Room[] f :floors)
        {
            if (f[0].isOver(mX,mY))
            {
                f[0].mousePressed(button, mX, mY);
            }

        }
    }
    public void addRoom()
    {
        if(curFloor < FLOOR_UNLOCK_ROOMS)
        {
            rooms.getLast()[curRoom] = new Room(curFloor, curRoom);
            if(spaceOnFloor()) {
                curRoom++;
            }else
            {
                curFloor++;
                curRoom = 0;

                Room[] newFloor = new Room[ROOMS_IN_FLOOR];
                rooms.add(newFloor);
                wallet.addFloor(curFloor, curRoom);
            }
        }
    }
    //checks if there are 2 to a floor.

    public boolean spaceOnFloor()
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
