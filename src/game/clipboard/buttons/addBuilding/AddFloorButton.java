package game.clipboard.buttons.addBuilding;

import core.Images;
import game.ResourceManager;
import game.RoomManager;
import game.clipboard.buttons.BuyingButton;
import game.paydayButtons.PayRoomTax;
import org.newdawn.slick.Color;

public class AddFloorButton extends BuyingButton {
    public AddFloorButton(int x, int y) {

        super(x, y);
        color = Color.gray;
        name = "Buy Floor";
        price = RoomManager.getCurFloorPrice();
        info = "Costs "+ PayRoomTax.ROOM_TAX+" per day";
        image = Images.BUTTONS.getSubImage(0,0).getScaledCopy(SIZE,SIZE);
    }

    @Override
    public void onClick() {
        RoomManager.addFloor();
        price = RoomManager.getCurFloorPrice();
    }
}
