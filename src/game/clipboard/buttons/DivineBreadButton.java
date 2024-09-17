package game.clipboard.buttons;

import game.MoneyManager;
import game.clipboard.items.bread.BrownBread;
import game.clipboard.items.bread.DivineBread;

public class DivineBreadButton extends ResearchingButton{


    public DivineBreadButton(int x, int y) {
        super(x, y);
        myProduct = new DivineBread();
        price = 800;
        name = "divine bread";
    }



}