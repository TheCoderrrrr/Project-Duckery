package game.clipboard;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

abstract public class Button {
    protected int myX;
    protected int myY;
    protected int width;
    protected int height;
    protected Color color;
    protected String name;
    protected Image image;

    public Button(int x, int y, int width, int height, Color color, String name)
    {
        myX= x;
        myY = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.name = name;
    }
    public Button(int x, int y,  Image image, String name)
    {
        myX= x;
        myY = y;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.image = image;
        this.name = name;
    }

    public boolean mouseOver(int x, int y){
        return (x>myX && y>myY && x<(myX+width) && y <(myY+height));
    }

    public void render(Graphics g)
    {
        if (image == null)
        {

            g.setColor(color);
            g.fillRect(myX,myY,width,height);
            g.setColor(Color.black);
            g.drawString(name,myX,myY);

        }
        else {
            g.drawImage(image, myX, myY);
            g.setColor(Color.black);
        }

    }
    public void update(){}
    public void click(int x, int y)
    {
        if (mouseOver(x,y))
        {
            action();
        }
    }

    abstract protected void action();

}
