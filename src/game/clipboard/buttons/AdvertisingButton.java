package game.clipboard.buttons;

import game.MoneyManager;
import org.newdawn.slick.Color;

public class AdvertisingButton extends BuyingButton {

    int seconds;
    public AdvertisingButton(int x, int y) {
        super(x, y);
        color = Color.blue;
        name = "Ad -  10 second boost";
        seconds = 10;
        price = 30;
    }


    @Override
    public void onClick() {
        super.onClick();
        if (MoneyManager.getFunds()>price)
        {
            MoneyManager.advertise(seconds * 60);
            MoneyManager.withdraw(price);
        }
    }
}
