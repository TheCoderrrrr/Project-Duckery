package game.entities.roomButtons;

import game.entities.rooms.ProductRoom;
import game.entities.rooms.Room;

public class ChangeRoomButton extends RoomButton{
    ProductRoom r;
    public ChangeRoomButton(ProductRoom room)
    {
        super(room);
        r = room;
    }
    @Override
    public void action() {
        r.switchProduct();
    }
}
