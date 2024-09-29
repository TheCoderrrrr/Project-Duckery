package game;

import core.Images;
import core.Main;
import game.clipboard.items.bread.BrownBread;
import game.entities.Duck;
import game.entities.Floor;
import game.entities.Room;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class World {

    private ArrayList<Room[]> rooms;
    private ArrayList<Room[]> floors;
    public static ArrayList<Duck> ducks;
    private ResourceManager wallet;
    private int currFloor;
    private int currBasement;
    private int currRoom;
    private static GameContainer gc;
    private static int yDisplace;
    private static int duckLimit;
    //private maxDucks;
    //private curDucks;
    
    
    private final int Y_SPEED = Room.HEIGHT/10;
    private final double GROUND_CHANGE = 0.75;//changes the valyue of y-speed to make background look far
    public static final int ROOMS_IN_FLOOR = 2;
    public static final int FLOOR_UNLOCK_ROOMS = 5;
    public static final int BASEMENT_UNLOCK_FLOOR=7;

    //3 locations for ppl to scroll
    public static final int BASE_SCROLL =0;
    private static int BOTTOM_SCROLL;
    private static int TOP_SCROLL;


    public static boolean pause;
    
    
    

    public World(GameContainer gc)
    {
        Room.addProduct(new BrownBread());
        this.gc = gc;
        rooms = new ArrayList<Room[]>();
        floors = new ArrayList<Room[]>();
        rooms.add(new Room[ROOMS_IN_FLOOR]);
        ducks = new ArrayList<Duck>();
        yDisplace =0;
        wallet = new ResourceManager();
        duckLimit = 1;
        currBasement = -1;
        currRoom = 0;
        pause = false;


        addRoom();


        wallet.setRooms(rooms);

        //for testing


    }

    public static boolean getPause(){ return pause;}

    public void render (Graphics g) {
        //makes the sky
        if (yDisplace<-2000)
        {
            g.setBackground(Color.green);
        }
        else {
            g.setBackground(Color.cyan);
        }




        g.setColor(Color.black);

        //makes the grass
        g.setColor(Color.green);
        g.fillRect(0, (int) (yDisplace*GROUND_CHANGE) +700, 2000, 2000);

        //draws the building
        for (Room[] r :rooms)
        {
            for (int i = 0; i<ROOMS_IN_FLOOR; i++)
            {
                if (r[i] != null)
                {
                    r[i].render(g);
                }
            }
        }
        for (Room[] f :floors)
        {
            f[0].render(g);
        }

        if (currFloor < FLOOR_UNLOCK_ROOMS)
        {
            //g.drawString("PRESS [1] to add room| Price:" + wallet.getRoomPrice(currFloor, currBasement), 10,580);
            g.drawString("PRESS [1] to add room| Price: " + wallet.getRoomPrice(currFloor, currRoom), 10,580);
        }
        else if (currFloor < BASEMENT_UNLOCK_FLOOR)
        {
            g.drawString("PRESS [2] to add FLOOR!!!" + wallet.getFloorPrice(currFloor), 10,580);
        }
        else {

            g.drawString("PRESS [2] to add FLOOR!!!"+ wallet.getFloorPrice(currFloor), 10,580);
            g.drawString("PRESS [3] to add BASEMENT!!!"+ wallet.getFloorPrice(currBasement), 10,600);

        }
        g.drawString("number of ducks not placed:"+ (duckLimit - getTotalDucks() ), 10, 620);
        g.drawString("[T] for top [B] for bottom [0] for base", 10, 640);

    }

    public void update() {

        if (!pause)
        {
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
                for (int i = 0; i<ROOMS_IN_FLOOR; i++)
                {
                    if (r[i] != null)
                    {
                        r[i].update();
                    }
                }
            }
            for (Room[] f :floors)
            {
                f[0].update();
            }

            wallet.update(rooms, floors);
        }

    }

    public void keyPressed(int key, char c) {
        if (c == '1' && ResourceManager.getFunds()>wallet.getRoomPrice(currFloor, currRoom)) {
            addRoom();
            ResourceManager.withdraw(wallet.getRoomPrice(currFloor, currRoom));
        }
        if (c == '2'&& currFloor>=FLOOR_UNLOCK_ROOMS && ResourceManager.getFunds()>=wallet.getFloorPrice(currFloor))
        {
            floors.add(new Floor[]{new Floor (currFloor)});
            currFloor ++;
            ResourceManager.withdraw(wallet.getFloorPrice(currFloor));
        }
        if (c == '3'&& currFloor>= BASEMENT_UNLOCK_FLOOR
        && ResourceManager.getFunds()>=wallet.getFloorPrice(currBasement))
        {
            floors.add (new Floor[]{new Floor (currBasement)});
            currBasement --;
            ResourceManager.withdraw(wallet.getFloorPrice(currBasement));
        }

        //change to buttons later, will "hotkey" to top and bottom (yay!)
        if (c == '0')
        {
            yDisplace = 0;
        }
        if (c == 'T' || c == 't')
        {
            yDisplace = (currFloor) * Room.HEIGHT - Main.getScreenHeight()/2;
        }
        if (c == 'B' || c == 'b')
        {
            yDisplace = (currBasement*Room.HEIGHT);
        }

    }

    //ACCESSOR

    public static int getYDisplace()
    {
        return yDisplace;
    }

    //checks if there are 2 to a floor.
    public boolean spaceOnFloor(){
        int count = 0;
        for(int i = 0; i<rooms.getLast().length; i++)
        {
            if (rooms.getLast() [i] != null)
            {
                count++;
            }

        }

        return (count<ROOMS_IN_FLOOR);

    }

    public void mousePressed(int button, int x, int y) {

        if (!pause)
        {
            for (Room[] r :rooms)
            {
                for (int i = 0; i<r.length; i++)
                {
                    if (r[i] != null && r[i].isOver(x, y))
                    {
                        r[i].mousePressed(button, x, y);
                        wallet.updateRoom(rooms.indexOf(r),i, r[i].getNumDucks());
                    }
                }
            }
            for (Room[] f :floors)
            {
                if (f[0].isOver(x,y))
                {
                    f[0].mousePressed(button, x, y);
                }

            }
        }

    }

//mutator

    public static void pause()
    {
        pause = true;
    }
    public static void unpause()
    {
        pause = false;
    }

    public void addRoom(){

        //adding a room on the same floor.
        if(currFloor < FLOOR_UNLOCK_ROOMS)
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

                Room[] newFloor = new Room[ROOMS_IN_FLOOR];
                rooms.add(newFloor);
                wallet.addFloor(currFloor, currRoom);
            }
        }

    }

    public static int getDuckLimit()
    {
        return duckLimit;
    }
    //
    public static void increaseDuckLimit() throws SlickException {
        duckLimit++;
        gc.setMouseCursor(Images.DUC1, 40,40);
    }
    public static int getTotalDucks()
    {
        return ducks.size();
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
    public static void updateDuckCount(Duck duck, boolean remove)
    {
        if(remove)
        {
            ducks.remove(duck);
        }else
        {
            ducks.add(duck);

        }
        if (duckLimit - getTotalDucks() == 0)
        {
            gc.setDefaultMouseCursor();
        }
        else {
            try {
                gc.setMouseCursor(Images.DUC1, 50,50);
            } catch (SlickException e) {
                System.out.println("whoops");
                throw new RuntimeException(e);

            }
        }

    }

}
