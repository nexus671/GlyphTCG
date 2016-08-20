package Cards;

import Game.Player;
import Game.Target;

/**
 * Created by acurr on 5/25/2016.
 */
public class Glyph extends Card {
    protected Target target;
    protected int amount;

    public Glyph() {
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public void effect(Player player) {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String getDescription() {
        return null;
    }

    public enum Glyphtypes {
        FIRE, AIR, WATER, EARTH, SHADOW, HALLOW
    }
}
