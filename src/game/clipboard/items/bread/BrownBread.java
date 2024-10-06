package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class BrownBread extends Item {
    public BrownBread()
    {
        value = 30;
        timeToCreate = 20;
        name = "Brown Bread";
        myIndex = 1;
        roomImage = Images.ROOMS.getSubImage(1,0);
        isBasement = false;
        price = 70;
    }

}
