package Cards.Troops;

import Cards.Pack;
import Cards.Troop;
import Game.Player;

/**
 * Created by acurr on 5/25/2016.
 */
public class SlimeEater extends Troop {


    public SlimeEater() {

        this.setName("Slime Eater");
        this.setCost(3);
        this.setDamage(3);
        this.setExhausted(true);
        this.setMaxHealth(1);
        this.setCurrentHealth(getMaxHealth());
        this.setSetName(Pack.GENESIS);
        this.setSpriteName("createcard");

    }

    @Override
    public void placeCard(Player player) {
        super.placeCard(player);
        for (Troop s : field.getPlayersTroops(player)) {
            if (s.getClass() == Slime.class) {
                this.setMaxHealth(this.getMaxHealth() + 1);
                s.setCurrentHealth(0);
            }
        }
        this.setCurrentHealth(this.getMaxHealth());
    }

    @Override
    public String getDescription() {
        return "A natural predator of slimes. Once on the field it consumes all slimes on the players side of the field and gains +1 health for each";
    }
}
