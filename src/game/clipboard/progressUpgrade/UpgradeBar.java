package game.clipboard.progressUpgrade;

import game.clipboard.ProgressBar;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class UpgradeBar extends ProgressBar {
    int counter;
    int total;
    int x;
    int y;

    public UpgradeBar(int counter, int total, int x, int y, String title){
        super(x, y, 75, 450, title);
        this.counter = counter;
        this.total = total;
        percent = (float)counter/total;
    }
    public UpgradeBar(int x, int y, String title){
        super(x, y, 75, 450, title);
        counter = 0;
        total = 1;
        percent = (float)counter/total;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.black);
        g.drawString(title+getComplete(), myX, myY- 25);
        g.fillRect(myX, myY, width, height);
        g.setColor(Color.yellow);
        if (percent<=1)
        {
            g.fillRoundRect(myX+5, myY+5,  (int) (width*percent)-10, height-10, 10);
        }
        else {
            g.fillRoundRect(myX+5, myY+5,  (int) (width*percent)-10, height-10, 10);
        }


    }

    public String getMessage(){
        if (!getComplete())
        {
            return "not researched yet";
        }
        return "";
    }

    public boolean getComplete(){return  counter>=total|| percent >= 1.0;}
    public void setTotal(int total){
        this.total = total;
        percent = (float)counter/total;}

    public void setPercent(float percent)
    {
        this.percent = percent;
    }
    public void addCounter(){
        counter++;
        percent = (float) counter/total;}

}
