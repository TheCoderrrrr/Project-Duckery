package game.entities.roomButtons;

import game.entities.Room;

public class ChangeRoomButton extends RoomButton{
    public ChangeRoomButton(Room room)
    {
        super(room);
    }
    @Override
    public void action() {
        room.switchProduct();
    }
}
