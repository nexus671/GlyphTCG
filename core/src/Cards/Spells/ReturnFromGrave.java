package Cards.Spells;

import Cards.Pack;
import Cards.Spell;
import Cards.Troop;
import Game.Player;
import Game.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by acurr on 5/31/2016.
 */
public class ReturnFromGrave extends Spell {

    public ReturnFromGrave() {
        this.setName("Return From the Grave");
        this.setAmount(1);
        this.setTarget(Target.SELF);
        this.setCost(1);
        this.setSetName(Pack.GENESIS);

    }

    public void effect(Player player) {
        List<Troop> grave = player.getBattlefield().getGrave();
        List<Troop> pool = new ArrayList<Troop>();
        for (Troop t : grave) {
            if (t.getCost() <= 2)
                pool.add(t);
        }
        if (pool.isEmpty()) {
            System.out.println("No cards in the grave that cost 2 or less");
            return;
        }
        Random random = new Random();
        Troop t = pool.get(random.nextInt(pool.size()));
        t.setCurrentHealth(t.getMaxHealth());
        t.setExhausted(true);
        player.getBattlefield().getPlayersTroops(player).add(t);

    }

    @Override
    public String getDescription() {
        return "Summons one random troop from the grave that costs 2 or less ";
    }
}
