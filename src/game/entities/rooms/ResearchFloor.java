package game.entities.rooms;

import game.World;
import game.clipboard.items.Item;
import game.clipboard.items.bread.BlandBread;
import game.clipboard.items.bread.BrownBread;
import game.clipboard.items.bread.CosmicBread;
import game.clipboard.items.bread.DivineBread;
import game.clipboard.items.weapon.Gun;
import game.entities.Duck;
import game.entities.roomButtons.ResearchButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class ResearchFloor extends Room{
    int resTotalTime;
    int resTimer;
    boolean research;
    boolean productsAvailable;
    ArrayList<Item> productLine;

    public ResearchFloor() {
        super(0,0);
        width = 800;
        maxDucks = 6;
        resTotalTime = 800;
        resTimer = -1;
        myButtons.add(new ResearchButton(this, myButtons.size()));
        productLine = new ArrayList<>();
        productLine.add(new BlandBread());
        productLine.add(new BrownBread());
        productLine.add(new CosmicBread());
        productLine.add(new DivineBread());
        productLine.add(new Gun());
        productsAvailable = true;
    }

    public void render(Graphics g)
    {
        super.render(g);// creates a square room at a given location
        g.setColor(Color.black);

        g.drawString("Num Ducks: "+getNumDucks()+"\nFloor:"+myFloor+"\nRESEARCHING?"+research+
                "\nProducts Available? "+productLine.size()+"\nETA:"+resTimer, x, World.getYDisplace() + y);
        for (Duck duck : ducks) duck.render(g);
        for (int i=0;i<myButtons.size();i++) myButtons.get(i).render(g);

    }

    public void update()
    {
        super.update();
        if (research & resTimer>0)
        {
            resTimer--;
        }
        if (resTimer ==0 && !productLine.isEmpty())
        {
            //to add: add to product list
            ProductRoom.addProduct(productLine.getFirst());
            productLine.remove(productLine.getFirst());
            research = false;
            resTimer = -1;
            resTotalTime *= 2;
        }
        else if (resTimer == 0 && productLine.isEmpty())
        {
            productsAvailable = false;
        }
    }

    public void beginResearch() {
        if (!ducks.isEmpty())
        {
            resTimer = resTotalTime;
            research = true;
        }
    }
}
