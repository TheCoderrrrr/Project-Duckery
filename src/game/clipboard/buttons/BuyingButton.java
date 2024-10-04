package game.clipboard.buttons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class BuyingButton {
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected Color color;
    public static final int SIZE= 100;
    protected String name;
    protected int price;
    protected Image image;
    protected String info;

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
        if (image == null)
        {
            g.setColor(color);
            g.fillRect(x, y, w, h);

        }
        else {
            g.drawImage(image.getScaledCopy(w,h), x,y);
        }
    }

    public boolean isMouseOver(int mX, int mY)
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
    public String getInfo()
    {
        String ret = name+"\nPrice: "+price+"\n"+info;
        return ret;
    }

    protected abstract void onClick();

}
