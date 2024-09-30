package game.entities.rooms;

import game.World;
import game.entities.Duck;
import game.entities.roomButtons.ResearchButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ResearchFloor extends Room{
    int resTotalTime;
    int resTimer;
    boolean research;

    public ResearchFloor() {
        super(0,0);
        width = 800;
        maxDucks = 6;
        resTotalTime = 60;
        resTimer = 0;
        myButtons.add(new ResearchButton(this));
    }

    public void render(Graphics g)
    {
        super.render(g);// creates a square room at a given location
        g.setColor(Color.black);

        g.drawString("Num Ducks: "+getNumDucks()+"\nFloor:"+myFloor+"\nRESEARCHING?"+research+"\nETA:"+resTimer, x, World.getYDisplace() + y);
        for (Duck duck : ducks) duck.render(g);
        for (int i=0;i<myButtons.size();i++) myButtons.get(i).render(g);

    }

    public void update()
    {
        super.update();
        if (research)
        {
            resTimer--;
        }
        if (resTimer ==0)
        {
            //to add: add to product list
            research = false;
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
