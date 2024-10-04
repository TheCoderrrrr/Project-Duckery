package game.clipboard.buttons.ads;

import core.Images;

public class NewspaperAd extends AdvertisingButton{

    public NewspaperAd(int x, int y) {
        super(x, y);
        name = "News Ad";
        price = 100;
        seconds = 30;
        boost = 1;
        info = "+1/room \n30 seconds";
        image = Images.BUTTONS.getSubImage(3,0).getScaledCopy(SIZE,SIZE);
    }
}
