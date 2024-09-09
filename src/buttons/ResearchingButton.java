package buttons;

import org.newdawn.slick.Color;

public class ResearchingButton extends Button{

    public ResearchingButton(int x, int y) {
        super(x, y);
        color = Color.white;
    }


    @Override
    public void onClick() {
        System.out.println("Researching products..");
    }
}
