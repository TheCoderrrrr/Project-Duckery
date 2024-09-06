package game.entities;


import game.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Room {

    final public static int SIZE = 200;
    final int DIST_FROM_LEFT = 125;
    int x;
    int y;
    int numDucks;
    int myFloor;
    int myRoom;
    Color myColor;

    public Room (int floor, int number)
    {
        //floor must be at least 0, number between 0 and 3.
        myFloor = floor;
        myRoom = number;
        x = DIST_FROM_LEFT + number*SIZE;
        y = 4*SIZE-floor*SIZE;
        numDucks = 0;


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
        g.drawString("Num Ducks: "+numDucks+"\nFloor:"+myFloor+"\nRoom:"+myRoom +
                "\nmycolor:"+( (myFloor+myRoom)%2 == 0 ), x, World.getYDisplace() + y);
    }

    public void mousePressed(int button, int x, int y) {

        if (isOver(x,y)){
            addDuck();
        }

    }
//mutator
    private void addDuck()
    {
        // adds to the amount of ducks
        if (numDucks < 3) //sets maximum number of ducks to 3
        {
            numDucks ++;

        }
    }

    // accessor
    public int getNumDucks() {
        return numDucks;
    }

    public boolean isOver(int x, int y)
    {
        //tells you if an x and y value is over the room
        return (x>=this.x && x<= (this.x + SIZE)
                && y>= this.y && y<= (this.y + SIZE));
    }
}
