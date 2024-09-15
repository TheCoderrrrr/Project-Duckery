package game.clipboard.buttons;

import org.newdawn.slick.Color;

public class AdvertisingButton extends BuyingButton {

    public AdvertisingButton(int x, int y) {
        super(x, y);
        color = Color.blue;
        name = "Ad";

        price = 200;
    }


    @Override
    public void onClick() {
        super.onClick();
        System.out.println("Advertising!!");
    }
}
