package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class DivineBread extends Item {
    public DivineBread()
    {
        value = 19;
        timeToCreate = 24;
        name = "Divine Bread";
        myIndex = 3;
        roomImage = Images.ROOMS.getSubImage(3,0);
        iconImage = Images.BREAD_BUTTONS.getSubImage(3,0);
        isBasement = false;
        quotaValue = 150;
    }
}
