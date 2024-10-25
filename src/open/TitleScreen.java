package open;

import core.Images;
import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class TitleScreen {

    int timer;
    Image myImage;

    public TitleScreen() {
        int timer = 0;
        myImage = Images.TITLE_SCREEN.getSubImage(0,0);
        myImage = myImage.getScaledCopy(Main.getAdjustedX(myImage.getWidth()), Main.getAdjustedY(myImage.getHeight()));
    }

    public void update()
    {
        timer++;
    }

    public void render(Graphics g)
    {
        g.drawImage(myImage, 0,0);
    }
}
