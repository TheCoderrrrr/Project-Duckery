package game.clipboard.menus;

import game.clipboard.buttons.Button;
import game.clipboard.buttons.HiringButton;
import game.clipboard.buttons.ResearchingButton;

public class ResearchMenu extends Menu{

    public ResearchMenu()
    {
        super(1);
        mySize = 1;

        myButtons = new Button[mySize];
        myButtons[0] = new ResearchingButton(X_LEFT, myY);

        setButtonX();

    }
    //public void makeButtons() {}
}
