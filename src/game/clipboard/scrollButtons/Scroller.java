package game.clipboard.scrollButtons;

import game.World;
import game.clipboard.Button;
import org.newdawn.slick.Image;

public class Scroller extends Button {

    int yCoefficient;
    private final int Y_CONST =7;

    public Scroller(int x, int y, Image image, String name) {
        super(x, y, image, name);

        if (name.equals("up"))
        {
            yCoefficient = 1;
        }
        else if (name.equals("down"))
        {
            yCoefficient = -1;
        }
    }

    @Override
    protected void action() {
        World.scroll(yCoefficient*Y_CONST);
    }
}
