package game.clipboard.menus;

import game.clipboard.buttons.BuyingButton;
import game.clipboard.buttons.ScrollButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

abstract public class Menu {

    BuyingButton[] myButtons;
    ScrollButton[] scrollers;
    String title;
    final public int X_LEFT = 1270;
    int myY;
    private int Y_TOP = 400;// highest button
    private int Y_DISP =  200;// difference between meny
    int mySize;
    int slide;

    public Menu(int order)
    {
        myY = Y_TOP+Y_DISP*order;
        setButtonX();
        scrollers = new ScrollButton[]{new ScrollButton(true,myY),new ScrollButton(false,myY)};

        slide = 0;

    }

    public void render(Graphics g)
    {
        g.setColor(Color.black);
        g.drawString(title +" - " + slide, X_LEFT+ 200,myY - 40);

        for (int i = slide*3; i< slide*3 +3; i++)
        {
            if (i>= 0 && i< myButtons.length) {
                myButtons[i].render(g);
            }

        }
        for (ScrollButton s :scrollers)
        {
            s.render(g);
        }

    }
    public void setButtonX()
    {
        for (int i=0; i<mySize; i++)
        {
            int newX= X_LEFT + (i%3)*(BuyingButton.SIZE+20);
            myButtons[i].setX(newX);
        }
    }

    public void click (int x, int y)
    {
        if (y>myY && y < myY + BuyingButton.SIZE)
        {
            for (int i = slide*3; i< slide*3 +3; i++)
            {
                if (i>= 0 && i< myButtons.length) {
                    myButtons[i].mousePressed(x, y);
                }

            }
            if (scrollers[0].isMouseOver(x,y) && slide>0)
            {
                slide --;
            }
            if (scrollers[1].isMouseOver(x,y) && myButtons.length>=(slide+1)*3)
            {
                slide ++;
            }
        }

    }


}
