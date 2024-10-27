package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class BlandBread extends Item {

    public BlandBread()
    {
        myIndex = 0;
        value = 5;
        timeToCreate = 10;
        name = "Bland \nBread";
        roomImage = Images.ROOMS.getSubImage(0,0);
        iconImage = Images.BREAD_BUTTONS.getSubImage(0,0);
        isBasement = false;
        quotaValue = 0;
    }
}
