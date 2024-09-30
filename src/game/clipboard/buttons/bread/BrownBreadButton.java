package game.clipboard.buttons.bread;

import game.clipboard.buttons.ResearchingButton;
import game.clipboard.items.bread.BrownBread;
import game.clipboard.menus.ResearchMenu;

public class BrownBreadButton extends ResearchingButton {


    public BrownBreadButton(int x, int y , ResearchMenu menu) {
        super(x, y, menu);
        myProduct = new BrownBread();
        price = 50;
        name = "brown bread";
    }




}
