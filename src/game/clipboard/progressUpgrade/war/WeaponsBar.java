package game.clipboard.progressUpgrade.war;

import game.clipboard.progressUpgrade.UpgradeBar;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class WeaponsBar extends UpgradeBar {
    int minimum;
    public WeaponsBar(int x, int y, String title) {
        super(x, y, title);
        minimum = 20;
        total = 50;
    }

    public void render(Graphics g)
    {
        super.render( g);
        g.setColor(Color.lightGray);
        g.setLineWidth(3);
        g.drawLine(myX+width*((float)minimum/total),myY,myX+width*((float)minimum/total) , myY+height);
        g.resetLineWidth();
    }


    public void setMinimum(int min) {
        if (minimum < total) {
            minimum = min;
        }
    }


    public boolean getComplete(){
        return  counter>=minimum|| percent >= 1.0;}


}
