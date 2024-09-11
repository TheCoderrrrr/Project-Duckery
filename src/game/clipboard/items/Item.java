package game.clipboard.items;

import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Item {
    protected int value;
    protected int timeToCreate;
    protected String name;
    protected Image image;

    public Item()
    {
    }

    public void render(Graphics g)
    {

    }

    public void update()
    {

    }
    public int getValue()
    {
        return value;
    }
    public int getTimeToCreate()
    {
        return timeToCreate * Main.FRAMES_PER_SECOND;
    }
    public String getName()
    {
        if(!name.isEmpty()) return name;
        else return "";
    }
}
