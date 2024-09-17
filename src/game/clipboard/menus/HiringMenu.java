package game.clipboard.menus;

import game.clipboard.buttons.AdvertisingButton;
import game.clipboard.buttons.BuyingButton;
import game.clipboard.buttons.HiringButton;
import game.clipboard.buttons.ResearchingButton;

public class HiringMenu extends Menu{

    public HiringMenu()
    {
        super(0);
        mySize = 1;
        title = "hire menu";
        myButtons = new BuyingButton[mySize];
        myButtons[0] = new HiringButton(X_LEFT, myY);

        setButtonX();

    }
    //public void makeButtons() {}
}
