package game;

import core.Images;
import core.Main;
import game.clipboard.buttons.BuyingButton;
import game.clipboard.buttons.ads.AdvertisingButton;
import game.clipboard.menus.*;
import game.clipboard.progressUpgrade.ad.AdManager;
import game.clipboard.progressUpgrade.research.ResearchManager;
import game.clipboard.progressUpgrade.UpgradeManager;
import game.clipboard.progressUpgrade.war.WarManager;
import game.managers.ResourceManager;
import game.clipboard.ProgressBar;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Clipboard {

    //manages all  the buttons and lets them get clicked;
    static ArrayList<BuyingButton> buttons = new ArrayList<>();
    static ArrayList<Menu> menus = new ArrayList<Menu>();
    static ArrayList<UpgradeManager> bars = new ArrayList<UpgradeManager>();
    static HiringMenu h;
    static String info;
    static ProgressBar warBar;

    public Clipboard()
    {
        h = new HiringMenu();

        //bars.add(new UpgradeManager(1, "test"));
        bars.add(new ResearchManager());
        bars.add(new AdManager());
        bars.add(new WarManager());
        menus.add(h);
        //menus.add(new AdvertisingMenu());
        //menus.add(new HotkeyMenu());
        info = "tester";

        warBar = new ProgressBar(1350,130,200,175, "WAR PROGRESS!!");

    }


    public void render(Graphics g) {

        g.drawImage(Images.CLIPBOARD.getScaledCopy(Main.getAdjustedX(700), Main.getAdjustedY(900)),
                Main.getAdjustedX(1100), Main.getAdjustedY(70));

        g.drawString(info, Main.getAdjustedX(1150),Main.getAdjustedX(120));

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
        for (UpgradeManager b: bars)
        {
            b.render( g);
        }
    }

    public void update() {
        AdvertisingButton.update();
        if(World.getWar())
        {
            warBar.update(ResourceManager.getPercentConquered());
        }
        for (UpgradeManager b: bars)
        {
            b.update();
        }

    }

//ACCESSOR
    public static HiringMenu getHireMenu()
    {
        return h;
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

//MUTATOR

    public void keyPressed(int key, char c) {}

    public void mousePressed(int button, int x, int y) {

        if (!World.getPause())
        {
            for(Menu m: menus)
            {
                m.click(x, y);
            }
            for(UpgradeManager b: bars)
            {
                b.click(x, y);
            }
        }
    }

    public static void updateInfo(String newInfo)
    {
        info = newInfo;
    }
}
