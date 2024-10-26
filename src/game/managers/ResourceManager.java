package game.managers;

import core.Main;
import core.messages.FloatMessage;
import core.messages.MessageManager;
import game.RoomManager;
import game.World;
import game.building.rooms.Floor;
import game.building.rooms.ProductRoom;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class ResourceManager {
    //this class is super hefty
    // ----.        .----
    // <o>    ||     <0>
    //       /  \
    //
    //       < v >
    //         V

    static private double fund;
    private static ArrayList<double[]> roomVals;
    private ArrayList<Floor> floors;
    private static double incomeRate;// amount of money/5 seconds.
    private static int adLevel;
    private static int adTimer;
    private static int breadMade;
    private static int warEffort;
    private static int enemyEffort;
    private static int gunsMade;

    private int timer;

    public ResourceManager()
    {
        fund = 300;
        roomVals = new ArrayList<>();
        incomeRate = 0;
        adLevel = 0;
        enemyEffort = 300;
        warEffort = 300;
    }


    public static void render(Graphics g) {

    }

    public static void update(ArrayList<ProductRoom[]> rooms, ArrayList<ProductRoom[]> floors) {
        incomeRate = 0;
        addFunds(rooms);
        addFunds(floors);

        if ((int)(Math.random()*100)>=99 && RoomManager.getCurBasement()<-1)
        {
            enemyEffort +=2;
            warEffort -=2;
        }
        if ((int)(Math.random()*1000)>=999 && RoomManager.getCurBasement()<-1)
        {
            enemyEffort +=80;
            warEffort -=80;
        }
//        enemyEffort += (int)(Math.random()*10)-5;
//        warEffort += (int)(Math.random()*10)-5;

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

    //ACCESSORS
    public static double getIncomeRate(){return incomeRate;}
    public static double getFund(){return fund;}
    public static double getBreadMade(){return breadMade;}
    public static int getGunsMade(){ return gunsMade;}
    public static double getWarEffort(){return warEffort;}
    public static double getPercentConquered(){
        return (double)warEffort/(double)(enemyEffort +warEffort);
    }
    public static double getFunds(){ return fund;}

    public static int getRoomPrice(int floor, int room)
    {
        //return room prices
        double price = 30;

        int exp = floor* World.ROOMS_IN_FLOOR + room;

        for (int i = 0; i< exp; i++)
        {
            price *= 1.25;
        }
        return (int)price;

    }



    //MUTATORS
    public static void addFunds(ArrayList<ProductRoom[]> rooms)
    {
        //counts the total income of bread
        if(!rooms.isEmpty())
        {
            for (ProductRoom[] r :rooms)
            {
                for(ProductRoom room : r)
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
//                                Sounds.divineSound.play();
                            }
                            else
                            {
//                                warEffort+=room.getWarEffort();
//                                enemyEffort -= room.getWarEffort();
                                //enemyEffort-=room.getWarEffort();
                                fund += room.getValue();//costs money to build weapons
                                room.resetTimer();
                                MessageManager.addMessage(new FloatMessage(
                                        "+ "+1+" gun", room.getX() + (float)room.getWidth()/2, room.getY(),
                                        Color.white, 70));
                                gunsMade++;
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

    public static void attack(int value)
    {
        warEffort += value*5;
        enemyEffort -= value*5;
        gunsMade -= value;//lose all weapons made in attack
    }

    public static void advertise(int length, int boost){
        //advertise based on amount of time/
        adLevel  += boost;
        adTimer = length;
    }



    public static void setRooms(ArrayList<ProductRoom[]> rooms)
    {
        // update so that it doesn't reset old stuff.

        //right now: creates a blank table based on the number of rooms currently there.
        for (ProductRoom[] r :rooms)
        {
            double[] vals = new double [r.length];
            roomVals.add(vals);
        }

    }
    public static void addFloor(int currFloor, int currRoom)
    {
        roomVals.add(new double[4]);
        roomVals.getLast()[0] = 0;

    }

    public static  void updateRoom(int floor, int room, int numDucks) {
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
    public static void takeBread(int value)
    {
        if (value<=breadMade){
            breadMade -= value;
        }
    }

    //doesn't check if there's enough money
    public static void tax(int value)
    {
        fund -= value;
    }

    private static void calculateIncomeRate()
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



    public static int getFloorPrice(int floor)
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
