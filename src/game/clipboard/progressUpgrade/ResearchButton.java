package game.clipboard.progressUpgrade;

import game.entities.rooms.ResearchFloor;
import game.managers.PopupManager;
import game.managers.ResourceManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import static game.entities.rooms.ResearchFloor.releaseProduct;

public class ResearchButton extends UpgradeButton {
    public ResearchButton(int x, int y) {
        super(x, y);
    }
    public void render(Graphics g)
    {
        super.render(g);
        g.setColor(Color.black);
        g.drawString("add "+ResearchFloor.getFirstProuduct().getName(), myX, myY);
    }
    public void click(int x, int y)
    {
        if (mouseOver(x,y))
        {

            if (ResourceManager.getBreadMade()>ResearchFloor.getFirstProuduct().getQuotaValue())
            {
                ResearchFloor.beginResearch();
                ResourceManager.takeBread(ResearchFloor.getFirstProuduct().getQuotaValue());
                releaseProduct();
            }

        }
    }
}
