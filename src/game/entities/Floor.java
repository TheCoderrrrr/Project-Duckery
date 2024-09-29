package game.entities;

import game.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Floor extends Room {

    //int myFloor;
    Room[] rooms;

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