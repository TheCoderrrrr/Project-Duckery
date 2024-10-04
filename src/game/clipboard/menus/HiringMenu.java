package game.clipboard.menus;

import game.clipboard.buttons.HiringButton;
import game.clipboard.buttons.addBuilding.AddBasementButton;
import game.clipboard.buttons.addBuilding.AddFloorButton;
import game.clipboard.buttons.addBuilding.AddRoomButton;
import game.entities.roomButtons.RoomButton;

public class HiringMenu extends Menu{
    AddRoomButton r;
    boolean hasFloorBut;
    boolean hasBaseBut;

    public HiringMenu()
    {
        super(0);
        mySize = 1;
        title = "hire menu";
        r = new AddRoomButton(X_LEFT,myY);

        myButtons.add( new HiringButton(X_LEFT, myY));
        myButtons.add(r);
//        myButtons.add( new AddFloorButton(X_LEFT, myY));
//        myButtons.add( new AddBasementButton(X_LEFT, myY));

        setButtonX();

    }

    public void addFloorButton()
    {
        if (!hasFloorBut)
        {
            myButtons.remove(r);
            myButtons.add( new AddFloorButton(X_LEFT, myY));
            setButtonX();
            hasFloorBut = true;
        }


    }

    public void addBasement()
    {
        if (!hasBaseBut)
        {
            myButtons.add( new AddBasementButton(X_LEFT, myY));
            setButtonX();
            hasBaseBut = true;
        }

    }
    //public void makeButtons() {}
}
