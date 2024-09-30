package game;

import core.Images;
import core.Main;
import game.entities.Duck;
import game.entities.rooms.Room;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class World {
    public static ArrayList<Duck> ducks;
    private ResourceManager wallet;
    private RoomManager roomManager;
    private int currBasement;
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
        this.gc = gc;
        ducks = new ArrayList<>();
        yDisplace =0;
        wallet = new ResourceManager();
        roomManager = new RoomManager(wallet);
        duckLimit = 1;
        pause = false;


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
        roomManager.render(g);

        g.drawString("number of ducks not placed:"+ (duckLimit - getTotalDucks() ), 10, 620);
        g.drawString("[T] for top [B] for bottom [0] for base", 10, 640);

        g.setColor (Color.black);
        g.drawString("$" + wallet.getFund() +"\nINCOME:" + wallet.getIncomeRate() +
                " per second\nBread made: " + wallet.getBreadMade() + "\nDucksTotal: "+getTotalDucks(), 20,20);

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
            roomManager.update();
        }

    }

    public void keyPressed(int key, char c) {
        roomManager.keyPressed(key, c);

        //change to buttons later, will "hotkey" to top and bottom (yay!)
        if (c == '0')
        {
            yDisplace = 0;
        }
        if (c == 'T' || c == 't')
        {
            yDisplace = (roomManager.getCurFloor()) * Room.HEIGHT - Main.getScreenHeight()/2;
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

    public void mousePressed(int button, int x, int y) {

        if (!pause)
        {
            roomManager.mousePressed(button, x, y);
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
