package game.clipboard.buttons;

import game.PayDay;
import game.ResourceManager;
import game.World;
import game.clipboard.items.Item;
import game.clipboard.menus.ResearchMenu;
import game.entities.rooms.ProductRoom;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ResearchingButton extends BuyingButton {

    protected Item myProduct;
    protected ResearchMenu menu;

    public ResearchingButton(int x, int y, ResearchMenu menu) {
        super(x, y);
        color = Color.white;
        name = "research";
        this.menu = menu;
    }

    public void render(Graphics g)
    {
        super.render(g);

        g.setColor(Color.black);

    }

    @Override
    public void onClick() {
        if (ResourceManager.getFunds()>price)
        {
            ProductRoom.addProduct(myProduct);
            ResourceManager.withdraw(price);
            menu.removeItem(this);
            PayDay.setBegin();
        }
    }
}
