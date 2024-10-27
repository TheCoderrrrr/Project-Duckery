package game.clipboard.progressUpgrade.ad;

import game.building.rooms.AdRoom;
import game.building.rooms.ResearchFloor;
import game.clipboard.progressUpgrade.BreadCounter;
import game.clipboard.progressUpgrade.UpgradeBar;
import game.clipboard.progressUpgrade.UpgradeButton;
import game.managers.ResourceManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class AdButton extends UpgradeButton {
    float breadCost;
    public AdButton(int x, int y, UpgradeBar bar, BreadCounter counter) {
        super(x, y, bar, counter);
        breadCost = 3;//cost of bread to advertise
        count.setTotal((int)breadCost);
    }
    public void render(Graphics g)
    {
        super.render(g);
        g.setColor(Color.black);
        g.drawString("AD BLITZ!", myX, myY);
    }
    public void click(int x, int y)
    {
        if (mouseOver(x,y) && count.getComplete() && bar.getComplete())
        {
            AdRoom.beginResearch();
            ResourceManager.takeBread((int)breadCost);
            AdRoom.releaseBlitz();
            breadCost*=1.3f;
            count.setTotal((int)breadCost);
        }
    }
}
