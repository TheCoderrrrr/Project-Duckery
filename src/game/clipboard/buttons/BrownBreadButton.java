package game.clipboard.buttons;

import game.clipboard.items.bread.BrownBread;
import game.clipboard.menus.ResearchMenu;

public class BrownBreadButton extends ResearchingButton{


    public BrownBreadButton(int x, int y , ResearchMenu menu) {
        super(x, y, menu);
        myProduct = new BrownBread();
        price = 50;
        name = "brown bread";
    }




}
