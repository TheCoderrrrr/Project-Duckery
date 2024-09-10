package game.clipboard.buttons;

import org.newdawn.slick.Color;

public class ResearchingButton extends Button{

    public ResearchingButton(int x, int y) {
        super(x, y);
        color = Color.white;

        name = "research";
        price = 20;
    }


    @Override
    public void onClick() {
        super.onClick();
        System.out.println("Researching products..");
    }
}
