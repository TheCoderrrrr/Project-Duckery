package game.clipboard;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ProgressBar {
    protected int myX;
    protected int myY;
    protected  int height;
    protected int width;
    protected double percent;
    protected String title;

    public ProgressBar(int x, int y, int h, int w, String title)
    {
        this.title =title;
        myX = x;
        myY = y;
        height = h;
        width = w;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.black);
        g.drawString(title, myX, myY- 25);
        g.fillRect(myX, myY, width, height);
        g.setColor(Color.yellow);
        g.fillRoundRect(myX+5, myY+(int)(height*(1.0-percent))+5, width-10,(int) (height*percent)-10, 10);
    }

    public void update(double percent)
    {
        this.percent = percent;
    }

}
