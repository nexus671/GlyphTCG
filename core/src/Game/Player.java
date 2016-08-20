package Game;

import Cards.Card;
import Cards.Glyph;
import Cards.Troop;
import Cards.Troops.Slime;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by acurr on 5/25/2016.
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private Deck activeDeck;
    private List<Card> hand;
    private List<Glyph.Glyphtypes> glyphs;
    private Trunk trunk;
    private Field battlefield;
    private Set<Listener> placecardListeners = new HashSet<Listener>();
    //Summoner spells TODO: 5/27/2016

    private String name;
    private int health;
    private int mana;
    private int maxMana;

    public Player(String name) {
        this.name = name;
        this.health = 20;
        this.mana = 1;
        setMaxMana(mana);
        activeDeck = new Deck();
        trunk = new Trunk();
        glyphs = new ArrayList<Glyph.Glyphtypes>();
        hand = new ArrayList<Card>();
        for (int i = 0; i < 20; i++) {
            trunk.addtoCollection(new Slime());
        }
    }

    public static void savePlayer(Player player) throws IOException {
        FileHandle file = Gdx.files.local("player.dat");
        Player savedPlayer = null;
        OutputStream out = null;
        try {
            file.writeBytes(serialize(player), false);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            if (out == null) try {
                out.close();
            } catch (Exception ex) {
            }
        }
        System.out.println("Saving player");
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

    public static Player readPlayer() throws IOException, ClassNotFoundException {
        Player player = null;
        FileHandle file = Gdx.files.local("player.dat");
        player = (Player) deserialize(file.readBytes());
        return player;
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void addlistenerPlaceCard(Listener l) {
        placecardListeners.add(l);
    }

    public void removelistenerPlaceCard(Listener l) {
        placecardListeners.remove(l);
    }

    public Set<Listener> getPlacecardListeners() {
        return placecardListeners;
    }

    public void setPlacecardListeners(Set<Listener> placecardListeners) {
        this.placecardListeners = placecardListeners;
    }

    public void putCardField(Troop c, Field f) {
        f.addTroop(this, c);
        hand.remove(c);
        c.placeCard(this);
        for (Listener d : placecardListeners) {
            d.update();
        }
    }

    public void drawCard(int amount) {
        int i = 0;
        if (activeDeck.isEmpty()) {
            recieveDamage(1);
            return;
        }
        while (i != amount) {
            hand.add(activeDeck.getCard(activeDeck.getSize() - 1));
            activeDeck.removeCard(activeDeck.getCard(activeDeck.getSize() - 1));
            i++;
        }
    }

    public void recieveDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Field getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(Field battlefield) {
        this.battlefield = battlefield;
    }

    public void addCardDeck(Card c) {
        activeDeck.addCard(c);
    }

    public void removeCardDeck(Card c) {
        activeDeck.removeCard(c);
    }

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public void setActiveDeck(Deck activeDeck) {
        this.activeDeck = activeDeck;
    }

    public void setActiveDeck(ArrayList<Card> activeDeck) {
        this.activeDeck = new Deck(activeDeck);
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public List<Glyph.Glyphtypes> getGlyphs() {
        return glyphs;
    }

    public void setGlyphs(List<Glyph.Glyphtypes> glyphs) {
        this.glyphs = glyphs;
    }

    public void setGlyphs(ArrayList<Glyph.Glyphtypes> glyphs) {
        this.glyphs = glyphs;
    }

    public Trunk getTrunk() {
        return trunk;
    }

    public void setTrunk(Trunk trunk) {
        this.trunk = trunk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    @Override
    public String toString() {
        return "Player{" +
                "activeDeck=" + activeDeck +
                ", hand=" + hand +
                ", glyphs=" + glyphs +
                ", trunk=" + trunk +
                ", battlefield=" + battlefield +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", mana=" + mana +
                ", maxMana=" + maxMana +
                '}';
    }
}
