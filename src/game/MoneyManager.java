package game;

import core.Main;
import game.entities.Room;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class MoneyManager {

    static private double fund;
    ArrayList<double[]> roomVals;
    static double incomeRate;// amount of money/5 seconds.
    static double adLevel;

    int timer;


    public MoneyManager()
    {
        fund = 200;
        roomVals = new ArrayList<>();
        incomeRate = 0.0;
        adLevel = 1.0;
    }


    public void render(Graphics g) {
        g.setColor (Color.black);
        g.drawString("$" + fund +"\nINCOME:" + incomeRate + " per second", 20,20);

    }

    public void update(ArrayList<Room[]> rooms) {
        addFunds(rooms);
    }
    public void addFunds(ArrayList<Room[]> rooms)
    {
        incomeRate = 0;
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
                            fund += ((double)room.getValue()*adLevel);//increases ad level.
                            room.resetTimer();

                        }
                        if (room.getNumDucks() >0)
                        {
                            incomeRate += ((double)room.getValue()*adLevel)/(room.getTimeToMake())* Main.FRAMES_PER_SECOND;
                        }

                    }
                }
            }
        }
        incomeRate = ((int) (incomeRate *100))/100.0;
    }

    public static void advertise(int level){
        if (level == 1)
        {
            adLevel = 1.50;
        }
    }

    public static double getFunds(){ return fund;}

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
                incomeRate += val[j]*adLevel;
            }
        }
        // if advertising is true, multiply by some value.
    }

    public int getRoomPrice(int floor, int room)
    {
        //return room;
        double price = 30;

        int exp = floor*World.ROOMS_IN_FLOOR + room;

        for (int i = 0; i< exp; i++)
        {
          price *= 1.25;
        }
        return (int)price;

    }

    public int getFloorPrice(int floor)
    {
        if ( floor >0)
        {
            double price = (int)(getRoomPrice(World.FLOOR_UNLOCK_ROOMS*World.ROOMS_IN_FLOOR, 0)/10);
            int exp = floor - World.FLOOR_UNLOCK_ROOMS;

            for (int i = 0; i< exp; i++)
            {
                price *= 1.5;
            }
            return (int)price;
        }
        else
        {
            double price = (int)(getRoomPrice(World.BASEMENT_UNLOCK_FLOOR*World.ROOMS_IN_FLOOR, 0)/10);
            int exp = floor - World.BASEMENT_UNLOCK_FLOOR;

            for (int i = 0; i< exp; i++)
            {
                price *= 1.75;
            }
            return (int) price;
        }

    }
}
