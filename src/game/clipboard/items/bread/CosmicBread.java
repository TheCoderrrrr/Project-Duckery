package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class CosmicBread extends Item {
    public CosmicBread()
    {
        value = 100;
        timeToCreate = 30;
        name = "Cosmic Bread";
        myIndex = 2;
        roomImage = Images.ROOMS.getSubImage(2,0);
        isBasement = false;
        price = 200;
    }
}
