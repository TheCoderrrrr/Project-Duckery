package game.clipboard.buttons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import game.clipboard.menus.Menu;

public class ScrollButton {
    private final int X_LEFT = 1125;
    private  final int X_RIGHT = 1125 + 120*5;
    int x;
    int y;
    int w;
    int h;
    boolean isLeft;

    Color myColor;


    public ScrollButton(boolean isLeft, int y)
    {
        this.y = y;
        w = 50;
        h = 50;
        this.isLeft = isLeft;
        if (isLeft)
        {
            myColor = Color.blue;
            x = X_LEFT;
        }
        else {
            myColor = Color.red;
            x = X_RIGHT;
        }
    }

    public void render (Graphics g)
    {
        g.setColor(myColor);
        g.fillRect(x, y, w,h);
    }
    public boolean isMouseOver(int mX, int mY)
    {
        return (mX > x && mX < x + w && mY > y && mY < y + h);
    }
}
