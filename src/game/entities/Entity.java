package game.entities;

import org.newdawn.slick.Graphics;

import java.awt.*;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int size;
    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }
    public abstract void render(Graphics g);
    public abstract void update();

}
