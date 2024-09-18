package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class DivineBread extends Item {
    public DivineBread()
    {
        value = 54;
        timeToCreate = 100;
        name = "Divine Bread";
        myIndex = 3;
        roomImage = Images.ROOMS.getSubImage(3,0);
    }
}
