package game.clipboard.menus;

import game.clipboard.buttons.GoToButton;
import game.clipboard.buttons.HiringButton;

public class HotkeyMenu extends Menu {
    public HotkeyMenu()
    {
        super(2);
        mySize = 1;
        title = "Go To";

        myButtons.add( new GoToButton(0, myY, 'T'));
        myButtons.add( new GoToButton(0, myY, 'R'));
        myButtons.add( new GoToButton(0, myY, 'B'));


        setButtonX();

    }
}
