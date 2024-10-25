package game.clipboard.progressUpgrade;

import game.entities.rooms.AdRoom;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class WarButton extends UpgradeButton{
    public WarButton(int x, int y) {super(x, y);}
    public void render(Graphics g)
    {
        super.render(g);
        g.setColor(Color.black);
        g.drawString("war", myX, myY);
    }

    public void click(int x, int y)
    {
        if (mouseOver(x,y))
        {

        }
    }
}
