package game.paydayButtons;

import game.World;

public class PayDucks extends PaydayButton{

    final static public int DUCK_WAGE = 10;

    public PayDucks()
    {
        super();
        name = "ducks";
    }

    @Override
    public void calculatePrice() {
        super.calculatePrice();
        price = World.getTotalDucks()*DUCK_WAGE;
    }
}
