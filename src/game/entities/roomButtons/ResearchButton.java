package game.entities.roomButtons;

import game.managers.ResourceManager;
import game.World;
import game.entities.rooms.ResearchFloor;
import game.managers.PopupManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ResearchButton extends RoomButton{
    ResearchFloor f;
    public ResearchButton(ResearchFloor researchFloor, int height) {
        super(researchFloor, height);
        f = researchFloor;
        myColor = Color.pink;
        info = "CLICK to research mystery produckt!\nCosts "+f.getFirstProuduct().getQuotaValue();
    }

    public void render (Graphics g)
    {
        super.render(g);
        g.setColor(Color.black);
        g.drawString("R", myX, myY + World.getYDisplace());
    }

    @Override
    public void action() {
        if (ResourceManager.getBreadMade()>f.getFirstProuduct().getQuotaValue())
        {
            f.beginResearch();
            ResourceManager.takeBread(f.getFirstProuduct().getQuotaValue());
            PopupManager.setBegin();
        }

        System.out.println("researching");
    }
}
