package game.entities.rooms;

import core.Images;
import game.World;
import game.clipboard.items.Item;
import game.entities.Duck;
import game.entities.roomButtons.ChangeRoomButton;
import game.entities.roomButtons.RoomButton;
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
    protected Image myImage;
    protected int maxDucks;

    protected int pauseTimer;
    protected boolean pause;
    protected final int TOTAL_BUILD_TIME = 800;
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
        isBasement = floor < 0;



        pause = false;
        if(isBasement)myImage = myRoomTypes.getSubImage(productsUG.get(curItem).getImageIndex(),0);
        else myImage = myRoomTypes.getSubImage(products.get(curItem).getImageIndex(),0);

    }

    public void render(Graphics g) {
        if ((myFloor + myRoom) % 2 == 0) {
            myColor = (Color.white);
        } else {
            myColor = Color.lightGray;
        }
        g.setColor(myColor);
        if (myImage != null && !pause) {
            g.drawImage(myImage, x, World.getYDisplace() + y);
        } else if (pause) {
            int i = pauseTimer % 20 / 10;
            g.drawImage(Images.BUILD_ANIMATION.getSubImage(i, 0), x, World.getYDisplace() + y);
        } else {
            g.fillRect(x, World.getYDisplace() + y, width, HEIGHT);// creates a square room at a given location
        }
    }
    public void mousePressed(int button, int x, int y) {

        boolean onButton = false;
        if (button == 0 && isOver(x, y) && getNumDucks() < maxDucks) {
            for (int i = 0; i < myButtons.size(); i++){
                if (myButtons.get(i).mouseOver(x, y)) {
                    myButtons.get(i).action();
                    onButton = true;
                }
            }
            if(!onButton)
            {
                addDuck(x);
            }

        }

        else if (button == 2) {

            if(!onButton) {
                for (Duck duck : ducks) {
                    if (duck.isOver(x, y) && ducks.size() < maxDucks) {
                        duck.mousePressed(button, x, y);
                        break;
                    }
                }
            }

        }


    }
    private void addDuck(int startX)
    {
        // adds to the amount of ducks
        if(World.getTotalDucks() < World.getDuckLimit())
        {
            Duck duck = new Duck(this, startX);
            ducks.add(duck);
            World.updateDuckCount(duck, false);
            resetTimer();
        }
        System.out.println((World.getTotalDucks() < World.getDuckLimit()) + "");
    }
    public void removeDucks(Duck duck)
    {
        ducks.remove(duck);
        World.updateDuckCount(duck, true);
    }

    public boolean isOver(int x, int y)
    {
        //tells you if an x and y value is over the room
        return (x>=this.x && x<= (this.x + width)
                && y>= (this.y+World.getYDisplace()) && y<= (this.y+World.getYDisplace() + HEIGHT));
    }


    public void update() {
        for(Duck duck : ducks)
        {
            duck.update();
        }
    }
    public int getNumDucks() {
        return ducks.size();
    }

    public int curYPosition()
    {
        return y + World.getYDisplace();
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void resetTimer()
    {
        timer = 0;
    }
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
    public ArrayList<Duck> getDucks()
    {
        return ducks;
    }
}