package Cards.Glyphs;

import Cards.Glyph;
import Cards.Pack;
import Game.Player;
import Game.Target;

/**
 * Created by acurr on 5/30/2016.
 */
public class GlyphFire extends Glyph {


    public GlyphFire() {
        this.name = "Fire Glyph";
        this.target = Target.SELF;
        this.amount = 1;
        this.setSetName(Pack.GENESIS);
    }

    public void effect(Player player) {
        player.getGlyphs().add(Glyphtypes.FIRE);
        player.setMaxMana(player.getMaxMana() + 1);
    }


    @Override
    public String getDescription() {
        return "Gives you one Glyph of " + Glyphtypes.FIRE + " and increases your maximum mana by one";
    }
}
