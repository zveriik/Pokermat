package entity;

import java.util.Arrays;

/**
 * Created by Zveriki on 09.02.2015.
 */
public class Table {

    private final Card[] cardsOnDesk = new Card[5];

    public Table() {
    }

    public Card[] getCardsOnDesk() {
        return cardsOnDesk;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("cardsOnDesk: \n");
        for (int i = 0; i < cardsOnDesk.length; i++) {
            str.append("(").append(i + 1).append(") ").append(cardsOnDesk[i].toString()).append("\n");
        }
        return str.toString();
    }
}

