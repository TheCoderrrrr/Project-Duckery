package game.clipboard.menus;

import game.clipboard.buttons.AdvertisingButton;
import game.clipboard.buttons.Button;
import game.clipboard.buttons.HiringButton;
import game.clipboard.buttons.ResearchingButton;

public class HiringMenu extends Menu{

    public HiringMenu()
    {
        super(0);
        mySize = 3;
        myButtons = new Button[mySize];
        myButtons[0] = new HiringButton(X_LEFT, myY);
        myButtons[1] = new ResearchingButton(X_LEFT,myY);
        myButtons[2] = new AdvertisingButton(X_LEFT,myY);
        setButtonX();

    }
    //public void makeButtons() {}
}
