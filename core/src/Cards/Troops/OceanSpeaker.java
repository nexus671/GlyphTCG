package Cards.Troops;

import Cards.Pack;
import Cards.Troop;
import Game.Player;

/**
 * Created by acurr on 5/25/2016.
 */
public class OceanSpeaker extends Troop {
    int counter = 0;

    public OceanSpeaker() {

        this.setName("Ocean Speaker");
        this.setCost(3);
        this.setDamage(2);
        this.setExhausted(true);
        this.setMaxHealth(3);
        this.setCurrentHealth(getMaxHealth());
        this.setSetName(Pack.GENESIS);
        this.setSpriteName("createcard");
    }

    @Override
    public void placeCard(Player player) {
        super.placeCard(player);
        player.addlistenerPlaceCard(this);
    }

    @Override
    public void update() {
        super.update();
        cardPlaced();
    }

    public void cardPlaced() {
        counter++;
        if (counter == 2) {
            player.drawCard(1);
            counter = 0;
        }
    }


    @Override
    public String getDescription() {
        return "A Creatue that can speak to vast ocean. For every two cards that you place you draw one";
    }
}
