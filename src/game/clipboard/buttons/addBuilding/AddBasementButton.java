package game.clipboard.buttons.addBuilding;

import core.Images;
import game.RoomManager;
import game.clipboard.buttons.BuyingButton;
import game.paydayButtons.PayRoomTax;
import org.newdawn.slick.Color;

public class AddBasementButton  extends BuyingButton {

    public AddBasementButton(int x, int y) {

        super(x, y);
        color = Color.gray;
        name = "Buy Basement";
        price =RoomManager.getCurBasementPrice();
        info = "Costs "+ PayRoomTax.ROOM_TAX+" per day";
        image = Images.BUTTONS.getSubImage(2,0).getScaledCopy(SIZE,SIZE);
    }

    @Override
    public void onClick() {
        RoomManager.addBasement();
        price =RoomManager.getCurBasementPrice();
    }
}
