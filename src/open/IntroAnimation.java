package open;

import core.Images;
import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class IntroAnimation {

    static SpriteSheet mySprite;
    static int slide;

    public IntroAnimation()
    {
        mySprite = Images.TITLE_ANIM;
        slide = 0;
    }

    public static void render(Graphics g)
    {
        Image myImage = mySprite.getSubImage(0, slide);
        myImage = myImage.getScaledCopy(Main.getAdjustedX(myImage.getWidth()), Main.getAdjustedY(myImage.getHeight()));
        g.drawImage(myImage,0,0);
    }

    public static void changeSlide()
    {
        if (slide<4)
        {
            slide++;
        }
    }

    public boolean getDone(){ return slide == 4; }



}
