import entity.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Zveriki on 11.02.2015.
 */
public class WinConditionTest {
    @Test
    public void testFlush(){
        Game game = new Game();
        Table table = new Table();
        createFlush(table.getCardsOnDesk());
        System.out.println(table);
        WinCondition win = game.checkCombination(table);
        System.out.println(win);
        assertNotNull(win);
        assertEquals(win, WinCondition.FLUSH);
    }

    @Test
    public void testStraights(){
        Game game = new Game();
        Table table = new Table();
        WinCondition win;

        createStraights(table.getCardsOnDesk(), false, false);
        System.out.println(table);
        win = game.checkCombination(table);
        System.out.println(win);
        assertNotNull(win);
        assertEquals(win, WinCondition.STRAIGHT);

        createStraights(table.getCardsOnDesk(), true, false);
        System.out.println(table);
        win = game.checkCombination(table);
        System.out.println(win);
        assertNotNull(win);
        assertEquals(win, WinCondition.STRAIGHT_FLUSH);

        createStraights(table.getCardsOnDesk(), true, true);
        System.out.println(table);
        win = game.checkCombination(table);
        System.out.println(win);
        assertNotNull(win);
        assertEquals(win, WinCondition.ROYAL_FLUSH);
    }

    private void createFlush (Card[] cards){
        for (int i=0; i<5; i++){
            cards[i] = new Card(CardSuit.CLUBS, CardValue.ACE);
        }
    }

    private void createStraights(Card[] cards, boolean flush, boolean royal){
        for (int i=0; i<5; i++){
            Card card = new Card();
            card.setSuit(!flush?i>2?CardSuit.CLUBS:CardSuit.DIAMONDS: CardSuit.DIAMONDS);
            card.setValue(CardValue.values()[royal?i+8:i]);
            cards[i] = card;
        }
    }
}
