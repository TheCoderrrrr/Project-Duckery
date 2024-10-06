package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class DivineBread extends Item {
    public DivineBread()
    {
        value = 1000;
        timeToCreate = 150;
        name = "Divine Bread";
        myIndex = 3;
        roomImage = Images.ROOMS.getSubImage(3,0);
        isBasement = false;
        price = 1000;
    }
}
