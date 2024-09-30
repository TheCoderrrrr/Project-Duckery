package game.entities.rooms;

import core.Images;
import game.World;
import game.entities.Duck;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ResearchFloor extends Floor {

    public ResearchFloor() {
        super(0);
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

        g.fillRect(x, World.getYDisplace() + y, width, HEIGHT);// creates a square room at a given location
        g.setColor(Color.black);

        if(!pause)
        {
            g.drawString("Num Ducks: "+getNumDucks()+"\nFloor:"+myFloor+"\nRESEARCHING\nETA:"+timer, x, World.getYDisplace() + y);
            for (Duck duck : ducks) duck.render(g);
            for (int i=0;i<myButtons.length;i++) myButtons[i].render(g);
        }

    }
}
