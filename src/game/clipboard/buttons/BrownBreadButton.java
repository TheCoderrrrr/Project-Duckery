package game.clipboard.buttons;

import game.MoneyManager;
import game.clipboard.items.bread.BrownBread;

public class BrownBreadButton extends ResearchingButton{


    public BrownBreadButton(int x, int y) {
        super(x, y);
        myProduct = new BrownBread();
        price = 50;
        name = "brown bread";
    }




}
