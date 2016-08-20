package Game;

import Cards.Card;
import Cards.Glyph;
import Cards.Spell;
import Cards.Troop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by acurr on 6/6/2016.
 */
public class Deck implements Serializable {
    public static int MAXDUPLICATE = 4;
    String name = "";
    private List<Card> cards = new ArrayList<Card>();
    private List<Troop> troops = new ArrayList<Troop>();
    private List<Spell> spells = new ArrayList<Spell>();
    //implment costtotal list

    private double airRatio;
    private double earthRatio;
    private double fireRatio;
    private double hallowRatio;
    private double shadowRatio;
    private double waterRatio;
    private int amountTroops;
    private int amountSpells;

    public Deck(List<Card> cards) {
        this.cards = cards;
        calculateAmountTroops();
        calculateAmountSpells();
    }

    private void calculateAmountTroops() {
        for (Card c : cards) {
            if (c.getClass() == Troop.class) {
                amountTroops++;
                troops.add((Troop) c);
            }
        }
    }

    private void calculateAmountSpells() {
        for (Card c : cards) {
            if (c.getClass() == Spell.class) {
                amountSpells++;
                spells.add((Spell) c);
            }

        }

    }

    public Deck() {
        this.airRatio = 0;
        this.earthRatio = 0;
        this.fireRatio = 0;
        this.hallowRatio = 0;
        this.shadowRatio = 0;
        this.waterRatio = 0;
        this.amountTroops = 0;
        this.amountSpells = 0;
    }

    public static int getMAXDUPLICATE() {
        return MAXDUPLICATE;
    }

    public static void setMAXDUPLICATE(int MAXDUPLICATE) {
        Deck.MAXDUPLICATE = MAXDUPLICATE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountSpells() {
        return amountSpells;
    }

    public void setAmountSpells(int amountSpells) {
        this.amountSpells = amountSpells;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public boolean addCard(Card card) {
        int amount = 0;
        for (Card c : cards) {
            if (c.getClass() == card.getClass()) {
                amount++;
            }
            if (amount == 4) {
                return false;
            }
        }
        cards.add(card);
        return true;
    }

    public void removeCard(Card card) {
        cards.remove(card);
        if (card.getClass() == Troop.class) {
            troops.remove((Troop) card);
        } else if (card.getClass() == Spell.class) {
            spells.remove((Spell) card);
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    private void calculateRatios() {
        List<Glyph.Glyphtypes> glyphsTypes = new ArrayList<Glyph.Glyphtypes>();
        for (Card c : cards) {
            glyphsTypes.addAll(c.getThresholds());
        }
        double air = 0, water = 0, fire = 0, earth = 0, hallow = 0, shadow = 0;
        for (Glyph.Glyphtypes g : glyphsTypes) {
            if (g.equals(Glyph.Glyphtypes.AIR)) {
                air++;
            } else if (g.equals(Glyph.Glyphtypes.EARTH)) {
                earth++;
            } else if (g.equals(Glyph.Glyphtypes.FIRE)) {
                fire++;
            } else if (g.equals(Glyph.Glyphtypes.WATER)) {
                water++;
            } else if (g.equals(Glyph.Glyphtypes.HALLOW)) {
                hallow++;
            } else if (g.equals(Glyph.Glyphtypes.SHADOW)) {
                shadow++;
            }
        }
        setAirRatio(air / getSize());
        setWaterRatio(water / getSize());
        setFireRatio(fire / getSize());
        setEarthRatio(earth / getSize());
        setHallowRatio(hallow / getSize());
        setShadowRatio(shadow / getSize());

    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Troop> getTroops() {
        return troops;
    }

    public void setTroops(List<Troop> troops) {
        this.troops = troops;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }


    public double getAirRatio() {
        return airRatio;
    }

    public void setAirRatio(double airRatio) {
        this.airRatio = airRatio;
    }

    public double getEarthRatio() {
        return earthRatio;
    }

    public void setEarthRatio(double earthRatio) {
        this.earthRatio = earthRatio;
    }

    public double getFireRatio() {
        return fireRatio;
    }

    public void setFireRatio(double fireRatio) {
        this.fireRatio = fireRatio;
    }

    public double getHallowRatio() {
        return hallowRatio;
    }

    public void setHallowRatio(double hallowRatio) {
        this.hallowRatio = hallowRatio;
    }

    public double getShadowRatio() {
        return shadowRatio;
    }

    public void setShadowRatio(double shadowRatio) {
        this.shadowRatio = shadowRatio;
    }

    public double getWaterRatio() {
        return waterRatio;
    }

    public void setWaterRatio(double waterRatio) {
        this.waterRatio = waterRatio;
    }

    public int getAmountTroops() {
        return amountTroops;
    }

    public void setAmountTroops(int amountTroops) {
        this.amountTroops = amountTroops;
    }

    public int getAmmountSpells() {
        return amountSpells;
    }

    public void setAmmountSpells(int ammountSpells) {
        this.amountSpells = ammountSpells;
    }

}
