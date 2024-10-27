package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class BrownBread extends Item {
    public BrownBread()
    {
        value = 3;
        timeToCreate = 5;
        name = "Brown Bread";
        myIndex = 1;
        iconImage = Images.BREAD_BUTTONS.getSubImage(1,0);
        roomImage = Images.ROOMS.getSubImage(1,0);
        isBasement = false;
        quotaValue = 15;
    }

}
