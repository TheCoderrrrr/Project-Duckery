package game.building.rooms;

import game.managers.ResourceManager;
import game.World;
import game.clipboard.buttons.ads.AdvertisingButton;
import game.building.Duck;
import game.building.roomButtons.RoomButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class AdRoom extends Room{
    static private int adProductionTotalTime;
    static private int adTimer;
    private static boolean makingAd; // tells if a product is being researched
    private static boolean hasDucks;
    private static boolean first;

    AdvertisingButton[] ads;

    public AdRoom() {
        super(1,0);
        width = 800;
        maxDucks = 6;
        adProductionTotalTime = 30*60;
        adTimer = -1;
        first = true;

        myImage = myFloorTypes.getSubImage(5,0);

    }

    public void render(Graphics g)
    {
        super.render(g);// creates a square room at a given location
        g.setColor(Color.black);
        if (ducks.isEmpty())
        {
            g.drawString("pls gimme ducks", x, World.getYDisplace() + y + 20);
        }
        else if (makingAd)
        {
            g.drawString("currently creating new ad"
                            +"\nTime to comletion: "+(adTimer)
                    , x, World.getYDisplace() + y+ 20);
        }

        for (Duck duck : ducks) duck.render(g);
        for (int i=0;i<myButtons.size();i++) myButtons.get(i).render(g);

    }

    public void update()
    {
        super.update();

        hasDucks = !ducks.isEmpty();
        // if not making an ad and there are no ducks, start making ad
        if(!makingAd && !ducks.isEmpty() && first)
        {
            beginResearch();
            first = false;
        }
        else if (makingAd && adTimer >0 && !ducks.isEmpty())
        {
            adTimer-= (int) Math.pow(1.5,getNumDucks() );
        }
        else if (adTimer <= 0 )
        {
           //releaseBlitz();
        }
    }

    public static void releaseBlitz()
    {
        ResourceManager.advertise(200, 4);
        adTimer = adProductionTotalTime;
    }

    public static void beginResearch() {
        if (hasDucks )
        {
            adTimer = adProductionTotalTime;
            makingAd = true;
        }
    }

    public static  float getPercentDone(){ return (float)adTimer/adProductionTotalTime;}

    public String getInfo(int x, int y)
    {
        String ret = "";
        boolean overButton = false;
        for (RoomButton b: myButtons)
        {
            if (b.mouseOver(x,y))
            {
                ret = b.getInfo();
                overButton = true;
            }
        }
        if (!overButton)
        {
            if(makingAd)
            {
                ret = "Working hard to make an ad";
            }
            else if (getNumDucks() >0){
                ret = "working hard to research an ad";
            }
            else {
                ret = "hire a duck to make an advertisement!";
            }
        }

        return ret;
    }

    public void removeDucks( Duck duck)
    {
        super.removeDucks(duck);
        if (adTimer != 0 )
        {
            makingAd = false;
        }
    }
}
