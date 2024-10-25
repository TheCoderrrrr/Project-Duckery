package game.managers;

import core.Main;
import game.World;
import game.popups.NewsPaper;
import game.popups.PayDay;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import javax.swing.*;
import java.awt.*;

public class PopupManager {
    private NewsPaper newsPaper;
    //private PayDay payDay;
    public int elapseTime;
    public static int LENGTH_OF_WEEK = Main.FRAMES_PER_SECOND * 45;
    public static boolean timerRunning;
    private int numClicks = 0;
    private static boolean begin;

    public PopupManager() {
        newsPaper = new NewsPaper();
        //payDay = new PayDay();
        timerRunning = true;
        begin = true;
    }

    public static void setBegin() {
        begin = true;
    }

    public static boolean hasBegun() {
        return begin;
    }

    public void render(Graphics g) {
        if (hasBegun()) {
            g.setColor(Color.black);
            g.drawString("Time: " + formatTime(elapseTime), 1697, 42);
            if (elapseTime % LENGTH_OF_WEEK == 0 ) {
                //payDay.render(g);
                ;
            }
            if (elapseTime % LENGTH_OF_WEEK == 0 ) {
                timerRunning = false;
                newsPaper.render(g);
            }
        }
    }

    public void update() throws SlickException {
        if (timerRunning) {
            elapseTime++;
        }
        if (elapseTime % LENGTH_OF_WEEK == 0 && numClicks == 0) {
            //payDay.update();
        }
    }

    public String formatTime(int elapseTime) {
        int totalSeconds = elapseTime / 60;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public void mousePressed(int x, int y) {

        if (!timerRunning){
//            if (!payDay.getIsPaid() && elapseTime % LENGTH_OF_WEEK == 0 ) {//checks that is in right state;
//                payDay.mouseClicked(x, y);
//            } else if (payDay.getIsPaid() && elapseTime % LENGTH_OF_WEEK == 0 && numClicks <1)//switching slides
//            {
//                numClicks++;
//            }
             if (elapseTime % LENGTH_OF_WEEK == 0 ) {
                timerRunning = true;
                World.unpause();
                numClicks = 0;
                //payDay.setUpdated(false);
                newsPaper.mouseClicked(x, y);
            }
        }


    }
}
