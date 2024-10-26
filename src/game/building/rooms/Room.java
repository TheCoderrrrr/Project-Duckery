package game.building.rooms;

import core.Images;
import core.Main;
import game.World;
import game.clipboard.items.Item;
import game.building.Duck;
import game.building.roomButtons.RoomButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;

abstract public class Room {
    public int width;
    final public static int HEIGHT = 200;
    final int DIST_FROM_LEFT = 125;
    protected int x;
    protected int y;
    int myFloor;
    protected ArrayList<Duck> ducks;
    int myRoom;
    protected Color myColor;
    protected static ArrayList<Item> products = new ArrayList<>();
    protected static ArrayList<Item> productsUG = new ArrayList<>();
    protected int curItem;
    protected int timer;
    protected SpriteSheet myRoomTypes;
    protected SpriteSheet myFloorTypes;
    protected Image myImage;
    protected int maxDucks;

    protected int pauseTimer;
    protected boolean pause;
    public static final int TOTAL_BUILD_TIME = 960;
    protected ArrayList<RoomButton> myButtons;
    protected boolean isBasement;


    public Room(int floor, int number)
    {
        //floor must be at least 0, number between 0 and 3.
        width = 400;
        ducks = new ArrayList<>();
        myButtons = new ArrayList<>();
        myFloor = floor;
        myRoom = number;
        x = DIST_FROM_LEFT + number*width;
        y = 4*HEIGHT-floor*HEIGHT;
        curItem = 0;
        timer = 0;
        maxDucks = 3;
        myRoomTypes = Images.ROOMS;
        myFloorTypes = Images.FLOORS;
        isBasement = floor < 0;



        pause = false;
        if(isBasement)myImage = myFloorTypes.getSubImage(productsUG.get(0).getImageIndex(),0);
        else myImage = myRoomTypes.getSubImage(products.get(curItem).getImageIndex(),0);

    }

    public void render(Graphics g) {

        //draws this if no image

        myColor = Color.lightGray;
        g.setColor(myColor);

        //draws this if YES image
        if (myImage != null && !pause) {
            g.drawImage(myImage.getScaledCopy(Main.getAdjustedX(myImage.getWidth()), Main.getAdjustedY(myImage.getHeight())),

                   Main.getAdjustedX(x), Main.getAdjustedY(World.getYDisplace() + y));
        } else if (pause) {
            int i = pauseTimer % 20 / 10;
            g.drawImage(Images.BUILD_ANIMATION.getSubImage(i, 0).getScaledCopy(Main.getAdjustedX(width),Main.getAdjustedY(HEIGHT))
                    , Main.getAdjustedX(x), Main.getAdjustedY(World.getYDisplace() + y));
        } else {
            g.fillRect(Main.getAdjustedX(x),Main.getAdjustedY(curYPosition()), Main.getAdjustedX( width), Main.getAdjustedX( HEIGHT));// creates a square room at a given location
        }

        g.setColor(Color.black);
        g.drawString("Ducks: " +ducks.size()+"/"+maxDucks, Main.getAdjustedX( x), Main.getAdjustedY( World.getYDisplace() + y));
    }

    //ACCESSOR
        //IS THE mouse over?
        public boolean mouseOver(int x, int y)
        {
            return (x>getX() && x<getX()+width && y>curYPosition() && y< curYPosition()+HEIGHT);
        }

        //info for the information on the tip
        abstract public String getInfo(int x, int y);

    //duck stuff!
        public int getNumDucks() {
            return ducks.size();
        }
         public ArrayList<Duck> getDucks()
    {
        return ducks;
    }

        //gets the current y position (displaced by world)
        public int curYPosition()
        {
            return y + World.getYDisplace();
        }

        //returns the UNCHANGED x and y;
        public int getX()
        {
            return x;
        }
        public int getY()
        {
            return y;
        }

        // gets the parameters of the world.
        public int getWidth()
        {
            return width;
        }
        public int getLeftWall()
        {
            return x;
        }
        public int getRightWall()
        {
            return x + width;
        }
        public int getFloor()
        {
            return curYPosition() + HEIGHT;
        }


    //MUTATOR


    // needs to be changed, but basically either clicks a button or adds/removes a duck
    public void mousePressed(int button, int x, int y) {

            //turn on the buttons
        boolean onButton = false;
        if (button == 0 && isOver(x, y)) {
            for (RoomButton myButton : myButtons) {
                System.out.println("can switch");
                if (myButton.mouseOver(x, y)) {
                    myButton.action();
                    onButton = true;
                }
            }
            if(!onButton && getNumDucks()<maxDucks)
            {
                addDuck(x,y);
            }

        }

        else if (button == 2) {

            if(!onButton) {
                for (Duck duck : ducks) {
                    if (duck.isOver(x, y) && ducks.size() <= maxDucks) {
                        duck.mousePressed(button, x, y);
                        break;
                    }
                }
            }

        }

    }

    //add a new duck to the room and update world!
    private void addDuck(int startX, int startY)
    {
        // adds to the amount of ducks
        if(World.getTotalDucks() < World.getDuckLimit())
        {
            Duck duck = new Duck(this, startX, startY);
            ducks.add(duck);
            World.updateDuckCount(duck, false);
            resetTimer();
        }
        System.out.println((World.getTotalDucks() < World.getDuckLimit()) + "");
    }

    //removes a duck :(
    public void removeDucks(Duck duck)
    {
        ducks.remove(duck);
        World.updateDuckCount(duck, true);
    }

    //checks if it is over.
    public boolean isOver(int x, int y)
    {
        //tells you if an x and y value is over the room
        return (x>=this.x && x<= (this.x + width)
                && y>= (this.y+World.getYDisplace()) && y<= (this.y+World.getYDisplace() + HEIGHT));
    }


    //updates the ducks
    public void update() {
        for(Duck duck : ducks)
        {
            duck.update();
        }
        if (!productsUG.isEmpty())
        {
            World.declareWar();
        }
    }
    public int getRoomSize()
    {
        return maxDucks;
    }

    //resets the timer used to make new bread
    public void resetTimer()
    {
        timer = 0;
    }

}
