package game.clipboard.buttons;

import game.MoneyManager;
import game.clipboard.items.bread.BlandBread;
import game.clipboard.items.bread.BrownBread;
import game.clipboard.menus.Menu;
import game.clipboard.menus.ResearchMenu;

public class BlandBreadButton extends ResearchingButton{


    public BlandBreadButton(int x, int y , ResearchMenu menu) {
        super(x, y, menu);
        myProduct = new BlandBread();
        price = 50;
        name = "bland bread";
    }



}
