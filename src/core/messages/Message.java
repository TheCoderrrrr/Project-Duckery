package core.messages;

import game.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import core.Fonts;

public class Message {

    protected String text;
    protected float x;
    protected float y;
    protected Color color;
    protected Font font;
    protected int duration;
    protected int timeLeft;
    protected boolean fading;

    public Message (String text, float x, float y, Color color, Font font)
    {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.font = font;
        duration = -1;
        fading = false;
    }

    public Message (String text, float x, float y)
    {
        this(text, x, y, Color.white, Fonts.big);
        duration = -1;
    }

    public Message (String text, float x, float y, Color color, Font font, int duration)
    {
        this.text = text;
        this.x = x;
        this.y=y;
        this.color = color;
        this.font = font;
        this.duration = duration;
        timeLeft = duration;
        fading = true;
    }

    public Message (String text, float x, float y, int duration)
    {
        this(text, x, y, Color.white, Fonts.big);
        this.duration = duration;
        timeLeft = duration;
        fading = true;
    }

    public void update()
    {
        timeLeft --;
    }

    public void render(Graphics g)
    {
        if(fading)
        {
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), getPercentTimeLeft());
        }
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x , y+ World.getYDisplace());

    }

    public int getDuration()
    {
        return duration;
    }
    public int getTimeLeft()
    {
        return timeLeft;
    }
    public float getPercentTimeLeft()
    {
        return (float)timeLeft/(float)duration;
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public void setFont(Font font)
    {
        this.font = font;
    }

    public void centerText()
    {
        x = x-font.getWidth(text)/2;
    }

}
