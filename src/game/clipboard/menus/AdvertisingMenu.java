package game.clipboard.menus;

import game.clipboard.buttons.AdvertisingButton;

public class AdvertisingMenu extends Menu{

    public AdvertisingMenu()
    {
        super(2);
        title = "advertising";
        mySize = 1;
        myButtons.add(new AdvertisingButton(X_LEFT, myY));
        setButtonX();





    }

}
