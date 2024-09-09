package game;

import game.clipboard.MiniManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Clipboard {

    MiniManager hire;

    public Clipboard()
    {

    }


    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(1100,70,700,900);

        g.setColor(Color.magenta);
        g.fillRect(1150,100, 100, 100);

        g.setColor(Color.blue);
        g.fillRect(1150, 450, 100, 100);

        g.setColor(Color.darkGray);
        g.fillRect(1150, 800, 100,100);
    }

    public void update() {
    }

    public void keyPressed(int key, char c) {


    }

    public void mousePressed(int button, int x, int y) {

        if(button == 0 && x >= 1150 && x <= 1250 && y >= 110 && y <= 198)
        {
            System.out.println("Duck hired!");
        }

        if(button == 0 && x >= 1153 && x <= 1245 && y >= 455 && y <= 545)
        {
            System.out.println("Researching products..");
        }

        if(button == 0 && x >= 1154 && x <= 1245 && y >= 502 && y <= 889)
        {
            System.out.println("Advertising!!");
        }


    }
}
