package open;

import core.Images;
import org.newdawn.slick.Graphics;

public class TitleScreen {

    int timer;

    public TitleScreen() {
        int timer = 0;
    }

    public void update()
    {
        timer++;
    }

    public void render(Graphics g)
    {
        g.drawImage(Images.TITLE_SCREEN.getSubImage(0,0), 0,0);
    }
}
