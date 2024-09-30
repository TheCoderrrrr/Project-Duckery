package game.clipboard.buttons;

import game.ResourceManager;
import game.clipboard.items.Item;
import game.clipboard.menus.ResearchMenu;
import game.entities.Room;
import org.newdawn.slick.Color;

public class ResearchingButton extends BuyingButton {

    protected Item myProduct;
    protected ResearchMenu menu;

    public ResearchingButton(int x, int y, ResearchMenu menu) {
        super(x, y);
        color = Color.white;
        name = "research";
        price = 20;
        this.menu = menu;
    }


    @Override
    public void onClick() {
        if (ResourceManager.getFunds()>price)
        {
            Room.addProduct(myProduct);
            ResourceManager.withdraw(price);
            menu.removeItem(this);
        }
    }
}
