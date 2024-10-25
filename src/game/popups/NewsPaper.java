package game.popups;

import core.Images;
import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public class NewsPaper {
    private ArrayList<Image> news;
    private int numNews;
    private boolean finsihed;

    public NewsPaper() {
        numNews = 0;
        news = new ArrayList<>();
        news.add(Images.NEWS1);
        news.add(Images.NEWS2);
        news.add(Images.NEWS3);
        news.add(Images.NEWS4);
        news.add(Images.NEWS5);
        news.add(Images.NEWS6);
        news.add(Images.NEWS7);
        news.add(Images.NEWS8);
        news.add(Images.NEWS9);
        finsihed = false;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(100, 100, Main.getScreenWidth() - 200, Main.getScreenHeight() - 200);
        g.setColor(Color.black);
        g.drawString("NEWS!!!!", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
        if (numNews < news.size()) {
            g.drawImage(news.get(numNews).getScaledCopy(Main.getScreenWidth() - 200, Main.getScreenHeight() - 200),
                    100, 100);
        }
    }

    public void mouseClicked(int x, int y) {
        numNews++;
    }
}
