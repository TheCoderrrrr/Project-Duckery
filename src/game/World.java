package game;

import game.entities.Duck;
import game.entities.Room;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class World {

    ArrayList<Room[]> rooms;
    ArrayList<Duck> ducks;
    MoneyManager wallet;
    int currFloor;
    int currRoom;

    public World(MoneyManager wallet)
    {
        rooms = new ArrayList<Room[]>();
        rooms.add(new Room[4]);
        ducks = new ArrayList<Duck>();

        this.wallet = wallet;


        addRoom();


        wallet.setRooms(rooms);

    }

    public void render (Graphics g) {
        //makes the sky
        g.setBackground(Color.cyan);

        g.setColor(Color.black);

        //makes the grass
        g.setColor(Color.green);
        g.fillRect(0, 700, 2000, 800);

        //draws the building
        for (Room[] r :rooms)
        {
            for (int i = 0; i<=3; i++)
            {
                if (r[i] != null)
                {
                    r[i].render(g);
                }
            }
        }

        if (currFloor < 3)
        {
            g.drawString("PRESS [1] to add room", 10,580);
        }
        else
        {
            g.drawString("PRESS [1] to add FLOOR!!!", 10,580);
        }

    }

    public void update() {

    }

    public void keyPressed(int key, char c) {
        if ( c == '1')
        {
            addRoom();
        }



    }

    public void mousePressed(int button, int x, int y) {

        for (Room[] r :rooms)
        {
            for (int i = 0; i<r.length; i++)
            {
                if (r[i] != null && r[i].isOver(x, y) && r[i].getNumDucks()<3)
                {
                    r[i].mousePressed(button, x, y);
                    wallet.updateRoom(rooms.indexOf(r),i, r[i].getNumDucks());

                }
            }
        }
    }

    public void addRoom(){

        //adding a room on the same floor.
        if(currFloor < 3)
        {
            rooms.getLast()[currRoom] = new Room(currFloor,currRoom);

            if(spaceOnFloor())
            {
                currRoom++;

            }
            else
            {
                currFloor++;
                currRoom = 0;

                Room[] newFloor = new Room[4];
                rooms.add(newFloor);
                wallet.addFloor(currFloor, currRoom);
            }


        }
        else //adding a new floor
        {
            currRoom = 0;
            Room [] newFloor = new Room[4];
            for (int i=0; i<newFloor.length; i++)
            {
                newFloor[i] = new Room(currFloor, currRoom);
                currRoom++;
            }
            rooms.add(newFloor);
            wallet.addFloor(currFloor,currRoom);
            currFloor++;
        }

    }

    //checks if there are 4 to a floor.
    public boolean spaceOnFloor(){
        int count = 0;
        for(int i = 0; i<rooms.getLast().length; i++)
        {
            if (rooms.getLast() [i] != null)
            {
                count++;
            }

        }

        return (count<4);

    }

}
