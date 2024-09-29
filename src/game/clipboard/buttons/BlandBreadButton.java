package game.clipboard.buttons;

import game.clipboard.items.bread.BlandBread;
import game.clipboard.menus.ResearchMenu;

public class BlandBreadButton extends ResearchingButton{


    public BlandBreadButton(int x, int y , ResearchMenu menu) {
        super(x, y, menu);
        myProduct = new BlandBread();
        price = 50;
        name = "bland bread";
    }



}
