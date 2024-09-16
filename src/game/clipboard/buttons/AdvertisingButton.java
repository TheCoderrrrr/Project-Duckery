package game.clipboard.buttons;

import game.MoneyManager;
import org.newdawn.slick.Color;

public class AdvertisingButton extends BuyingButton {

    public AdvertisingButton(int x, int y) {
        super(x, y);
        color = Color.blue;
        name = "Ad";

        price = 30;
    }


    @Override
    public void onClick() {
        super.onClick();
        if (MoneyManager.getFunds()>price)
        {
            MoneyManager.advertise(1);
            MoneyManager.withdraw(price);
        }
    }
}
