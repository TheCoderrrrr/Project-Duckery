package game.entities;


import game.World;
import game.clipboard.items.Item;
import game.clipboard.items.bread.BlandBread;
import game.clipboard.items.bread.CosmicBread;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Room {

    //final public static int SIZE = 200;
    final public static int WIDTH = 400;
    final public static int HEIGHT = 200;
    final int DIST_FROM_LEFT = 125;
    protected int x;
    protected int y;
    int myFloor;
    protected ArrayList<Duck> ducks;
    int myRoom;
    protected Color myColor;
    protected ArrayList<Item> products = new ArrayList<>();
    protected int curItem;
    protected int timer;

    public Room (int floor, int number)
    {
        //floor must be at least 0, number between 0 and 3.
        ducks = new ArrayList<>();
        myFloor = floor;
        myRoom = number;
        x = DIST_FROM_LEFT + number*WIDTH;
        y = 4*HEIGHT-floor*HEIGHT;
        curItem = 0;
        timer = 0;
        products.add(new BlandBread());
        products.add(new CosmicBread());

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
        g.fillRect(x, World.getYDisplace() + y,WIDTH,HEIGHT); // creates a square room at a given location
        g.setColor(Color.black);
        if (myFloor >= 0 && myFloor < World.FLOOR_UNLOCK_ROOMS)
        {
            g.drawString("Num Ducks: "+getNumDucks()+"\nFloor:"+myFloor+"\nRoom:"+myRoom +
                    "\nmycolor:"+( getTimeToMake() ) + "\nTime to completion:" + (getTimeToMake() - timer) + "\nValue:" + getValue() + "\nProduct" + getProductName(), x, World.getYDisplace() + y);
        }

        for (Duck duck : ducks) duck.render(g);

    }
    public void update()
    {
        for(Duck duck : ducks)
        {
            duck.update();
        }
        if(timer < getTimeToMake())
        {
            timer++;
        }
    }

    public void mousePressed(int button, int x, int y) {

        if(button == 0 && isOver(x,y) && getNumDucks()<3) addDuck();
        else if(button == 2 && isOver(x,y) && curItem < products.size() - 1) curItem++;
        else if(button == 1)
        {
            for (Duck duck : ducks) {
                if (duck.isOver(x, y)) {
                    duck.mousePressed(button, x, y);
                    break;
                }
            }
        }

    }
    public boolean completedProduct()
    {
        return timer == getTimeToMake();
    }
//mutator
    public void addDuck()
    {
        // adds to the amount of ducks
        if(World.getTotalDucks() < World.getDuckLimit())
        {
            Duck duck = new Duck(this);
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
        return (x>=this.x && x<= (this.x + WIDTH)
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
        return products.get(curItem).getValue();
    }
    public int getTimeToMake()
    {
        return (int) ( products.get(curItem).getTimeToCreate() * Math.pow(0.65,ducks.size()) ) ;
    }
    public String getProductName()
    {
        return products.get(curItem).getName();
    }
    public void resetTimer()
    {
        timer = 0;
    }
    public int getLeftWall()
    {
        return x;
    }
    public int getRightWall()
    {
        return x + WIDTH;
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
