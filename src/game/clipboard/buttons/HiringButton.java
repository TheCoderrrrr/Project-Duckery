package game.clipboard.buttons;

import game.World;
import org.newdawn.slick.Color;
import game.MoneyManager;
import org.newdawn.slick.SlickException;

public class HiringButton extends BuyingButton {

    public HiringButton(int x, int y) {
        super(x, y);
        color = Color.magenta;
        name = "hire";
        price = 10;

    }

    @Override
    public void onClick() {
        super.onClick();

        if (MoneyManager.getFunds()>= price)
        {
            try {
                World.increaseDuckLimit();
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
            MoneyManager.withdraw(price);
        }

    }


}
