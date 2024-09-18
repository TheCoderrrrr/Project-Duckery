package game.clipboard.buttons;

import game.MoneyManager;
import game.clipboard.items.bread.BrownBread;
import game.clipboard.items.bread.DivineBread;
import game.clipboard.menus.Menu;
import game.clipboard.menus.ResearchMenu;

public class DivineBreadButton extends ResearchingButton{


    public DivineBreadButton(int x, int y , ResearchMenu menu) {
        super(x, y, menu);
        myProduct = new DivineBread();
        price = 800;
        name = "divine bread";
    }



}