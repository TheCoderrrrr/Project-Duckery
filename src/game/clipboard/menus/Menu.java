package game.clipboard.menus;

import game.clipboard.buttons.AdvertisingButton;
import game.clipboard.buttons.Button;
import org.newdawn.slick.Graphics;

abstract public class Menu {

    Button[] myButtons;
    String title;
    final public int X_LEFT = 1150;
    int myY;
    private int Y_TOP = 400;// highest button
    private int Y_DISP =  200;// difference between meny
    int mySize;

    public Menu(int order)
    {
        myY = Y_TOP+Y_DISP*order;
        setButtonX();


    }

    public void render(Graphics g)
    {
        for (Button b :myButtons)
        {
            b.render(g);
        }
    }
    public void setButtonX()
    {
        for (int i=0; i<mySize; i++)
        {
            int newX= X_LEFT + i*(Button.SIZE+20);
            myButtons[i].setX(newX);
        }
    }

    public void click (int x, int y)
    {
        if (y>myY && y < myY + Button.SIZE)
        {
            for (Button b:myButtons)
            {
                b.mousePressed(x,y);
            }
        }
    }


}
