package game.clipboard.buttons.addBuilding;

import core.Images;
import game.RoomManager;
import game.clipboard.buttons.BuyingButton;
import game.managers.ResourceManager;
import game.paydayButtons.PayRoomTax;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class AddBasementButton  extends BuyingButton {

    public AddBasementButton(int x, int y) {

        super(x, y);
        color = Color.gray;
        name = "Buy Basement";
        price =RoomManager.getCurBasementPrice();
        info = "Costs "+ PayRoomTax.ROOM_TAX+" per day";
        image = Images.BUTTONS.getSubImage(2,0).getScaledCopy(SIZE,SIZE);
    }

    public void render(Graphics g)
    {
        super.render(g);

        if (price<= ResourceManager.getFunds())
        {
            g.setColor(Color.green);
        }
        else {
            g.setColor(Color.red);
        }
        g.drawString("price: "+price, x,y +image.getHeight());
    }

    @Override
    public void onClick() {
        RoomManager.addBasement();
        price =RoomManager.getCurBasementPrice();
    }
}
