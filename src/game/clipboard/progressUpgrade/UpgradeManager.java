package game.clipboard.progressUpgrade;

import org.newdawn.slick.Graphics;

public class UpgradeManager {
    protected BreadCounter counter;
    protected UpgradeBar bar;
    protected UpgradeButton button;
    protected final int X_LEFT = 1170;
    protected int myY;
    protected final int Y_TOP = 480;
    protected final int Y_DISP = 160;

    public UpgradeManager(int order, String name)
    {
        myY = Y_TOP + Y_DISP * order;
        counter = new BreadCounter(X_LEFT+200, myY + 70);
        bar = new UpgradeBar(X_LEFT, myY, name);
        button = new UpgradeButton(X_LEFT + 475, myY, bar, counter);
    }

    public void update()
    {
        button.resetMessage();
//        button.addMessage(bar.getMessage());
//        button.addMessage(counter.getMessage());
        button.update();
    }
    public void render(Graphics g)
    {

        bar.render(g);
        counter.render(g);
        button.render(g);
    }

    public void click(int x, int y)
    {
        if (counter.getComplete() && bar.getComplete())
        {
            button.click(x,y);
        }
    }

    public void addToBar()
    {
        bar.addCounter();
    }
    public void addToCount()
    {
        counter.addCounter();
    }
    public void setBar(int total)
    {
        bar.setTotal(total);
    }
    public void setCount(int total)
    {
        counter.setTotal(total);
    }
    public void setBarPercent(float percent)
    {
        bar.setPercent(percent);
    }
}
