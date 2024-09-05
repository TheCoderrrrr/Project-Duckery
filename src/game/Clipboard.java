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
    }

    public void update() {
    }

    public void keyPressed(int key, char c) {


    }

    public void mousePressed(int button, int x, int y) {
    }
}
