package game;

import buttons.AdvertisingButton;
import buttons.Button;
import buttons.HiringButton;
import buttons.ResearchingButton;
import game.clipboard.MiniManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Clipboard {

    MiniManager hire;
    ArrayList<Button> buttons = new ArrayList<>();

    public Clipboard()
    {
        buttons.add(new HiringButton(1150, 100));
        buttons.add(new AdvertisingButton(1150, 450));
        buttons.add(new ResearchingButton(1150, 800));

    }


    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(1100,70,700,900);

        for(Button b: buttons)
        {
            b.render(g);
        }
    }

    public void update() {
    }

    public void keyPressed(int key, char c) {


    }

    public void mousePressed(int button, int x, int y) {

        for(Button b: buttons)
        {
            b.mousePressed(x, y);
        }


    }
}
