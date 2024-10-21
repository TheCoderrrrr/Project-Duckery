package game.entities.rooms;

import game.PayDay;
import game.World;
import game.clipboard.items.Item;
import game.clipboard.items.bread.BlandBread;
import game.clipboard.items.bread.BrownBread;
import game.clipboard.items.bread.CosmicBread;
import game.clipboard.items.bread.DivineBread;
import game.clipboard.items.weapon.Gun;
import game.entities.Duck;
import game.entities.roomButtons.ResearchButton;
import game.entities.roomButtons.RoomButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class ResearchFloor extends Room{
    int resTotalTime;
    int resTimer;
    private boolean research; // tells if a product is being researched
    boolean productsAvailable;
    ArrayList<Item> productLine;

    public ResearchFloor() {
        super(0,0);
        width = 800;
        maxDucks = 6;
        resTotalTime = 60*90;
        resTimer = -1;

        productLine = new ArrayList<>();
        //productLine.add(new BrownBread());
        productLine.add(new CosmicBread());
        productLine.add(new DivineBread());
        productLine.add(new Gun());

        productsAvailable = true;
        myImage = myFloorTypes.getSubImage(5,0);
        //myButtons.add(new ResearchButton(this, myButtons.size()));
    }

    public void render(Graphics g)
    {
        super.render(g);// creates a square room at a given location
        g.setColor(Color.black);
        if (ducks.isEmpty())
        {
            g.drawString("add a duck to research an improved product", x, World.getYDisplace() + y + 20);
        }
        else if (research)
        {
            g.drawString("currently researching" + productLine.getFirst().getName()
                    +"\nTime to comletion: "+(resTimer)
                    , x, World.getYDisplace() + y+ 20);
        }

        for (Duck duck : ducks) duck.render(g);
        for (int i=0;i<myButtons.size();i++) myButtons.get(i).render(g);

    }

    public void update()
    {
        super.update();

        if (!ducks.isEmpty() && !productLine.isEmpty() &&!research)
        {
            beginResearch();
            PayDay.setBegin();
        }
        if (research && resTimer>0)
        {
            resTimer-= (int) Math.pow(1.5,getNumDucks() );
        }
        else if (research && resTimer <=0 && !productLine.isEmpty())
        {
            //to add: add to product list
            ProductRoom.addProduct(productLine.getFirst());
            productLine.remove(productLine.getFirst());
            research = false;
        }

        else if (resTimer<=0 && productLine.isEmpty())
        {
            productsAvailable = false;
        }
    }

    public Item getFirstProuduct() {
        if (productLine.isEmpty()) {
            return new BlandBread();
        }
        else
        {
            return productLine.getFirst();
        }

    }

    public void beginResearch() {
        if (!ducks.isEmpty() )
        {
            System.out.println("researching "+productLine.getFirst().getName());
            resTimer = resTotalTime;
            research = true;
        }
    }

    public String getInfo(int x, int y)
    {
        String ret = "";
        boolean overButton = false;
        for (RoomButton b: myButtons)
        {
            if (b.mouseOver(x,y))
            {
                ret = b.getInfo();
                overButton = true;
            }
        }
        if (!overButton)
        {
            if(research)
            {
                ret = "Currently Researching "+productLine.getFirst().getName();
            }
            else {
                ret = "hire a duck to research new produckts!";
            }
        }

        return ret;
    }

    public void removeDucks( Duck duck)
    {
        super.removeDucks(duck);
        if (resTimer != 0 )
        {
            research = false;
        }
    }
}
