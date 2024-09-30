package game.entities.roomButtons;

import game.entities.rooms.ResearchFloor;
import org.newdawn.slick.Color;

public class ResearchButton extends RoomButton{
    ResearchFloor f;
    public ResearchButton(ResearchFloor researchFloor) {
        super(researchFloor);
        f = researchFloor;
        myColor = Color.pink;
    }

    @Override
    public void action() {
        f.beginResearch();
    }
}
