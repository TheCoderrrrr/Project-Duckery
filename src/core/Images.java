package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {
    public static Image DUC1;
    public static SpriteSheet DUC2;
    public static SpriteSheet ROOMS;
    public static SpriteSheet BUILD_ANIMATION;

    public static void loadImages()
    {
        try
        {
            DUC1 = new Image("res/duck/duck_one.png");
            DUC2 = new SpriteSheet((new Image("res/duck/animated_duck.png")).getScaledCopy(448,64),64,64);
            DUC1 = DUC2.getSubImage(0,0).getScaledCopy(64,64);
            ROOMS = new SpriteSheet(new Image("res/floors/rooms.png").getScaledCopy(1600,200),400,200);
            BUILD_ANIMATION = new SpriteSheet(new Image("res/floors/buildRoom.png").getScaledCopy(800,200),400,200);
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
