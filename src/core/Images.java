package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {
    public static Image DUC1;
    public static SpriteSheet DUC2;
    public static SpriteSheet ROOMS;
    public static SpriteSheet FLOORS;
    public static SpriteSheet BUILD_ANIMATION;
    public static SpriteSheet BREAD_BUTTONS;


    public static SpriteSheet TITLE_SCREEN;

    public static Image ADD_DUCK;
    public static Image FIRE_DUCK;
    public static Image CLIPBOARD;



    public static void loadImages()
    {
        try
        {
            DUC1 = new Image("res/duck/duck_one.png");
            DUC2 = new SpriteSheet((new Image("res/duck/animated_duck.png")).getScaledCopy(448,64),64,64);
            DUC1 = DUC2.getSubImage(0,0).getScaledCopy(64,64);
            ROOMS = new SpriteSheet(new Image("res/floors/rooms.png").getScaledCopy(1600,200),400,200);
            FLOORS = new SpriteSheet(new Image("res/floors/floors.png").getScaledCopy(4800,200),800,200);
            BUILD_ANIMATION = new SpriteSheet(new Image("res/floors/build_room.png").getScaledCopy(800,200),400,200);
            BREAD_BUTTONS = new SpriteSheet(new Image("res/floors/bread_buttons.png"), 48,48);

            TITLE_SCREEN = new SpriteSheet(new Image("res/Duckery.png"),1920, 1080);
            ADD_DUCK = new Image("res/clipboard/add_duck.png");


        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
