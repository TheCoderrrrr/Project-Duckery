package game;

import game.clipboard.buttons.BuyingButton;
import game.clipboard.MiniManager;
import game.clipboard.menus.AdvertisingMenu;
import game.clipboard.menus.HiringMenu;
import game.clipboard.menus.Menu;
import game.clipboard.menus.ResearchMenu;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Clipboard {

    MiniManager hire;
    ArrayList<BuyingButton> buttons = new ArrayList<>();
    ArrayList<Menu> menus = new ArrayList<Menu>();

    public Clipboard()
    {
//        buttons.add(new HiringButton(1150, 100));
//        buttons.add(new AdvertisingButton(1150, 450));
//        buttons.add(new ResearchingButton(1150, 800));

        menus.add(new HiringMenu());
        menus.add(new AdvertisingMenu());
        menus.add(new ResearchMenu());

    }


    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(1100,70,700,900);

        for(Menu m: menus)
        {
            m.render(g);
        }
    }

    public void update() {
    }

    public void keyPressed(int key, char c) {


    }

    public void mousePressed(int button, int x, int y) {

        for(Menu m: menus)
        {
            m.click(x, y);
        }


    }
}
