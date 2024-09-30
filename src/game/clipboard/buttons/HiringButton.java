package game.clipboard.buttons;

import game.World;
import org.newdawn.slick.Color;
import game.ResourceManager;
import org.newdawn.slick.SlickException;
import game.paydayButtons.PayDucks;

public class HiringButton extends BuyingButton {

    public HiringButton(int x, int y) {
        super(x, y);
        color = Color.magenta;
        name = "hire\ncosts "+PayDucks.DUCK_WAGE+"/day";
        price = 10;

    }

    @Override
    public void onClick() {
        if (ResourceManager.getFunds()>= price)
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
