package game.clipboard.buttons;

import game.MoneyManager;
import game.clipboard.items.Item;
import game.entities.Room;
import org.newdawn.slick.Color;

public class ResearchingButton extends BuyingButton {

    protected Item myProduct;

    public ResearchingButton(int x, int y) {
        super(x, y);
        color = Color.white;
        name = "research";
        price = 20;
    }


    @Override
    public void onClick() {
        super.onClick();
        if (MoneyManager.getFunds()>price)
        {
            Room.addProduct(myProduct);
            MoneyManager.withdraw(price);
        }
    }
}
