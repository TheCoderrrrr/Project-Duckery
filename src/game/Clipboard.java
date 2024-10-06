package game;

import core.Images;
import game.clipboard.buttons.BuyingButton;
import game.clipboard.MiniManager;
import game.clipboard.buttons.ads.AdvertisingButton;
import game.clipboard.menus.*;
import game.progressBar.ProgressBar;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Clipboard {

    static MiniManager hire;
    static ArrayList<BuyingButton> buttons = new ArrayList<>();
    static ArrayList<Menu> menus = new ArrayList<Menu>();
    static HiringMenu h;
    static String info;
    static ProgressBar warBar;

    public Clipboard()
    {
        h = new HiringMenu();

        menus.add(h);
        menus.add(new AdvertisingMenu());
        menus.add(new HotkeyMenu());
        info = "tester";

        warBar = new ProgressBar(1550,230,200,175, "WAR PROGRESS!!");
        //menus.add(new ResearchMenu());

    }

    public static HiringMenu getHireMenu()
    {
        return h;
    }


    public void render(Graphics g) {
        g.drawImage(Images.CLIPBOARD.getScaledCopy(700,900),1100,70);

        g.drawString(info, 1150,120);

        for(Menu m: menus)
        {
            m.render(g);
        }
        if(World.getWar())
        {
            warBar.render(g);
        }

        g.setColor(Color.black);
        g.drawString("$" + ResourceManager.getFund() +"\nBread made: " + ResourceManager.getBreadMade() +
                "\nEmployees: "+World.getTotalDucks(), 1600,110);
    }

    public void update() {
        AdvertisingButton.update();
        if(World.getWar())
        {
            warBar.update(ResourceManager.getPercentConquered());
        }

    }

    public static String getButtonInfo(int x, int y)
    {
        String ret = "";
        for(Menu m: menus)
        {
            ret = ret + m.mouseOver(x, y);
        }
        return ret;
    }

    public static void updateInfo(String newInfo)
    {
        info = newInfo;
    }

    public void keyPressed(int key, char c) {


    }

    public void mousePressed(int button, int x, int y) {

        if (!World.getPause())
        {
            for(Menu m: menus)
            {
                m.click(x, y);
            }
        }



    }
}
