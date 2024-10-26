package game.clipboard.progressUpgrade.ad;

import game.building.rooms.AdRoom;
import game.clipboard.progressUpgrade.BreadCounter;
import game.clipboard.progressUpgrade.UpgradeBar;
import game.clipboard.progressUpgrade.UpgradeButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class AdButton extends UpgradeButton {
    public AdButton(int x, int y, UpgradeBar bar, BreadCounter counter) {
        super(x, y, bar, counter);
    }
    public void render(Graphics g)
    {
        super.render(g);
        g.setColor(Color.black);
        g.drawString("AD BLITZ!", myX, myY);
    }
    public void click(int x, int y)
    {
        if (mouseOver(x,y))
        {
            AdRoom.beginResearch();
            AdRoom.releaseBlitz();
        }
    }
}
