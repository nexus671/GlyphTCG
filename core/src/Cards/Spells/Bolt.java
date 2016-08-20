package Cards.Spells;

import Cards.Pack;
import Cards.Spell;
import Cards.Troop;
import Game.Player;
import Game.Target;


/**
 * Created by acurr on 5/29/2016.
 */
public class Bolt extends Spell {
    int damage = 2;
    // public void effect(ListTroops troops) {
    //    troops.get().forEach( t -> t.setCurrentHealth(t.getCurrentHealth()-damage));
    // }
    // public void effect(ListPlayers players) {
    //     players.get().forEach(p -> p.setHealth(p.getHealth()-damage));
    //}

    public Bolt() {
        this.setName("Bolt");
        this.setAmount(1);
        this.setTarget(Target.ANY);
        this.setCost(1);
        this.setSetName(Pack.GENESIS);
    }

    public void effect(Player player) {
        player.setHealth(player.getHealth() - damage);
    }

    public void effect(Troop troop) {
        troop.setCurrentHealth(troop.getCurrentHealth() - damage);
    }

    @Override
    public String getDescription() {
        return "Deals " + damage + " to a Troop or Player";
    }
}
