package buttons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class Button {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Color color;

    public Button(int x, int y){
        this.x = x;
        this.y = y;
        w = 100;
        h = 100;
    }
    public Button(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = w;
    }
    public Button(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    protected boolean isMouseOver(int mX, int mY)
    {
        return (mX > x && mX < x + w && mY > y && mY < y + h);
    }
    public void mousePressed(int mX, int mY)
    {
        if (isMouseOver(mX, mY))
        {
            onClick();
        }
    }

    public abstract void onClick();

}
