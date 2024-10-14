package game.paydayButtons;

import game.World;

public class PayDucks extends PaydayButton{

    final static public int DUCK_WAGE = 5;

    public PayDucks()
    {
        super();
        name = "Duck Wages";
    }

    @Override
    public void calculatePrice() {
        super.calculatePrice();
        price = World.getTotalDucks()*DUCK_WAGE;
    }
}
