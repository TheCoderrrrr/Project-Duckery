package game.clipboard.progressUpgrade.ad;

import game.building.rooms.AdRoom;
import game.clipboard.progressUpgrade.UpgradeManager;
import game.managers.ResourceManager;
import org.newdawn.slick.Color;

public class AdManager extends UpgradeManager {
    public AdManager() {
        super(2, "advertisements!");
        bar.setColor(Color.magenta);
        button = new AdButton(X_LEFT+475, myY, bar, counter);
    }
    public void update()
    {
        super.update();
        counter.setCounter((int) ResourceManager.getBreadMade());
        bar.setPercent(1- AdRoom.getPercentDone());
    }

}
