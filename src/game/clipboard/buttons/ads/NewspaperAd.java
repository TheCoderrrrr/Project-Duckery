package game.clipboard.buttons.ads;

public class NewspaperAd extends AdvertisingButton{

    public NewspaperAd(int x, int y) {
        super(x, y);
        name = "News Ad\n+1/room\n30 seconds";
        price = 100;
        seconds = 30;
        boost = 1;

    }
}
