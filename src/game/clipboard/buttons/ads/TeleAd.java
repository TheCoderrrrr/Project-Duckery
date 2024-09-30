package game.clipboard.buttons.ads;

public class TeleAd extends AdvertisingButton{

    public TeleAd(int x, int y) {
        super(x, y);
        name = "Tele Ad\n+10/room\n20 seconds";
        price = 600;
        seconds = 20;
        boost = 10;
    }
}
