package open;

import core.Images;
import org.newdawn.slick.Graphics;
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
        g.drawImage(mySprite.getSubImage(0,slide),0,0);
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
