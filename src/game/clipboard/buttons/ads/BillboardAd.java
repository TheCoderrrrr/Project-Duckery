package game.clipboard.buttons.ads;

public class BillboardAd extends AdvertisingButton{
    public BillboardAd(int x, int y) {
        super(x, y);
        name = "Billboard Ad\n+5/room\n40 seconds";
        price = 300;
        seconds = 40;
        boost = 5;

    }
}
