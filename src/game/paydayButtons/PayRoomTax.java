package game.paydayButtons;

import game.World;

public class PayRoomTax extends PaydayButton {

    final static public int ROOM_TAX = 70;

    public PayRoomTax()
    {
        super();
        name = "rooms";
    }

    @Override
    public void calculatePrice() {
        super.calculatePrice();
        price = World.getTotalRooms()*ROOM_TAX;
    }

}

