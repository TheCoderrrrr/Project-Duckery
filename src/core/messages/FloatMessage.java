package core.messages;

import org.newdawn.slick.Color;

import core.Fonts;

public class FloatMessage extends Message{

    public FloatMessage (String text, float x, float y, Color color, int duration)
    {
        super(text, x, y, color,Fonts.big, duration);
        fading = true;
    }

    public void update()
    {
        super.update();
        y--;
    }

}
