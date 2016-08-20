package Cards.Troops;

import Cards.Pack;
import Cards.Troop;

/**
 * Created by acurr on 5/25/2016.
 */
public class Slime extends Troop {


    public Slime() {

        this.setName("Slime");
        this.setCost(1);
        this.setDamage(1);
        this.setExhausted(true);
        this.setMaxHealth(1);
        this.setCurrentHealth(getMaxHealth());
        this.setSetName(Pack.GENESIS);
        this.setSpriteName("createcard");

    }


    @Override
    public String getDescription() {
        return "A Slime";
    }
}
