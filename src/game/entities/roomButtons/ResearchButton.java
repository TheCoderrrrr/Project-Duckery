package game.entities.roomButtons;

import game.entities.rooms.ProductRoom;
import game.entities.rooms.ResearchFloor;
import org.newdawn.slick.Color;

public class ResearchButton extends RoomButton{
    ResearchFloor f;
    public ResearchButton(ResearchFloor researchFloor, int height) {
        super(researchFloor, height);
        f = researchFloor;
        myColor = Color.pink;
        info = "CLICK to research mystery produckt!";
    }

    @Override
    public void action() {
        f.beginResearch();
    }
}
