package game.entities;


import game.MoneyManager;
import game.World;
import game.clipboard.items.Item;
import game.clipboard.items.bread.BlandBread;
import game.clipboard.items.bread.CosmicBread;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Room {

    final public static int SIZE = 200;
    final int DIST_FROM_LEFT = 125;
    private int x;
    private int y;
    int myFloor;
    private ArrayList<Duck> ducks;
    int myRoom;
    private Color myColor;
    private ArrayList<Item> products = new ArrayList<>();
    private int curItem;
    private int timer;

    public Room (int floor, int number)
    {
        //floor must be at least 0, number between 0 and 3.
        ducks = new ArrayList<>();
        myFloor = floor;
        myRoom = number;
        x = DIST_FROM_LEFT + number*SIZE;
        y = 4*SIZE-floor*SIZE;
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
        g.fillRect(x, World.getYDisplace() + y,SIZE,SIZE); // creates a square room at a given location
        g.setColor(Color.black);
        g.drawString("Num Ducks: "+getNumDucks()+"\nFloor:"+myFloor+"\nRoom:"+myRoom +
                "\nmycolor:"+( (myFloor+myRoom)%2 == 0 ) + "\nTime to completion:" + (getTimeToMake() - timer) + "\nValue:" + getValue() + "\nProduct" + getProductName(), x, World.getYDisplace() + y);
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

        if(button == 0 && isOver(x,y) ) addDuck();
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
        return (x>=this.x && x<= (this.x + SIZE)
                && y>= (this.y+World.getYDisplace()) && y<= (this.y+World.getYDisplace() + SIZE));
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
        return products.get(curItem).getTimeToCreate();
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
        return x + SIZE;
    }
    public int getFloor()
    {
        return curYPosition() + SIZE;
    }
    public ArrayList<Duck> getDucks()
    {
        return ducks;
    }
}
