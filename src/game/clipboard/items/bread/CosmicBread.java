package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class CosmicBread extends Item {
    public CosmicBread()
    {
        value = 14;
        timeToCreate = 20;
        name = "Cosmic Bread";
        myIndex = 2;
        roomImage = Images.ROOMS.getSubImage(2,0);
        iconImage = Images.BREAD_BUTTONS.getSubImage(2,0);
        isBasement = false;
        quotaValue = 80;
    }
}
