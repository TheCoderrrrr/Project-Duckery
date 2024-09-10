package game.clipboard.menus;

import game.clipboard.buttons.AdvertisingButton;
import game.clipboard.buttons.Button;
import game.clipboard.buttons.HiringButton;

public class AdvertisingMenu extends Menu{

    public AdvertisingMenu()
    {
        super(2);
        mySize = 2;
        myButtons = new Button[mySize];
        myButtons[0] = new AdvertisingButton(X_LEFT, myY);
        myButtons[1] = new HiringButton(X_LEFT, myY);
        setButtonX();


    }

}
