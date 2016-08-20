package Cards;

import Game.Field;
import Game.Listener;
import Game.Player;
import com.badlogic.gdx.graphics.Texture;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by acurr on 5/25/2016.
 */
public abstract class Card extends Listener implements Serializable {
    protected String cardBack = "Cardback.jpg";
    protected Texture cardFront;
    protected String name;
    protected int cost;
    protected Set<Glyph.Glyphtypes> thresholds = new HashSet<Glyph.Glyphtypes>();
    protected boolean exhausted;
    protected Pack setName;
    protected String spriteName;
    protected Field field;
    protected String species;
    protected Player player;

    public Card() {

    }

    @Override
    public void update() {
        super.update();
    }

    public void placeCard(Player player) {
        this.player = player;
    }

    public Pack getSetName() {
        return setName;
    }

    public void setSetName(Pack setName) {
        this.setName = setName;
    }
    // public static Texture getCardBack() {return cardBack;}

    // public static void setCardBack(Texture cardBack) {Card.cardBack = cardBack;}

    public Texture getCardFront() {
        return cardFront;
    }

    public void setCardFront(Texture cardFront) {
        this.cardFront = cardFront;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Set<Glyph.Glyphtypes> getThresholds() {
        return thresholds;
    }

    public void setThresholds(Set<Glyph.Glyphtypes> thresholds) {
        this.thresholds = thresholds;
    }

    public boolean isExhausted() {
        return exhausted;
    }

    public void setExhausted(boolean exhausted) {
        this.exhausted = exhausted;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public abstract String getDescription();

    public String getSpriteName() {
        return spriteName + ".jpg";
    }

    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }

    public String getCardBack() {
        return cardBack;
    }

    public void setCardBack(String cardBack) {
        this.cardBack = cardBack;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", thresholds=" + thresholds +
                ", exhausted=" + exhausted +
                '}';
    }
}
