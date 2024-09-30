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

public class ProductRoom  extends Room{

    public ProductRoom(int floor, int number)
    {
        //floor must be at least 0, number between 0 and 3.
       super(floor, number);

        myButtons.add(new ChangeRoomButton(this));

    }

    public void render(Graphics g)
    {
        super.render(g);
        g.setColor(Color.black);

        if(!pause)
        {
            g.drawString("Num Ducks: "+getNumDucks()+"\nFloor:"+myFloor+"\nRoom:"+myRoom +
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

            if(timer < getTimeToMake() && !ducks.isEmpty())
            {
                timer++;
            }
            if (!products.isEmpty())
            {

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
        if(button == 1 && isOver(x,y)) switchProduct();

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


    public static void addProduct(Item product)
    {
        if(product.getIsBasement()) productsUG.add(product);
        else products.add(product);

    }

    // accessor

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
}
