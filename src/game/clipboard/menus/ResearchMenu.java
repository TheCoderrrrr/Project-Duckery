package game.clipboard.menus;

import game.clipboard.buttons.*;

public class ResearchMenu extends Menu{

    public ResearchMenu()
    {
        super(1);
        mySize = 4;

        title = "research";

        myButtons = new BuyingButton[mySize];
        myButtons[0] = new BlandBreadButton(X_LEFT, myY);
        myButtons[1] = new BrownBreadButton(X_LEFT, myY);
        myButtons[2] = new CosmicBreadButton(X_LEFT, myY);
        myButtons[3] = new DivineBreadButton(X_LEFT, myY);

        setButtonX();

    }
    //public void makeButtons() {}
}
