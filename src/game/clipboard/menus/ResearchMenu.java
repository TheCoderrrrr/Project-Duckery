package game.clipboard.menus;

import game.clipboard.buttons.*;
import game.clipboard.buttons.ResearchingButton;
//import game.clipboard.buttons.bread.BlandBreadButton;
//import game.clipboard.buttons.bread.BrownBreadButton;
//import game.clipboard.buttons.bread.CosmicBreadButton;
//import game.clipboard.buttons.bread.DivineBreadButton;

public class ResearchMenu extends Menu {
//no longer used
    public ResearchMenu() {
        super(1);
        mySize = 4;

        title = "research";

//        myButtons.add(new BlandBreadButton(X_LEFT, myY, this));
//        myButtons.add(new BrownBreadButton(X_LEFT, myY, this));
//        myButtons.add(new CosmicBreadButton(X_LEFT, myY, this));
//        myButtons.add(new DivineBreadButton(X_LEFT, myY, this));

        setButtonX();

    }
    //public void makeButtons() {}

    public void click(int x, int y) {
        if (y > myY && y < myY + BuyingButton.SIZE) {
            for (int i = slide * 3; i < slide * 3 + 3; i++) {
                if (i >= 0 && i < myButtons.size()) {
                    myButtons.get(i).mousePressed(x, y);

                    setButtonX();
                }

            }
        }
    }

    public void removeItem(ResearchingButton r) {
        myButtons.remove(r);
    }
}

