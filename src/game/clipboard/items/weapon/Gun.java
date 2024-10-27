package game.clipboard.items.weapon;

import core.Images;
import game.clipboard.items.Item;

public class Gun extends Item {
    public Gun()
    {
        timeToCreate = 6;
        name = "Gun";
        myIndex = 4;
        roomImage = Images.ROOMS.getSubImage(3,0);
        warEffort = 20;
        isBasement = true;
        value = -50;
        quotaValue = 250;
    }

}
