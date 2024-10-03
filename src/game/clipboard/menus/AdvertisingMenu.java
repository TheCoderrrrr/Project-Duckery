package game.clipboard.menus;

import game.clipboard.buttons.ads.AdvertisingButton;
import game.clipboard.buttons.ads.BillboardAd;
import game.clipboard.buttons.ads.NewspaperAd;
import game.clipboard.buttons.ads.TeleAd;

public class AdvertisingMenu extends Menu{

    public AdvertisingMenu()
    {
        super(1);
        title = "advertising";
        mySize = 3;
        myButtons.add(new NewspaperAd(X_LEFT, myY));
        myButtons.add(new BillboardAd(X_LEFT, myY));
        myButtons.add(new TeleAd(X_LEFT, myY));
        setButtonX();





    }

}
