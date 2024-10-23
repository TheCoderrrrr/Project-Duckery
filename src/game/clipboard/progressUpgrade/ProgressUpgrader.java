package game.clipboard.progressUpgrade;

import game.progressBar.ProgressBar;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ProgressUpgrader extends ProgressBar {
    int counter;
    int total;
    int x;
    int y;

    public ProgressUpgrader(int counter, int total, int x, int y, String title){
        super(x, y, 100, 700, title);
        this.counter = counter;
        this.total = total;
        percent = (float)counter/total;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.black);
        g.drawString(title, myX, myY- 25);
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

    public boolean getComplete(){return  counter>=total;}
    public void setTotal(int total){
        this.total = total;
        percent = (float)counter/total;}
    public void addCounter(){
        counter++;
        percent = (float) counter/total;}

}
