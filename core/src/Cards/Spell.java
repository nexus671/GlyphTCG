package Cards;

import Game.Player;
import Game.Target;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by acurr on 5/29/2016.
 */
public class Spell extends Card {
    protected Target target;
    protected int amount;

    public Spell() {
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void effect(ListPlayers players) {
    }

    public void effect(ListTroops troops) {
    }

    public void effect(Player player) {
    }

    public void effect(Troop troop) {
    }

    @Override
    public String toString() {
        return "(" + name + " Glyphs " + getThresholds() + " Desc: " + getDescription() + ")";
    }

    @Override
    public String getDescription() {
        return null;
    }


    public interface ListTroops extends Supplier<List<Troop>> {
    }

    public interface ListPlayers extends Supplier<List<Player>> {
    }
}
