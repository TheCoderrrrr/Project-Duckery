package game.clipboard.buttons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class BuyingButton {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Color color;
    public static final int SIZE= 100;
    protected String name;
    protected int price;

    public BuyingButton(int x, int y){
        this.x = x;
        this.y = y;
        w = SIZE;
        h = SIZE;
    }
    public BuyingButton(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = w;
    }
    public BuyingButton(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setX(int newX)
    {
        x = newX;
    }

    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x, y, w, h);

        g.setColor(Color.black);
        g.drawString("Item: "+name+"\nPrice: "+price, x, y);
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

    protected void onClick()
    {
    }

}
