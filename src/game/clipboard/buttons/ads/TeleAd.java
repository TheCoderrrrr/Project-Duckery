package game.clipboard.buttons.ads;

import core.Images;

public class TeleAd extends AdvertisingButton{

    public TeleAd(int x, int y) {
        super(x, y);
        name = "Tele Ad";
        price = 600;
        seconds = 20;
        boost = 10;
        info = "+10/room\n20 seconds";
        image = Images.BUTTONS.getSubImage(5,0).getScaledCopy(SIZE,SIZE);
    }
}
