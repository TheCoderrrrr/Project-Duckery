package game.popups;

import core.Images;
import core.Main;
import game.World;
import game.paydayButtons.PayDucks;
import game.paydayButtons.PayRoomTax;
import game.paydayButtons.PaydayButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class PayDay {
    private PaydayButton[] payButtons;
    private boolean updated;
    private boolean finished;

    public PayDay() {
        payButtons = new PaydayButton[]{new PayDucks(), new PayRoomTax()};
        finished = false;
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(100, 100, Main.getScreenWidth() - 200, Main.getScreenHeight() - 200);
        World.pause();
        for (int i = 0; i < payButtons.length; i++) {
            payButtons[i].render(g);
        }
    }

    public void update() throws SlickException {
        if(!updated)
        {
            for (int i = 0; i < payButtons.length; i++) {
                payButtons[i].calculatePrice();
            }
            updated = true;
        }
    }


    public void mouseClicked(int x, int y) {
        for (int i = 0; i < payButtons.length; i++) {
            if (payButtons[i].mouseOver(x, y)) {
                payButtons[i].click();
            }
        }
    }

    public boolean getIsPaid() {
        boolean isPaid = true;
        for (int i = 0; i < payButtons.length; i++) {
            if (!payButtons[i].isPaid()) {
                isPaid = false;
            }
        }
        return isPaid;
    }

    public void setUpdated(boolean set) {
        updated = set;
    }
}
