package game;

import core.Main;
import core.messages.FloatMessage;
import core.messages.MessageManager;
import game.entities.rooms.Floor;
import game.entities.rooms.Room;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class ResourceManager {

    static private double fund;
    private ArrayList<double[]> roomVals;
    private ArrayList<Floor> floors;
    private static double incomeRate;// amount of money/5 seconds.
    private static int adLevel;
    private static int adTimer;
    private static int breadMade;
    private static int warEffort;

    private int timer;

    public double getIncomeRate(){return incomeRate;}
    public double getFund(){return fund;}
    public double getBreadMade(){return breadMade;}


    public ResourceManager()
    {
        fund = 300;
        roomVals = new ArrayList<>();
        incomeRate = 0;
        adLevel = 0;
    }


    public static void render(Graphics g) {

    }

    public void update(ArrayList<Room[]> rooms, ArrayList<Room[]> floors) {
        incomeRate = 0;
        addFunds(rooms);
        addFunds(floors);

        //counts down the timer for ads to reduce
        if (adTimer>0)
        {
            adTimer --;
        }
        //resets base adLevel (no bonus)
        if (adTimer == 0)
        {
            adLevel = 0;
        }
    }
    public void addFunds(ArrayList<Room[]> rooms)
    {
        //counts the total income of bread
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
                            if(!room.isBasement())
                            {
                                fund += ((double)room.getValue()+adLevel);//increases ad level.
                                room.resetTimer();
                                MessageManager.addMessage(new FloatMessage(
                                        "+ "+(room.getValue()+adLevel), room.getX() + (float)room.getWidth()/2, room.getY(),
                                        Color.yellow, 70));
                                breadMade++;
                            }
                            else
                            {
                                warEffort++;
                                room.resetTimer();
                                MessageManager.addMessage(new FloatMessage(
                                        "+ "+(room.getWarEffort()+adLevel), room.getX() + (float)room.getWidth()/2, room.getY(),
                                        Color.white, 70));
                            }
                        }
                        if (room.getNumDucks() >0)
                        {
                            incomeRate += (room.getValue()+adLevel)/(room.getTimeToMake())* Main.FRAMES_PER_SECOND;
                        }

                    }
                }
            }
        }
        incomeRate = ((int) (incomeRate *100))/100;
    }
    public void addWarEffort()
    {

    }

    public static void advertise(int length, int boost){
        //advertise based on amount of time/
        adLevel  += boost;
        adTimer += length;
    }

    public static double getFunds(){ return fund;}

    public void setRooms(ArrayList<Room[]> rooms)
    {
        // update so that it doesn't reset old stuff.

        //right now: creates a blank table based on the number of rooms currently there.
        for (Room[] r :rooms)
        {
            double[] vals = new double [r.length];
            roomVals.add(vals);
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

    //doesn't check if there's enough money
    public static void tax(int value)
    {
        fund -= value;
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
                incomeRate += val[j]+adLevel;
            }
        }
        // if advertising is true, multiply by some value.
    }

    public int getRoomPrice(int floor, int room)
    {
        //return room prices
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
        //price of next floor: should be updated
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
