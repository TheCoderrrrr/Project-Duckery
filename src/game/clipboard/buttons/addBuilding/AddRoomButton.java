package game.clipboard.buttons.addBuilding;

import core.Images;
import game.managers.ResourceManager;
import game.clipboard.buttons.BuyingButton;
import game.paydayButtons.PayRoomTax;
import org.newdawn.slick.Color;
import game.RoomManager;
import org.newdawn.slick.Graphics;

public class AddRoomButton extends BuyingButton {

    public AddRoomButton(int x, int y) {
        super(x, y);
        color = Color.gray;
        name = "Buy Room";
        price = RoomManager.getCurRoomPrice();
        info = "Costs "+ PayRoomTax.ROOM_TAX+" per day";
        image = Images.BUTTONS.getSubImage(1,0).getScaledCopy(SIZE,SIZE);

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
        if (ResourceManager.getFunds()>RoomManager.getCurRoomPrice()) {
            RoomManager.addRoom();
            ResourceManager.withdraw(price);
            price = RoomManager.getCurRoomPrice();

        }

    }

}
