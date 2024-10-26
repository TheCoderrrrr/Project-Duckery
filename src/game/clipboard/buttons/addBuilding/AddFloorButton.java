package game.clipboard.buttons.addBuilding;

import core.Images;
import game.RoomManager;
import game.clipboard.buttons.BuyingButton;
import game.managers.ResourceManager;
import game.paydayButtons.PayRoomTax;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class AddFloorButton extends BuyingButton {
    public AddFloorButton(int x, int y) {

        super(x, y);
        color = Color.gray;
        name = "Buy Floor";
        price = RoomManager.getCurFloorPrice();
        info = "Costs "+ PayRoomTax.ROOM_TAX+" per day";
        image = Images.BUTTONS.getSubImage(0,0).getScaledCopy(SIZE,SIZE);
    }

    public void render(Graphics g)
    {
        super.render(g);
        if (price< ResourceManager.getFunds())
        {
            g.setColor(Color.green);
        }
        else {
            g.setColor(Color.red);
        }
        g.drawString("price: "+price, x,y +image.getHeight());
    }

    @Override
    public void onClick() {
        if (ResourceManager.getFunds()>price) {
            RoomManager.addFloor();
            price = RoomManager.getCurFloorPrice();
        }

    }
}
