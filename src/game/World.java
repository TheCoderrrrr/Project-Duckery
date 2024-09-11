package game;

import game.entities.Duck;
import game.entities.Room;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

public class World {

    private ArrayList<Room[]> rooms;
    public static ArrayList<Duck> ducks;
    private MoneyManager wallet;
    private int currFloor;
    private int currRoom;
    private GameContainer gc;
    private static int yDisplace;
    //private maxDucks;
    //private curDucks;
    
    
    private final int Y_SPEED = Room.SIZE/10;
    private final double GROUND_CHANGE = 0.75;//changes the valyue of y-speed to make background look far
    
    
    

    public World(GameContainer gc)
    {

        this.gc = gc;
        rooms = new ArrayList<Room[]>();
        rooms.add(new Room[4]);
        ducks = new ArrayList<Duck>();
        yDisplace =0;
        wallet = new MoneyManager();


        addRoom();


        wallet.setRooms(rooms);

    }

    public void render (Graphics g) {
        //makes the sky
        if (yDisplace<-2000)
        {
            g.setBackground(Color.green);
        }
        else {
            g.setBackground(Color.cyan);
        }
        wallet.render(g);


        g.setColor(Color.black);

        //makes the grass
        g.setColor(Color.green);
        g.fillRect(0, (int) (yDisplace*GROUND_CHANGE) +700, 2000, 2000);

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
        if (gc.getInput().isKeyDown(Input.KEY_UP)||gc.getInput().isKeyDown(Input.KEY_W))
        {
            yDisplace+=Y_SPEED;
        }
        else if (gc.getInput().isKeyDown(Input.KEY_DOWN)||gc.getInput().isKeyDown(Input.KEY_S))
        {
            yDisplace-=Y_SPEED;
        }
        for (Room[] r :rooms)
        {
            for (int i = 0; i<=3; i++)
            {
                if (r[i] != null)
                {
                    r[i].update();
                }
            }
        }
        wallet.update(rooms);
    }

    public void keyPressed(int key, char c) {
        if (c == '1') {
            addRoom();
        }



    }

    //ACCESSOR

    public static int getYDisplace()
    {
        return yDisplace;
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

//mutator
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

    public void mouseWheelMoved(int newValue) {
        if (newValue> 0)
        {
            yDisplace+=Y_SPEED;
        }
        if (newValue< 0)
        {
            yDisplace-=Y_SPEED;
        }

    }
}
