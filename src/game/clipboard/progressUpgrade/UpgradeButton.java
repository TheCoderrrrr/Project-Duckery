package game.clipboard.progressUpgrade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class UpgradeButton {
    int myX;
    int myY;
    final int SIZE = 100;
    String message;
    Color myColor;
    //individual upgraders;
    public UpgradeButton(int x, int y)
    {
        myX = x;
        myY = y;
        myColor = Color.green;
        message = "";
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
        if (mouseOver(x,y))
        {
            myColor = Color.red;
        }

    }
    public boolean mouseOver(int x, int y)
    {
        return x>myX && x<(myX+SIZE) && x>myY && y<(myY+SIZE);
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
