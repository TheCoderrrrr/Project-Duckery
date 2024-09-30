package game.entities.roomButtons;

import game.World;
import game.entities.rooms.Room;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

abstract public class RoomButton {
    protected Room room;
    protected int SIZE = 20;
    protected int myX;
    protected int myY;
    protected int myIndex;
    protected Color myColor;
    protected int red;

    public RoomButton(Room room)
    {
        this.room = room;
        myX = room.getX()+room.getWidth() - SIZE*2;
        myY = room.getY()+(myIndex+1)*SIZE;
    }

    public void render(Graphics g)
    {
        g.setColor(myColor);
        g.fillRect(myX, myY+ World.getYDisplace(), SIZE, SIZE);
    }
    public void update()
    {
        myColor = new Color(red,255,255);
    }
    public boolean mouseOver(int x, int y)
    {
        if (x>myX && x < (myX+SIZE) && y>myY && y< (myY+SIZE) )
        {
            red = 255;
            return true;
        }
        return false;
    }
    abstract public void action();

}
