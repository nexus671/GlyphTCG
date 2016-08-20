package Cards.Glyphs;

import Cards.Glyph;
import Cards.Pack;
import Game.Player;
import Game.Target;

/**
 * Created by acurr on 5/30/2016.
 */
public class GlyphHallow extends Glyph {


    public GlyphHallow() {
        this.name = "Hallow Glyph";
        this.target = Target.SELF;
        this.amount = 1;
        this.setSetName(Pack.GENESIS);
    }

    public void effect(Player player) {
        player.getGlyphs().add(Glyphtypes.HALLOW);
        player.setMaxMana(player.getMaxMana() + 1);
    }


    @Override
    public String getDescription() {
        return "Gives you one Glyph of " + Glyphtypes.HALLOW + " and increases your maximum mana by one";
    }
}
