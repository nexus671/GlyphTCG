package Cards.Troops;

import Cards.Pack;
import Cards.Troop;
import Game.Player;

/**
 * Created by acurr on 5/25/2016.
 */
public class WhirlingDervish extends Troop {


    public WhirlingDervish() {

        this.setName("Whirling Dervish");
        this.setCost(1);
        this.setDamage(2);
        this.setExhausted(true);
        this.setMaxHealth(1);
        this.setCurrentHealth(getMaxHealth());
        this.setSetName(Pack.GENESIS);
        this.setSpriteName("createcard");

    }

    @Override
    public void attack(Player player) {
        super.attack(player);
        setCurrentHealth(0);
    }

    @Override
    public void attack(Troop troop) {
        super.attack(troop);
        setCurrentHealth(0);
    }

    @Override
    public String getDescription() {
        return "A Whirling Dervish it dissipates after it attacks. Health is set to 0 once it attacks.";
    }
}
