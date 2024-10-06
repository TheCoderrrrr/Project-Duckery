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
    int price;
    boolean paid;
    Color myColor;

    public PaydayButton()
    {
        myColor = Color.white;
        index = indexCounter;
        indexCounter++;
        myX = 1000;
        myY = 150+HEIGHT*2*index;

    }


    public void render(Graphics g)
    {
        if(paid)
        {
            myColor = Color.green;
        }
        else {
            myColor = Color.white;
        }
        g.setColor(myColor);
        g.fillRect(myX,myY, WIDTH, HEIGHT);
        g.setColor(Color.black);

        if(!paid)
        {
            g.drawString(name+"\n$"+price+"\nClick to pay", myX, myY);
        }
        else
        {
            g.drawString(name+"\npaid", myX, myY);
        }


    }

    public boolean mouseOver(int x, int y)
    {
        return (x > myX && x < (myX + WIDTH) && y > (myY) && y < (myY + HEIGHT));
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
