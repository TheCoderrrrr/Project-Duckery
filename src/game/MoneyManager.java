package game;

import game.entities.Room;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class MoneyManager {

    static private double fund;
    ArrayList<double[]> roomVals;
    double incomeRate;// amount of money/5 seconds.

    int timer;


    public MoneyManager()
    {
        fund = 100.0;
        roomVals = new ArrayList<>();
        incomeRate = 0.0;
    }


    public void render(Graphics g) {
        g.setColor (Color.black);
        g.drawString("$" + fund +"\nINCOME:" + incomeRate, 20,20);

    }

    public void update(ArrayList<Room[]> rooms) {
        addFunds(rooms);
    }
    public void addFunds(ArrayList<Room[]> rooms)
    {
        if(!rooms.isEmpty())
        {
            for (Room[] r :rooms)
            {
                for(Room room : r)
                {
                    if(room != null)
                    {
                        if(room.completedProduct())
                        {
                            fund += room.getValue();
                            room.resetTimer();
                        }
                    }
                }
            }
        }
    }

    public void setRooms(ArrayList<Room[]> rooms)
    {
        // update so that it doesn't reset old stuff.

        //right now: creates a blank table based on the number of rooms currently there.
        for (Room[] r :rooms)
        {
            double[] vals = new double [r.length];
            roomVals.add( vals);
        }

    }
    public void addFloor(int currFloor, int currRoom)
    {
        roomVals.add(new double[4]);
        roomVals.getLast()[0] = 0;

    }

    public void updateRoom(int floor, int room, int numDucks) {
        //will be updated later to keep track of type of product as well.

        // for each room, updates the value it adds to the overall income (per day)

        Double value = 0.0;
        if (numDucks == 1)
        {
            value = 1.0;
        }
        else if (numDucks == 2)
        {
            value = 1.5;
        }
        else if (numDucks == 3)
        {
            value = 1.75;
        }


        double[] v = roomVals.get(floor);
        v[room] = value;

        calculateIncomeRate();


    }

    public static void withdraw(int value)
    {
        if (value<=fund){
            fund -= value;
        }

    }

    private void calculateIncomeRate()
    {
        // calculates the income rate by adding up values in each room
        incomeRate = 0.0;
        for (int i = 0; i < roomVals.size(); i++)
        {
            for (int j =0; j< roomVals.get(i).length; j++)
            {
                double[] val = roomVals.get(i);
                incomeRate += val[j];
            }
        }
        // if advertising is true, multiply by some value.
    }

}
