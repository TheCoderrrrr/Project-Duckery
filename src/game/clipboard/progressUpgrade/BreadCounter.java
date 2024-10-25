package game.clipboard.progressUpgrade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BreadCounter {
    int counter;
    int total;
    int x;
    int y;

    public BreadCounter(int x, int y){
        counter = 0;
        total = 1;
        this.x = x;
        this.y = y;
    }

    public BreadCounter(int counter, int total, int x, int y){
        this.counter = counter;
        this.total = total;
        this.x = x;
        this.y = y;
    }

    public String getMessage(){
        if (!getComplete())
        {
            return "not enough bread";
        }
        return "";
    }

    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(x,y, 220,20 );
        g.setColor(Color.black);
        g.drawString("Bread: "+counter+" / "+total, x, y);
    }
    public void setCounter(int count){
        counter = count;
    }
    public boolean getComplete(){return  counter>=total;}
    public void setTotal(int total){this.total = total; }
    public void addCounter(){ counter++;}


}
