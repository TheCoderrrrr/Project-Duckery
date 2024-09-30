package game.paydayButtons;

import game.ResourceManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

abstract public class PaydayButton {
    String name;
    static final int WIDTH = 300;
    static final int HEIGHT = 150;
    protected static int indexCounter;
    protected static int index;
    protected static int myX;
    protected int myY;
    protected int price;
    protected boolean paid;

    public PaydayButton()
    {
        index = indexCounter;
        indexCounter++;
        myX = 1000;
        myY = 150+HEIGHT*2*index;

    }


    public void render(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRect(myX,myY, WIDTH, HEIGHT);
        g.setColor(Color.black);
        g.drawString("Pay "+name+"\n$ "+price+"\npaid? "+paid, myX, myY);


    }

    public boolean mouseOver(int x, int y)
    {
        if (x>myX && x < myX+WIDTH && y>myY && y<myY+HEIGHT)
        {
            return true;
        }
        return false;

    }
    public boolean isPaid(){return paid;}
    public void calculatePrice()
    {
        paid = false;
    }

    public void click()
    {
        if (!paid)
        {
            ResourceManager.tax(price);
            paid = true;
        }

    }




}