package game.building.roomButtons;

import core.Main;
import game.World;
import game.building.rooms.ProductRoom;
import game.building.rooms.Room;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

abstract public class RoomButton {
    protected Room room;
    public final static int SIZE = 24;
    protected int myX;
    protected int myY;
    protected int myIndex;
    protected Color myColor;
    protected int red;
    protected Image myImage;
    protected String info;
    protected int price;

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
            g.drawImage(myImage.getScaledCopy(Main.getAdjustedX(SIZE), Main.getAdjustedY(SIZE)),
                    Main.getAdjustedX(myX), Main.getAdjustedY(myY + World.getYDisplace()));
        }

    }
    public void update()
    {
        myColor = new Color(red,255,255);
    }
    public boolean mouseOver(int x, int y)
    {
        if (x>myX && x < (myX+SIZE) && y>myY+World.getYDisplace() && y< (myY+World.getYDisplace()+SIZE) )
        {
            return true;
        }
        return false;
    }

    public String getInfo()
    {
        return info;
    }
    abstract public void action();

}
