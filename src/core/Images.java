package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {
    public static Image DUC1;
    public static SpriteSheet DUCKSHEET;
    public static SpriteSheet ROOMS;
    public static SpriteSheet FLOORS;
    public static SpriteSheet BUILD_ANIMATION;
    public static SpriteSheet BREAD_BUTTONS;


    public static SpriteSheet TITLE_SCREEN;
    public static SpriteSheet TITLE_ANIM;

    public static Image ADD_DUCK;
    public static Image FIRE_DUCK;
    public static Image CLIPBOARD;

    public static Image NEWS1;
    public static Image NEWS2;
    public static Image NEWS3;
    public static SpriteSheet BUTTONS;



    public static void loadImages()
    {
        try
        {
            //duck + room art
            DUCKSHEET = new SpriteSheet((new Image("res/duck/animated_duck.png")).getScaledCopy(448,64),64,64);
            DUC1 = DUCKSHEET.getSubImage(0,0).getScaledCopy(64,64);
            ROOMS = new SpriteSheet(new Image("res/floors/rooms.png").getScaledCopy(1600,200),400,200);
            FLOORS = new SpriteSheet(new Image("res/floors/floors.png").getScaledCopy(4800,200),800,200);
            BUILD_ANIMATION = new SpriteSheet(new Image("res/floors/build_room.png").getScaledCopy(800,200),400,200);


            //title screen + animation
            TITLE_SCREEN = new SpriteSheet(new Image("res/duckery.png"),1920, 1080);
            TITLE_ANIM = new SpriteSheet (new Image("res/animation.png").getScaledCopy(1920,1080*4),1920, 1080);

            //clipboard buttons + art
            ADD_DUCK = new Image("res/clipboard/add_duck.png");
            CLIPBOARD = new Image("res/clipboard/clipboard.png");
            BREAD_BUTTONS = new SpriteSheet(new Image("res/floors/bread_buttons.png"), 48,48);
            BUTTONS = new SpriteSheet(new Image("res/clipboard/buttons.png"),96,96);


            //add news images here
            NEWS1 = new Image("res/news/news_one.png");
            NEWS2 = new Image("res/news/news_two.png");
            NEWS3 = new Image("res/news/news_three.png");




        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
