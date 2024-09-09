package buttons;

import org.newdawn.slick.Color;

public class HiringButton extends Button {

    public HiringButton(int x, int y) {
        super(x, y);
        color = Color.magenta;
    }

    @Override
    public void onClick() {
        System.out.println("Hiring Duck!");
    }


}
