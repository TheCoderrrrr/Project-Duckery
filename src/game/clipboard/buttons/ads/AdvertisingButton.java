package game.clipboard.buttons.ads;

import game.managers.ResourceManager;
import game.clipboard.buttons.BuyingButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class AdvertisingButton extends BuyingButton {

    int seconds;
    int boost;
    static int timer;
    private static int COOLDOWN_TIME = 800;
    private static Color adColor;
    public AdvertisingButton(int x, int y) {
        super(x, y);
        color = Color.blue;
        name = "Ad -  10 second boost";
        seconds = 10;
        price = 30;
        boost = 10;
    }

    public static void update()
    {
        if (timer>0)
        {
            timer--;
            int r = (int)((float)(COOLDOWN_TIME - timer)/COOLDOWN_TIME * 255);
            adColor = new Color(255-r, 255-r, 255);
        }
        if (timer == 0)
        {
            adColor = Color.blue;
        }
    }
    public void render(Graphics g)
    {

        if (image == null)
        {
            g.setColor(adColor);
            g.fillRect(x, y, w, h);

        }
        else {

            g.drawImage(image.getScaledCopy(w,h), x,y);
            g.setColor(new Color(255,255,255, 255-(int)((float)(COOLDOWN_TIME - timer)/COOLDOWN_TIME * 255)));
            g.fillRect(x,y,w,h);

        }
    }


    @Override
    public void onClick() {
        if (timer <= 0)
        {
            if (ResourceManager.getFunds()>price)
            {
                timer = COOLDOWN_TIME;
                ResourceManager.advertise(seconds * 60, boost);
                ResourceManager.withdraw(price);
            }
        }

    }
}
