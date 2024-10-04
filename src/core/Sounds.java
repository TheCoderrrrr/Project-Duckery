package core;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sounds {

    public static Sound moneySound;
    public static Sound divineSound;
    public static Sound mrMalQuack;
    public static Sound rossQuack;
    public static Sound vedikaQuack;
    public static Sound gunnarQuack;

    public static void loadSounds()
    {
        try {
            moneySound = new Sound("res/sounds/Dvij_cha_ching.wav");
            divineSound = new Sound("res/sounds/Divine_bread_cha_ching.wav");
            mrMalQuack = new Sound("res/sounds/quacks/Mr_Mal_Quack.wav");
            rossQuack = new Sound("res/sounds/quacks/Ross_Quack.wav");
            vedikaQuack = new Sound("res/sounds/quacks/Vedika_Quack.wav");
            gunnarQuack = new Sound("res/sounds/quacks/Gunnar_Quack.wav");

        }
        catch(SlickException e)
        {
            e.printStackTrace();
        }
    }

}
