package game.clipboard.menus;

import game.clipboard.buttons.HiringButton;

public class HiringMenu extends Menu{

    public HiringMenu()
    {
        super(0);
        mySize = 1;
        title = "hire menu";

        myButtons.add( new HiringButton(X_LEFT, myY));

        setButtonX();

    }
    //public void makeButtons() {}
}
