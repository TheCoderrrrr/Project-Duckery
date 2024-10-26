package game.clipboard.progressUpgrade.research;

import game.building.rooms.ResearchFloor;
import game.clipboard.progressUpgrade.BreadCounter;
import game.clipboard.progressUpgrade.UpgradeBar;
import game.clipboard.progressUpgrade.UpgradeButton;
import game.managers.ResourceManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import static game.building.rooms.ResearchFloor.releaseProduct;

public class ResearchButton extends UpgradeButton {
    public ResearchButton(int x, int y, UpgradeBar bar, BreadCounter counter) {
        super(x, y, bar, counter);
    }
    public void render(Graphics g)
    {
        super.render(g);
        if (ResearchFloor.getFirstProuduct() != null)
        {
            g.setColor(Color.black);
            g.drawString("add "+ResearchFloor.getFirstProuduct().getName(), myX, myY);
        }
        else
        {
            g.drawString("no \nproduckts \navailable \n:(", myX,myY);
        }

    }
    public void click(int x, int y)
    {
        if (mouseOver(x,y))
        {

            if (ResearchFloor.getFirstProuduct() != null && ResourceManager.getBreadMade()>ResearchFloor.getFirstProuduct().getQuotaValue())
            {
                ResearchFloor.beginResearch();
                ResourceManager.takeBread(ResearchFloor.getFirstProuduct().getQuotaValue());
                releaseProduct();
            }

        }
    }
}
