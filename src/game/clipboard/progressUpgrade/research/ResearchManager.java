package game.clipboard.progressUpgrade.research;

import game.building.rooms.ResearchFloor;
import game.clipboard.progressUpgrade.UpgradeManager;
import game.managers.ResourceManager;
import org.newdawn.slick.Color;

public class ResearchManager extends UpgradeManager {

    public ResearchManager() {
        super(2, "research timer");
        button = new ResearchButton(X_LEFT+475, myY, bar, counter);
        bar.setColor(Color.blue);
        bar.setPercent(1-ResearchFloor.getPercentDone());
    }
    public void update()
    {
        super.update();
        bar.setPercent(1-ResearchFloor.getPercentDone());

        if (ResearchFloor.getFirstProuduct()!=null)
        {
            counter.setCounter((int)ResourceManager.getBreadMade());
            counter.setTotal(ResearchFloor.getFirstProuduct().getQuotaValue());
            counter.setTotal(ResearchFloor.getFirstProuduct().getQuotaValue());
        }

    }
}
