package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {
    public static Image DUC1;

    public static void loadImages()
    {
        try
        {
            DUC1 = new Image("res/duck/duck_one.png");
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
