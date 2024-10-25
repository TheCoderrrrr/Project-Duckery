package game.clipboard.progressUpgrade;

import game.entities.rooms.ResearchFloor;
import game.managers.ResourceManager;

public class ResearchManager extends UpgradeManager{

    public ResearchManager() {
        super(1, "research timer");
        button = new ResearchButton(X_LEFT+475, myY);

    }
    public void update()
    {
        counter.setCounter((int)ResourceManager.getBreadMade());
        bar.setPercent(1-ResearchFloor.getPercentDone());
        counter.setTotal(ResearchFloor.getFirstProuduct().getQuotaValue());
    }
}
