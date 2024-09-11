package game.entities;

import game.World;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import core.Images;

public class Duck extends Entity {
    private Room room;
    public Duck(Room room)
    {
        super(room.getX(), room.getY());
        this.room = room;
        image = Images.DUC1;
        size = image.getWidth();

    }
    public void render(Graphics g) {
        if(isOver(Mouse.getX(), Mouse.getY()))
        {
            System.out.println("OVERRR");
        }
        g.drawImage(image, x, y + World.getYDisplace());
    }

    public void update() {


       // System.out.println(x + " : " + y + " : " + World.getYDisplace() + " : " + room.y + " : " + room.curYPosition());

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
                && y>= this.y + World.getYDisplace() && y<= (this.y + size + World.getYDisplace()));
    }
}
