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

    public World(MoneyManager wallet)
    {
        rooms = new ArrayList<Room[]>();
        ducks = new ArrayList<Duck>();

        this.wallet = wallet;

        Room[] room = {new Room(0,0), new Room(0,1), null, null};
        rooms.add(room);

        wallet.setRooms(rooms);

    }

    public void render (Graphics g) {
        //makes the sky
        g.setBackground(Color.cyan);

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
    }

    public void update() {

    }

    public void keyPressed(int key, char c) {



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

    //public void addRoom()

}
