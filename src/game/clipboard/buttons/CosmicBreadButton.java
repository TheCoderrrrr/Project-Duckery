package game.clipboard.buttons;
import game.MoneyManager;

import game.clipboard.items.bread.CosmicBread;
import game.clipboard.menus.Menu;
import game.clipboard.menus.ResearchMenu;

public class CosmicBreadButton extends ResearchingButton{


    public CosmicBreadButton(int x, int y , ResearchMenu menu) {
        super(x, y, menu);
        myProduct = new CosmicBread();
        price = 600;
        name = "cosmic bread";
    }




}
