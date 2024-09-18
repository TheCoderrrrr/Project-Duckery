package game.clipboard.buttons;

import game.MoneyManager;
import game.clipboard.items.Item;
import game.clipboard.menus.Menu;
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
        super.onClick();
        if (MoneyManager.getFunds()>price)
        {
            Room.addProduct(myProduct);
            MoneyManager.withdraw(price);
            menu.removeItem(this);
        }
    }
}
