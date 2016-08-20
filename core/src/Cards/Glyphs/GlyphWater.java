package Cards.Glyphs;

import Cards.Glyph;
import Cards.Pack;
import Game.Player;
import Game.Target;

/**
 * Created by acurr on 5/30/2016.
 */
public class GlyphWater extends Glyph {


    public GlyphWater() {
        this.name = "Water Glyph";
        this.target = Target.SELF;
        this.amount = 1;
        this.setSetName(Pack.GENESIS);
    }

    public void effect(Player player) {
        player.getGlyphs().add(Glyphtypes.WATER);
        player.setMaxMana(player.getMaxMana() + 1);
    }


    @Override
    public String getDescription() {
        return "Gives you one Glyph of " + Glyphtypes.WATER + " and increases your maximum mana by one";
    }
}
