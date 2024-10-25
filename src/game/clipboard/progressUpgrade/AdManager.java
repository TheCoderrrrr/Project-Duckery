package game.clipboard.progressUpgrade;

import game.entities.rooms.AdRoom;
import game.entities.rooms.ResearchFloor;
import game.managers.ResourceManager;

public class AdManager extends UpgradeManager{
    public AdManager() {
        super(2, "advertisements!");

    }
    public void update()
    {
        counter.setCounter((int) ResourceManager.getBreadMade());
        bar.setPercent(1- AdRoom.getPercentDone());
    }

}
