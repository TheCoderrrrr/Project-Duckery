package game.clipboard.items.weapon;

import core.Images;
import game.clipboard.items.Item;

public class Gun extends Item {
    public Gun()
    {
        timeToCreate = 100;
        name = "Pew Pew";
        myIndex = 3;
        roomImage = Images.ROOMS.getSubImage(3,0);
        warEffort = 10;
        isBasement = true;
    }

}
