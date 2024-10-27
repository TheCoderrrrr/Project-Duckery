package game.clipboard.buttons;

import core.Images;
import game.RoomManager;
import game.World;
import org.newdawn.slick.Color;
import game.managers.ResourceManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import game.paydayButtons.PayDucks;

public class HiringButton extends BuyingButton {

    public HiringButton(int x, int y) {
        super(x, y);
        color = Color.magenta;
        name = "hire duck";
        info = "costs "+PayDucks.DUCK_WAGE+"/day";
        price = 10;
        image = Images.ADD_DUCK;

    }

    public void render(Graphics g)
    {
        super.render(g);
        if (World.getDuckLimit() + 1 > RoomManager.getRoomAvailability() && price<=ResourceManager.getFunds())
        {
            g.setColor(new Color(0f,0f,0f,.3f));
            g.fillRect(x,y,w,h+20);
        }

        if (price<= ResourceManager.getFunds()  ||  World.getDuckLimit() + 1 <= RoomManager.getRoomAvailability())
        {
            g.setColor(Color.green);
        }
        else {
            g.setColor(Color.red);
        }
        g.drawString("price: "+price, x,y +image.getHeight());
    }

    @Override
    public void onClick() {
        if (ResourceManager.getFunds()>= price && World.getDuckLimit() + 1 <= RoomManager.getRoomAvailability())
        {
            try {
                World.increaseDuckLimit();
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
            ResourceManager.withdraw(price);
        }

    }
}
