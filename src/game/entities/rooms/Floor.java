package game.entities.rooms;

import game.World;
import game.RoomManager;

public class Floor extends Room {

    //int myFloor;
    Room[] rooms;
    int index = RoomManager.FLOOR_UNLOCK_ROOMS;

    public Floor(int myFloor) {
        super(myFloor, 0);
        this.myFloor = myFloor;
        rooms = new Room[World.ROOMS_IN_FLOOR];
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = new Room(myFloor, i);
        }
        maxDucks = 6;
        width = 800;

    }
}