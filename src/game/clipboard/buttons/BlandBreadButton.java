package game.clipboard.buttons;

import game.MoneyManager;
import game.clipboard.items.bread.BlandBread;
import game.clipboard.items.bread.BrownBread;

public class BlandBreadButton extends ResearchingButton{


    public BlandBreadButton(int x, int y) {
        super(x, y);
        myProduct = new BlandBread();
        price = 50;
        name = "bland bread";
    }



}
