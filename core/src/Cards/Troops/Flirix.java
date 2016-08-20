package Cards.Troops;

import Cards.Glyph;
import Cards.Pack;
import Cards.Troop;
import Game.Player;

/**
 * Created by acurr on 5/25/2016.
 */
public class Flirix extends Troop {

    int instantdamange = 2;

    public Flirix() {

        this.setName("Flirix");
        this.setCost(2);
        this.thresholds.add(Glyph.Glyphtypes.FIRE);
        this.setDamage(2);
        this.setExhausted(true);
        this.setMaxHealth(1);
        this.setCurrentHealth(getMaxHealth());
        this.setSetName(Pack.GENESIS);
        this.setSpriteName("createcard");
    }

    public void placeCard(Player player) {
        super.placeCard(player);
        Player defender = field.getOpposingPlayer(player);
        defender.recieveDamage(instantdamange);
    }

    @Override
    public String getDescription() {
        return "A being made of volatile yellow fire. Deals " + instantdamange + "to the opposing player upon being played.";
    }
}
