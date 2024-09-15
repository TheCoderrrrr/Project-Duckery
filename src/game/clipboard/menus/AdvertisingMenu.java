package game.clipboard.menus;

import game.MoneyManager;
import game.clipboard.buttons.AdvertisingButton;
import game.clipboard.buttons.BuyingButton;
import game.clipboard.buttons.HiringButton;

public class AdvertisingMenu extends Menu{

    public AdvertisingMenu()
    {
        super(2);
        title = "advertising";
        mySize = 1;
        myButtons = new BuyingButton[mySize];
        myButtons[0] = new AdvertisingButton(X_LEFT, myY);
        setButtonX();





    }

}
