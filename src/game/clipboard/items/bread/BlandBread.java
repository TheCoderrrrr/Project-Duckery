package game.clipboard.items.bread;

import core.Images;
import game.clipboard.items.Item;

public class BlandBread extends Item {

    public BlandBread()
    {
        myIndex = 0;
        value = 5;
        timeToCreate = 3;
        name = "Bland Bread";
        roomImage = Images.ROOMS.getSubImage(0,0);
    }
}
