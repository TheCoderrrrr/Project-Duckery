package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class CosmicBread extends Item {
    public CosmicBread()
    {
        value = 25;
        timeToCreate = 15;
        name = "Cosmic Bread";
        myIndex = 2;
        roomImage = Images.ROOMS.getSubImage(2,0);
        isBasement = false;
    }
}
