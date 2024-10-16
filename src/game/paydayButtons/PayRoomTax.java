package game.paydayButtons;

import game.RoomManager;
import game.World;

public class PayRoomTax extends PaydayButton {

    final static public int ROOM_TAX = 10;

    public PayRoomTax()
    {
        super();
        name = "Property Tax";
    }

    @Override
    public void calculatePrice() {
        super.calculatePrice();
        price = RoomManager.getTotalRooms()*ROOM_TAX;
    }

}

