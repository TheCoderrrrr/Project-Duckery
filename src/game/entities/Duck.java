package game.entities;

import core.Sounds;
import core.Images;
import game.entities.rooms.Room;

import static core.Images.DUCKSHEET;
import static game.World.getYDisplace;

public class Duck extends Entity {
    private Room room;
    private final int X_SPEED = 1;
    private int xConst;
    private int i;
    private int timer;
    private int timer2;
    private boolean makeWeapons;
    private double mySpeed;

    public Duck(Room room, int startX, int startY)
    {
        super(room.getX(), room.getY());
        this.room = room;
        image = Images.DUC1;
        size = image.getWidth();
        xConst = 1;
        x = startX;
        y = startY;
//        if (!isAboveFloor()) {
//            y = room.getFloor() - 40 - size;
//        }

        y = room.getY();

        makeWeapons = true;
        mySpeed = Math.random()*2+1;


        image = image.getFlippedCopy(true,false);

    }


    public void update() {
        if(isAboveFloor())
        {
            y++;
        }
        else {
            x += xConst * X_SPEED * mySpeed;
        }
        timer ++;
        timer2++;
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

        }

        if (x > (room.getX()+room.getWidth() - size))
        {
            xConst = -1;

        }
        if (xConst == 1)
        {
            image = DUCKSHEET.getSubImage(i,0).getFlippedCopy(true, false);
        }
        if (xConst == -1)
        {
            image = DUCKSHEET.getSubImage(i,0);
        }

        if(timer2 % 600 == 0)
        {
            quack();
        }
    }
    public void quack()
    {
        int quack = (int) (Math.random() * 4);
        switch(quack){
            case 0: Sounds.rossQuack.play(1.1f - (float)(Math.random() * 0.2f), 0.7f);
                    break;
            case 1: Sounds.mrMalQuack.play(1.1f - (float)(Math.random() * 0.2f), 0.57f);
                    break;
            case 2: Sounds.vedikaQuack.play(1.1f - (float)(Math.random() * 0.2f), 0.57f);
                    break;
            case 3: Sounds.gunnarQuack.play(1.1f - (float)(Math.random() * 0.2f), 0.57f);
                    break;
        }

    }
    public void mousePressed(int button, int x, int y)
    {
            room.removeDucks(this);
    }

    public boolean inRoomBounds()
    {
        return (x >= room.getLeftWall() && x + size < room.getRightWall() && y + getYDisplace() + size + 40 < room.getFloor());

    }

    public boolean isAboveFloor()
    {
        return (y + size  + 40 < room.getY()+ room.HEIGHT);
    }
}
