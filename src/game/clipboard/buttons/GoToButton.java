package game.clipboard.buttons;

import core.Images;
import game.World;
import org.newdawn.slick.Color;

public class GoToButton extends BuyingButton{

    char where;

    public GoToButton(int x, int y, char where) {
        super(x, y);
        this.where =where;
        color = Color.gray;

        if (where == 'T')
        {
            name = "Top";
            image = Images.BUTTONS.getSubImage(6,0).getScaledCopy(SIZE,SIZE);
        }
        if (where == 'B')
        {
            name = "Bottom";
            image = Images.BUTTONS.getSubImage(8,0).getScaledCopy(SIZE,SIZE);
        }
        if (where == 'R')
        {
            name = "Research Floor";
            image = Images.BUTTONS.getSubImage(7,0).getScaledCopy(SIZE,SIZE);
        }
        price = 0;

    }

    @Override
    public void onClick() {
        World.quickScroll(where);

    }

    public String getInfo()
    {
        String ret = name;
        return ret;
    }

}
