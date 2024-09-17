package game.clipboard.buttons;
import game.MoneyManager;

import game.clipboard.items.bread.CosmicBread;

public class CosmicBreadButton extends ResearchingButton{


    public CosmicBreadButton(int x, int y) {
        super(x, y);
        myProduct = new CosmicBread();
        price = 600;
        name = "cosmic bread";
    }




}
