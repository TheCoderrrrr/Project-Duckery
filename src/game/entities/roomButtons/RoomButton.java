package game.entities.roomButtons;

import game.World;
import game.entities.rooms.ProductRoom;
import game.entities.rooms.Room;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

abstract public class RoomButton {
    protected Room room;
    protected int SIZE = 24;
    protected int myX;
    protected int myY;
    protected int myIndex;
    protected Color myColor;
    protected int red;
    protected Image myImage;

    public RoomButton(ProductRoom room, int i )
    {
        this.room = room;
        myIndex = i;
        myX = room.getX()+room.getWidth() - SIZE*2;
        myY = room.getY()+SIZE + (int)((myIndex)*1.5*SIZE);
    }
    public RoomButton(Room room, int i)
    {
        this.room = room;
        myIndex = i;
        myX = room.getX()+room.getWidth() - SIZE*2;
        myY = room.getY()+SIZE + (int)((myIndex)*1.5*SIZE);
    }

    public void render(Graphics g)
    {
        if(myImage == null)
        {
            g.setColor(myColor);
            g.fillRect(myX, myY+ World.getYDisplace(), SIZE, SIZE);
        }
        else {
            g.drawImage(myImage.getScaledCopy(SIZE,SIZE), myX, myY);
        }

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
