package game;

import core.Game;
import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class News {
    public boolean timerRunning;
    public int elapseTime;

    public News() {
        timerRunning = true;
    }

    public void render(Graphics g)
    {
        g.drawString("Time: " + formatTime(elapseTime), 1697, 42);

        if (elapseTime == 600 || elapseTime == 1200)
        {
            g.setColor(Color.white);
            g.fillRect(100, 100, Main.getScreenWidth()-200, Main.getScreenHeight()-200);
            g.setColor(Color.black);
            g.drawString("NEWS INCOMING!!!", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
            System.out.println("News");
            timerRunning = false;
            World.pause();
        }
    }

    public void update(int delta) throws SlickException {
        if(timerRunning) {
            elapseTime ++;
        }

    }

    public String formatTime(int elapseTime)
    {
        int totalSeconds = elapseTime / 60;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public void mouseClicked()
    {
        timerRunning = true;
        World.unpause();
    }


}
