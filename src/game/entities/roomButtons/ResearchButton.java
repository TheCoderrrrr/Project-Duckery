package game.entities.roomButtons;

import game.PayDay;
import game.ResourceManager;
import game.World;
import game.entities.rooms.ProductRoom;
import game.entities.rooms.ResearchFloor;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ResearchButton extends RoomButton{
    ResearchFloor f;
    public ResearchButton(ResearchFloor researchFloor, int height) {
        super(researchFloor, height);
        f = researchFloor;
        myColor = Color.pink;
        info = "CLICK to research mystery produckt!\nCosts "+f.getFirstProuduct().getPrice();
    }

    public void render (Graphics g)
    {
        super.render(g);
        g.setColor(Color.black);
        g.drawString("R", myX, myY + World.getYDisplace());
    }

    @Override
    public void action() {
        if (ResourceManager.getFunds()>f.getFirstProuduct().getPrice())
        {
            f.beginResearch();
            ResourceManager.withdraw(f.getFirstProuduct().getPrice());
            PayDay.setBegin();
        }

        System.out.println("researching");
    }
}
