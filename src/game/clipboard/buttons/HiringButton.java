package game.clipboard.buttons;

import core.Images;
import game.RoomManager;
import game.World;
import org.newdawn.slick.Color;
import game.managers.ResourceManager;
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
