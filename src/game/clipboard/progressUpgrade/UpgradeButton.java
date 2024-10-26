package game.clipboard.progressUpgrade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class UpgradeButton {
    protected int myX;
    protected int myY;
    protected final int SIZE = 100;
    protected String message;
    protected Color myColor;

    protected UpgradeBar bar;
    protected BreadCounter count;
    //individual upgraders;
    public UpgradeButton(int x, int y, UpgradeBar bar, BreadCounter count)
    {
        myX = x;
        myY = y;
        myColor = Color.green;
        message = "";

        this.bar = bar;
        this.count = count;
    }

    public void update()
    {
        if (count.getComplete() && bar.getComplete())
        {
            myColor = Color.green;
        }
        else if (count.getComplete()|| bar.getComplete())
        {
            myColor = Color.yellow;
        }
        else {
            myColor = Color.red;
        }
    }
    public void render(Graphics g)
    {
        g.setColor(myColor);
        g.fillRect(myX, myY, SIZE, SIZE);
        g.setColor(Color.black);
        g.drawString(message, myX, myY);
    }
    public void click(int x , int y)
    {

    }
    public boolean mouseOver(int x, int y)
    {
        return x>myX && x<(myX+SIZE) && y>myY && y<(myY+SIZE);
    }
    public void resetMessage()
    {
        message = "";
    }
    public void addMessage(String message)
    {
        this.message = this.message +"\n"+ message;
    }
}
