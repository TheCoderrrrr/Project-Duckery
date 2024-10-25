package game.clipboard.progressUpgrade;

import game.entities.rooms.AdRoom;
import game.entities.rooms.ResearchFloor;
import game.managers.ResourceManager;

public class AdManager extends UpgradeManager{
    public AdManager() {
        super(2, "advertisements!");

        button = new AdButton(X_LEFT+475, myY);
    }
    public void update()
    {
        counter.setCounter((int) ResourceManager.getBreadMade());
        bar.setPercent(1- AdRoom.getPercentDone());
    }

}
