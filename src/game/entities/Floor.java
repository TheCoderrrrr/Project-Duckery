package game.entities;

import game.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Floor extends Room {

    //int myFloor;
    Room[] rooms;
    public Floor(int myFloor)
    {
        super(myFloor,0 );
        this.myFloor = myFloor;
        rooms = new Room[World.ROOMS_IN_FLOOR];
        for (int i=0; i<rooms.length; i++)
        {
            rooms[i] = new Room(myFloor, i);
        }




    }

    public void render (Graphics g)
    {
        if ( ( myFloor+myRoom ) %2 ==0 )
        {
            myColor = (Color.white);
        }
        else
        {
            myColor = Color.lightGray;
        }
        g.setColor(myColor);
        g.fillRect(x, World.getYDisplace() + y,WIDTH*2,HEIGHT); // creates a long room at a given location
        g.setColor(Color.black);

        g.drawString("Num Ducks: "+getNumDucks()+"\nFloor:"+myFloor+"\nRoom:"+myRoom +
                "\nProduct" + getProductName(), x, World.getYDisplace() + y);
        for (Duck duck : ducks) duck.render(g);
    }

    public boolean isOver(int x, int y)
    {
        return (x>=this.x && x<= (this.x + 2*WIDTH)
                && y>= (this.y+World.getYDisplace()) && y<= (this.y+World.getYDisplace() + HEIGHT));
    }

    public void mousePressed(int button, int x, int y) {

        if(button == 0 && isOver(x,y) && getNumDucks()<=6){
            addDuck();
        }
        else if(button == 2 && isOver(x,y) && curItem < products.size() - 1)
        {
            switchProduct();
        }
        else if(button == 1)
        {
            for (Duck duck : ducks) {
                if (duck.isOver(x, y)) {
                    duck.mousePressed(button, x, y);
                    break;
                }
            }
        }

    }
}

