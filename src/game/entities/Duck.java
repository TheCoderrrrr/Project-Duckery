package game.entities;

import game.World;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import core.Images;
import org.newdawn.slick.SpriteSheet;

import static core.Images.DUC2;

public class Duck extends Entity {
    private Room room;
    private final int X_SPEED = 1;
    private int xConst;
    private int i;
    private int timer;

    public Duck(Room room)
    {
        super(room.getX(), room.getY());
        this.room = room;
        image = Images.DUC1;
        size = image.getWidth();
        xConst = 1;


        image = image.getFlippedCopy(true,false);

    }
    public void render(Graphics g) {
        if(isOver(Mouse.getX(), Mouse.getY()))
        {
            System.out.println("OVERRR");
        }
        g.drawImage(image, x, y + World.getYDisplace());
    }

    public void update() {
        if(inRoomBounds())
        {
            y++;
        }
        else {
            x += xConst * X_SPEED;
        }
        timer ++;

        if (timer == 2)
        {
            if (i<6)
            {
                i++;
            }
            else {
                i = 0;
            }

            timer = 0;

        }



        if (x < room.getX())
        {
            xConst = 1;
            image = DUC2.getSubImage(i,0).getFlippedCopy(true, false);
        }
        if (x > (room.getX()+Room.WIDTH - size))
        {
            xConst = -1;
            image = DUC2.getSubImage(i,0);
        }


    }
    public void mousePressed(int button, int x, int y)
    {
            room.removeDucks(this);
    }
    public boolean isOver(int x, int y)
    {
        //tells you if an x and y value is over the duck
        return (x>=this.x && x<= (this.x + size)
                && y>= this.y + World.getYDisplace() && y<= (this.y + size + World.getYDisplace()));
    }
    public boolean inRoomBounds()
    {
        return (x >= room.getLeftWall() && x + size < room.getRightWall() && y + World.getYDisplace() + size + 40 < room.getFloor());

    }
}
