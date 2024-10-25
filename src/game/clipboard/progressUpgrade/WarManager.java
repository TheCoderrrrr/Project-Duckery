package game.clipboard.progressUpgrade;

import game.entities.rooms.AdRoom;
import game.managers.ResourceManager;

public class WarManager extends UpgradeManager{
    public WarManager( ) {
        super(-1, "war");
        counter = new BreadCounter(X_LEFT+200, myY + 70);
        bar = new UpgradeBar(X_LEFT, myY, "war");
        button = new WarButton(X_LEFT+475, myY);
    }
    public void update()
    {
        counter.setCounter( ResourceManager.getGunsMade());
        bar.setPercent(1- AdRoom.getPercentDone());
    }
}
