package Game;

import Cards.Card;
import Cards.Troops.Slime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acurr on 6/6/2016.
 */
public class Trunk implements Serializable {
    private Player owner;
    private List<Card> collection;
    private List<Deck> decks;

    public Trunk() {
        collection = new ArrayList<Card>();
        decks = new ArrayList<Deck>();
        collection.add(new Slime());
    }

    public void addtoCollection(Card card) {
        collection.add(card);

    }

    public void removefromCollection(Card card) {
        collection.remove(card);
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    public void removeDeck(Deck deck) {
        decks.remove(deck);
    }

    public List<Card> getCollection() {
        return collection;
    }

    public void setCollection(List<Card> collection) {
        this.collection = collection;
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }
}
