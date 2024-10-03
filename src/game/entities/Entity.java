package game.entities;

import game.World;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.*;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image image;
    protected int size;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        if (isOver(Mouse.getX(), Mouse.getY())) {
        }
        g.drawImage(image, x, y + World.getYDisplace());
    }

    public abstract void update();

    public boolean isOver(int x, int y)
    {
        //tells you if an x and y value is over the duck
        return (x>=this.x && x<= (this.x + size)
                && y>= this.y + World.getYDisplace() && y<= (this.y + size + World.getYDisplace()));
    }


}
