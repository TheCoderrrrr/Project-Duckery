package game.entities.rooms;

import game.World;
import game.RoomManager;

public class Floor extends ProductRoom {

    //int myFloor;
    ProductRoom[] rooms;
    int index = RoomManager.FLOOR_UNLOCK_ROOMS;

    public Floor(int myFloor) {
        super(myFloor, 0);
        this.myFloor = myFloor;
        rooms = new ProductRoom[World.ROOMS_IN_FLOOR];
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = new ProductRoom(myFloor, i);
        }
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