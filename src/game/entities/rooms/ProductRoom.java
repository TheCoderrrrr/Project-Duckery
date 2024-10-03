package game.entities.rooms;


import core.Images;
import game.World;
import game.clipboard.items.Item;
import game.clipboard.items.weapon.Gun;
import game.entities.Duck;
import game.entities.roomButtons.ChangeRoomButton;
import game.entities.roomButtons.RoomButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;

public class ProductRoom  extends Room{
    static protected int lastCurItem;

    public ProductRoom(int floor, int number)
    {
        //floor must be at least 0, number between 0 and 3.
       super(floor, number);
       curItem = lastCurItem;
       myImage = myRoomTypes.getSubImage(products.get(curItem).getImageIndex(),0);

       if (floor<0)
       {
           curItem = 4;
       }

    }

    public void render(Graphics g)
    {
        super.render(g);
        g.setColor(Color.black);

        if(!pause)
        {
            g.drawString("Num Ducks: "+getTimeToMake()+"\nFloor:"+myFloor+"\nRoom:"+timer +
                    "\nProduct" + getProductName(), x, World.getYDisplace() + y);
            for (Duck duck : ducks) duck.render(g);
            for (int i=0;i<myButtons.size();i++) myButtons.get(i).render(g);
        }
        else {
            g.drawString("Building "+ getProductName() + " room!\n ETA: "+pauseTimer, x, World.getYDisplace() + y);
        }

    }
    public void update()
    {
        super.update();
        if (!pause)
        {
            if (!isBasement() && myButtons.size()!= products.size())
            {
                myButtons = new ArrayList<>();
                for (Item p: products)
                {
                    if (p != products.get(curItem))
                    {
                        myButtons.add(new ChangeRoomButton(this, p, myButtons.size()));
                    }

                }
            }
            else if (isBasement)
            {
                myButtons = new ArrayList<>();
                for (Item p: productsUG)
                {
                    if (p != productsUG.get(0))
                    {
                        myButtons.add(new ChangeRoomButton(this, p, productsUG.size()));
                    }

                }
            }

            if(timer < getTimeToMake() && !ducks.isEmpty())
            {
                timer++;
            }
            for (int i=0;i<myButtons.size();i++) myButtons.get(i).update();
        }
        else {
            pauseTimer--;
            if (pauseTimer<=0)
            {
                pause = false;
            }
        }


    }

    public void mousePressed(int button, int x, int y) {

        super.mousePressed(button,x,y);

    }
    public boolean completedProduct()
    {
        return timer == getTimeToMake() && (!products.isEmpty() || !productsUG.isEmpty());
    }

    public void switchProduct(Item i)
    {
        if (!isBasement() && i != products.get(curItem))
        {
            curItem = products.indexOf(i);
            pause = true;
            pauseTimer = TOTAL_BUILD_TIME;
            myImage = myRoomTypes.getSubImage(products.get(curItem).getImageIndex(),0);

            resetTimer();
            lastCurItem = curItem;
        }

    }
//mutator


    public static void addProduct(Item product) {
        // adds a type of product
        if (product instanceof Gun){
            productsUG.add(product);
        }
        else
        {
            products.add(product);
        }


    }

    // accessor

    public int getValue()
    {
        if (!products.isEmpty()&& !isBasement())
        {
            return products.get(curItem).getValue();
        }
        else if (!productsUG.isEmpty()&& isBasement())
        {
            return products.get(0).getValue();
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
            return productsUG.get(0).getWarEffort();
        }
        else {
            return 0;
        }
    }
    public int getTimeToMake()
    {
        if (!isBasement() && !products.isEmpty())
        {
            return (int) ( products.get(curItem).getTimeToCreate() * Math.pow(0.50,ducks.size()) ) ;
        }
        else if (isBasement() && !productsUG.isEmpty())
        {
            return (int) ( productsUG.get(0).getTimeToCreate() * Math.pow(0.50,ducks.size()) ) ;
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
                return productsUG.get(0).getName();
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
}
