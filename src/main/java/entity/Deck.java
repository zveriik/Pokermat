package entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zveriki on 09.02.2015.
 */
public class Deck {

    private LinkedList<Card> deck;

    public Deck() {
    }

    public Deck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public LinkedList<Card> getDeck() {
        return deck;
    }

    public void setDeck(LinkedList<Card> deck) {
        this.deck = deck;
    }

    public Card removeFirst() {
        return deck.removeFirst();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Deck[");
        for (Card card : deck) {
            str.append(card.toString());
        }
        str.append("]");
        return str.toString();
    }
}
