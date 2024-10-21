package game.entities.rooms;

import game.World;
import game.RoomManager;

public class Floor extends ProductRoom {


    int index = RoomManager.FLOOR_UNLOCK_ROOMS;

    //a floor is a  longer room
    public Floor(int myFloor) {
        super(myFloor, 0);

        this.myFloor = myFloor;
        maxDucks = 6;
        width = 800;
       myRoomTypes = myFloorTypes;
       if (myFloor>0)
       {
           myImage = myFloorTypes.getSubImage(products.get(curItem).getImageIndex(),0);
       }
       else {
           myImage = myFloorTypes.getSubImage(productsUG.getFirst().getImageIndex(),0);
       }


    }
}