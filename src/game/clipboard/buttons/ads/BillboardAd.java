package game.clipboard.buttons.ads;

import core.Images;

public class BillboardAd extends AdvertisingButton{
    public BillboardAd(int x, int y) {
        super(x, y);
        name = "Magazine Ad";
        price = 300;
        seconds = 40;
        boost = 5;
        info = "+5/room\n40 seconds";
        image = Images.BUTTONS.getSubImage(4,0).getScaledCopy(SIZE,SIZE);

    }
}
