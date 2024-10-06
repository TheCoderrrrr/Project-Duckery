package game.entities.roomButtons;

import core.Images;
import game.ResourceManager;
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
        price = 20;
        myImage = Images.BREAD_BUTTONS.getSubImage(i.getImageIndex(),0);
        info = "change room product to "+ item.getName() +"?\n"+"Will take "+ProductRoom.TOTAL_BUILD_TIME/60+" seconds"+
                "\nprice: $"+price;



    }
    @Override
    public void action() {

        if (ResourceManager.getFunds()>=price)
        {

            ResourceManager.withdraw(20);
            r.switchProduct(i);
        }

    }
}
