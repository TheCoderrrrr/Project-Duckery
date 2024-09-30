package game.entities.roomButtons;

import game.entities.rooms.Room;

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
