package buttons;

import org.newdawn.slick.Color;

public class AdvertisingButton extends Button{

    public AdvertisingButton(int x, int y) {
        super(x, y);
        color = Color.blue;
    }


    @Override
    public void onClick() {
        System.out.println("Advertising!!");
    }
}
