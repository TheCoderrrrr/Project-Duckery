package game;

import core.Main;
import game.paydayButtons.PayDucks;
import game.paydayButtons.PayRoomTax;
import game.paydayButtons.PaydayButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class PayDay {
    public boolean timerRunning;
    public int elapseTime;
    public static int LENGTH_OF_WEEK = Main.FRAMES_PER_SECOND*5;
    private int numClicks = 0;
    private PaydayButton[] payButtons;
    private boolean updated;

    public PayDay() {
        timerRunning = true;
        payButtons = new PaydayButton[]{new PayDucks(), new PayRoomTax()};
    }

    public void render(Graphics g)
    {
        g.setColor(Color.black);
        g.drawString("Time: " + formatTime(elapseTime), 1697, 42);

        if (elapseTime%LENGTH_OF_WEEK == 0 && numClicks <1)
        {
            g.setColor(Color.red);
            g.fillRect(100, 100, Main.getScreenWidth()-200, Main.getScreenHeight()-200);
            g.setColor(Color.black);
            g.drawString("Payday\n"+getIsPaid(), Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
            timerRunning = false;
            World.pause();
            for(int i = 0; i< payButtons.length; i++){payButtons[i].render(g);}
        }
        if (elapseTime%LENGTH_OF_WEEK == 0 && numClicks == 2)
        {
            g.setColor(Color.white);
            g.fillRect(100, 100, Main.getScreenWidth()-200, Main.getScreenHeight()-200);
            g.setColor(Color.black);
            g.drawString("NEWS!!!!", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
        }
    }

    public void update(int delta) throws SlickException {
        if(timerRunning) {
            elapseTime ++;
        }
        if(!updated &&elapseTime%LENGTH_OF_WEEK == 0 && numClicks == 0 )
        {
            for(int i = 0; i< payButtons.length; i++){payButtons[i].calculatePrice();}
            updated = true;
        }


    }

    public String formatTime(int elapseTime)
    {
        int totalSeconds = elapseTime / 60;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public void mouseClicked(int x, int y)
    {

        if(!getIsPaid() && elapseTime%LENGTH_OF_WEEK == 0 && numClicks == 0) //checks that is in right state;
        {
            for(int i = 0; i< payButtons.length; i++){
                if (payButtons[i].mouseOver(x,y)) ;
                {
                    payButtons[i].click();
                }
            }
        }
        if(getIsPaid() && elapseTime%LENGTH_OF_WEEK == 0 && numClicks <=1)//switching slides
        {
            numClicks++;
        }
        else if (elapseTime%LENGTH_OF_WEEK == 0 && numClicks == 2 )
        {
            timerRunning = true;
            World.unpause();
            numClicks = 0;
            updated = false;
        }


    }

    private boolean getIsPaid()
    {
        boolean isPaid = true;
        for(int i = 0; i< payButtons.length; i++){
            if (!payButtons[i].isPaid())
            {
                isPaid = false;
            }
        }
        return isPaid;
    }


}