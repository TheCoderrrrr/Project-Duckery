package game.clipboard.menus;

import game.clipboard.buttons.BuyingButton;
import game.clipboard.buttons.ResearchingButton;

public class ResearchMenu extends Menu{

    public ResearchMenu()
    {
        super(1);
        mySize = 1;

        title = "research";

        myButtons = new BuyingButton[mySize];
        myButtons[0] = new ResearchingButton(X_LEFT, myY);

        setButtonX();

    }
    //public void makeButtons() {}
}
