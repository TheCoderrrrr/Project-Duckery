package game.clipboard.buttons;

import game.World;
import org.newdawn.slick.Color;

public class HiringButton extends Button {

    public HiringButton(int x, int y) {
        super(x, y);
        color = Color.magenta;
        name = "hire";
        price = 10;

    }

    @Override
    public void onClick() {
        super.onClick();
        World.increaseDuckLimit();
        System.out.println("Hiring Duck!");
    }


}
