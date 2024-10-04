package game.entities.roomButtons;

import core.Images;
import game.clipboard.items.Item;
import game.entities.rooms.ProductRoom;
import game.entities.rooms.Room;

public class ChangeRoomButton extends RoomButton{
    ProductRoom r;
    Item i;


    public ChangeRoomButton(ProductRoom room, Item item, int height)
    {
        super(room, height);
        r = room;
        i = item;
        myImage = Images.BREAD_BUTTONS.getSubImage(i.getImageIndex(),0);
        info = "change room product to "+ item.getName() +"?\n"+"Will take "+ProductRoom.TOTAL_BUILD_TIME/60+"seconds";

    }
    @Override
    public void action() {
        r.switchProduct(i);
    }
}
