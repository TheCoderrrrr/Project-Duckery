package game.clipboard.progressUpgrade.war;

import game.clipboard.progressUpgrade.BreadCounter;
import game.clipboard.progressUpgrade.UpgradeBar;
import game.clipboard.progressUpgrade.UpgradeButton;
import game.managers.ResourceManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class WarButton extends UpgradeButton {

    public WarButton(int x, int y,UpgradeBar bar, BreadCounter counter ) {super(x, y, bar,counter); }
    public void render(Graphics g)
    {
        super.render(g);
        g.setColor(Color.black);
        g.drawString("attack!", myX, myY);
    }

    public void click(int x, int y)
    {
        if (mouseOver(x,y))
        {
            if(bar.getComplete())
            {
                ResourceManager.attack(bar.getCount());
            }
        }
    }
}
