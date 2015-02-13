package entity;

/**
 * Created by Zveriki on 09.02.2015.
 */
public class Card {

    private CardSuit suit;

    private CardValue value;

    public Card() {
    }

    public Card(CardSuit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card[suit=" + suit + ", value=" + value + "]";
    }

}
