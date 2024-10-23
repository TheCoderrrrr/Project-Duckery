package game.clipboard.buttons.addBuilding;

import core.Images;
import game.managers.ResourceManager;
import game.clipboard.buttons.BuyingButton;
import game.paydayButtons.PayRoomTax;
import org.newdawn.slick.Color;
import game.RoomManager;

public class AddRoomButton extends BuyingButton {

    public AddRoomButton(int x, int y) {
        super(x, y);
        color = Color.gray;
        name = "Buy Room";
        price = RoomManager.getCurRoomPrice();
        info = "Costs "+ PayRoomTax.ROOM_TAX+" per day";
        image = Images.BUTTONS.getSubImage(1,0).getScaledCopy(SIZE,SIZE);

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
