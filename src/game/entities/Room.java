package game.entities;


import core.Images;
import game.World;
import game.clipboard.items.Item;
import game.entities.roomButtons.ChangeRoomButton;
import game.entities.roomButtons.RoomButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;

public class Room {

    //final public static int SIZE = 200;
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
    protected RoomButton[] myButtons;
    private boolean isBasement;

    public Room (int floor, int number)
    {
        //floor must be at least 0, number between 0 and 3.
        width = 400;
        ducks = new ArrayList<>();
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



        myButtons = new RoomButton[]{new ChangeRoomButton(this)};

    }

    public void render(Graphics g)
    {
        if ( ( myFloor+myRoom ) %2 ==0 )
        {
            myColor = (Color.white);
        }
        else
        {
            myColor = Color.lightGray;
        }
        g.setColor(myColor);
        if (myImage != null && !pause)
        {
            g.drawImage(myImage, x, World.getYDisplace() + y);
        }
        else if (pause)
        {
            int i = pauseTimer%20/10;
            g.drawImage(Images.BUILD_ANIMATION.getSubImage(i,0), x, World.getYDisplace() + y);
        }
        else {
            g.fillRect(x, World.getYDisplace() + y, width, HEIGHT);// creates a square room at a given location
        }
        g.setColor(Color.black);

        if(!pause)
        {
            g.drawString("Num Ducks: "+getNumDucks()+"\nFloor:"+myFloor+"\nRoom:"+myRoom +
                    "\nProduct" + getProductName(), x, World.getYDisplace() + y);
            for (Duck duck : ducks) duck.render(g);
            for (int i=0;i<myButtons.length;i++) myButtons[i].render(g);
        }
        else {
            g.drawString("Building "+ getProductName() + " room!\n ETA: "+pauseTimer, x, World.getYDisplace() + y);
        }




    }
    public void update()
    {
        if (!pause)
        {

            if(timer < getTimeToMake() && !ducks.isEmpty())
            {
                timer++;
            }
            if (!products.isEmpty())
            {

            }
            for (int i=0;i<myButtons.length;i++) myButtons[i].update();
        }
        else {
            pauseTimer--;
            if (pauseTimer<=0)
            {
                pause = false;
            }
        }
        for(Duck duck : ducks)
        {
            duck.update();
        }




    }

    public void mousePressed(int button, int x, int y) {

        if(button == 0 && isOver(x,y) && getNumDucks()<maxDucks) addDuck(x);
        else if(button == 1 && isOver(x,y)) switchProduct();
        else if(button == 2)
        {
            for (Duck duck : ducks) {
                if (duck.isOver(x, y)) {
                    duck.mousePressed(button, x, y);
                    break;
                }
            }
        }
        for (int i=0;i<myButtons.length;i++) if (myButtons[i].mouseOver(x,y))
        {
            myButtons[i].action();
        }

    }
    public boolean completedProduct()
    {
        return timer == getTimeToMake() && !products.isEmpty();
    }
    public void switchProduct()
    {
        if(isBasement)
        {
            if(curItem < productsUG.size() - 1) curItem++;
            else curItem = 0;
            pause = true;
            pauseTimer = TOTAL_BUILD_TIME;
            myImage = myRoomTypes.getSubImage(productsUG.get(curItem).getImageIndex(),0);

            resetTimer();
        }
        else{
            if(curItem < products.size() - 1) curItem++;
            else curItem = 0;
            pause = true;
            pauseTimer = TOTAL_BUILD_TIME;
            myImage = myRoomTypes.getSubImage(products.get(curItem).getImageIndex(),0);

            resetTimer();
        }
    }
//mutator
    public void addDuck(int startX)
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
    public static void addProduct(Item product)
    {
        if(product.getIsBasement()) productsUG.add(product);
        else products.add(product);

    }

    // accessor
    public int getNumDucks() {
        return ducks.size();
    }

    public int curYPosition()
    {
        return y + World.getYDisplace();
    }
    public boolean isOver(int x, int y)
    {
        //tells you if an x and y value is over the room
        return (x>=this.x && x<= (this.x + width)
                && y>= (this.y+World.getYDisplace()) && y<= (this.y+World.getYDisplace() + HEIGHT));
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getValue()
    {
        if (!products.isEmpty())
        {
            return products.get(curItem).getValue();
        }
        else {
            return 0;
        }

    }
    public boolean isBasement()
    {
        return isBasement;
    }
    public int getWarEffort()
    {
        if (!productsUG.isEmpty())
        {
            return productsUG.get(curItem).getWarEffort();
        }
        else {
            return 0;
        }
    }
    public int getTimeToMake()
    {
        if (!products.isEmpty())
        {
            return (int) ( products.get(curItem).getTimeToCreate() * Math.pow(0.50,ducks.size()) ) ;
        }
        else {
            return 1;
        }

    }
    public String getProductName()
    {
        if(isBasement)
        {
            if (!productsUG.isEmpty())
            {
                return productsUG.get(curItem).getName();
            }
            else {
                return "none-purchase to add";
            }
        }
        else
        {
            if (!products.isEmpty())
            {
                return products.get(curItem).getName();
            }
            else {
                return "none-purchase to add";
            }
        }

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
