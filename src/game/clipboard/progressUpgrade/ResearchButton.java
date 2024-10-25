package game.clipboard.progressUpgrade;

import game.entities.rooms.ResearchFloor;
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
            releaseProduct();
            ResearchFloor.beginResearch();
        }
    }
}
