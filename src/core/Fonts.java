package core;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class Fonts {

    public static TrueTypeFont big;
    public static TrueTypeFont small;

    public static void loadFonts()
    {
        big = new TrueTypeFont (new Font("Verdana", Font.BOLD, 42), false);
        small = new TrueTypeFont (new Font("Verdana", Font.BOLD, 15), false);

    }

}