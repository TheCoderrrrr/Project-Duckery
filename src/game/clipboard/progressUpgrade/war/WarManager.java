package game.clipboard.progressUpgrade.war;

import game.building.rooms.AdRoom;
import game.clipboard.progressUpgrade.BreadCounter;
import game.clipboard.progressUpgrade.UpgradeBar;
import game.clipboard.progressUpgrade.UpgradeManager;
import game.managers.ResourceManager;
import org.newdawn.slick.Color;

public class WarManager extends UpgradeManager {
    int minimum;
    int maximum;
    public WarManager( ) {
        super(2, "war");
        minimum = 20;
        maximum = 50;
        counter = new BreadCounter(X_LEFT+200, myY + 70);
        counter.setTotal(minimum);
        counter.setName("guns");
        bar = new WeaponsBar(X_LEFT, myY, "war");
        bar.setTotal(maximum);
        bar.setColor(Color.red);
        button = new WarButton(X_LEFT+475, myY, bar, counter);
    }
    public void update()
    {
        super.update();
        counter.setCounter( ResourceManager.getGunsMade());
        bar.setCounter(ResourceManager.getGunsMade());
        //bar.setPercent(1- AdRoom.getPercentDone());
    }
}
