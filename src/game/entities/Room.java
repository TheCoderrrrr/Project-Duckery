package game.entities;


import game.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Room {

    final public static int SIZE = 200;
    final int DIST_FROM_LEFT = 125;
    int x;
    int y;
    int myFloor;
    private ArrayList<Duck> ducks;
    int myRoom;
    Color myColor;

    public Room (int floor, int number)
    {
        //floor must be at least 0, number between 0 and 3.
        ducks = new ArrayList<>();
        myFloor = floor;
        myRoom = number;
        x = DIST_FROM_LEFT + number*SIZE;
        y = 4*SIZE-floor*SIZE;


    }

    public void render(Graphics g)
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
        g.fillRect(x, World.getYDisplace() + y,SIZE,SIZE); // creates a square room at a given location
        g.setColor(Color.black);
        g.drawString("Num Ducks: "+getNumDucks()+"\nFloor:"+myFloor+"\nRoom:"+myRoom +
                "\nmycolor:"+( (myFloor+myRoom)%2 == 0 ), x, World.getYDisplace() + y);
        for (Duck duck : ducks) duck.render(g);

    }
    public void update()
    {
        for(Duck duck : ducks)
        {
            duck.update();
        }
    }

    public void mousePressed(int button, int x, int y) {

        if(button == 0 && isOver(x,y)) addDuck();
        else for(int i = 0; i < ducks.size(); i++) ducks.get(i).mousePressed(button, x, y);

    }
//mutator
    public void addDuck()
    {
        // adds to the amount of ducks
        ducks.add(new Duck(this));
    }
    public void removeDucks(Duck duck)
    {
        ducks.remove(duck);
    }

    // accessor
    public int getNumDucks() {
        return ducks.size();
    }

    public int curYPosition()
    {
        return y + World.getYDisplace();
    }
    public boolean isOver(int x, int y)
    {
        //tells you if an x and y value is over the room
        return (x>=this.x && x<= (this.x + SIZE)
                && y>= (this.y+World.getYDisplace()) && y<= (this.y+World.getYDisplace() + SIZE));
    }
}
