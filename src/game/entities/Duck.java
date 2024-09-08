package game.entities;

import game.World;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Duck extends Entity{
    private Room room;
    public Duck(Room room)
    {
        super(room.x, room.y);
        this.room = room;
        size = 60;

    }
    public void render(Graphics g) {
        if(isOver(Mouse.getX(), Mouse.getY()))
        {
            System.out.println("OVERRR");
        }
        g.fillRect(x, y + World.getYDisplace(), size, size );
    }

    public void update() {
        System.out.println(x + " : " + y + " : " + World.getYDisplace() + " : " + room.y + " : " + room.curYPosition());

    }
    public void mousePressed(int button, int x, int y)
    {
        if(isOver(x, y) && button == 1)
        {
            room.removeDucks(this);
        }
    }
    public boolean isOver(int x, int y)
    {
        //tells you if an x and y value is over the duck
        return (x>=this.x && x<= (this.x + size)
                && y>= this.y && y<= (this.y + size + World.getYDisplace()));
    }
}
