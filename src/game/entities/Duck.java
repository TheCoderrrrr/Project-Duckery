package game.entities;

import game.World;
import game.entities.rooms.Room;
import core.Images;

import static core.Images.DUC2;

public class Duck extends Entity {
    private Room room;
    private final int X_SPEED = 1;
    private int xConst;
    private int i;
    private int timer;
    private boolean makeWeapons;

    public Duck(Room room, int startX)
    {
        super(room.getX(), room.getY());
        this.room = room;
        image = Images.DUC1;
        size = image.getWidth();
        xConst = 1;
        x = startX;
        makeWeapons = true;


        image = image.getFlippedCopy(true,false);

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
        if (x > (room.getX()+room.getWidth() - size))
        {
            xConst = -1;
            image = DUC2.getSubImage(i,0);
        }


    }
    public void mousePressed(int button, int x, int y)
    {
            room.removeDucks(this);
    }

    public boolean inRoomBounds()
    {
        return (x >= room.getLeftWall() && x + size < room.getRightWall() && y + World.getYDisplace() + size + 40 < room.getFloor());

    }
}
