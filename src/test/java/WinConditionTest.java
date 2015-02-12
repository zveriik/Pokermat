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
    public void testStraight(){
        Game game = new Game();
        Table table = new Table();
        createStraight(table.getCardsOnDesk());
        System.out.println(table);
        WinCondition win = game.checkCombination(table);
        System.out.println(win);
        assertNotNull(win);
        assertEquals(win, WinCondition.STRAIGHT);
    }

    private void createFlush (Card[] cards){
        for (int i=0; i<5; i++){
            cards[i] = new Card(CardSuit.CLUBS, CardValue.ACE);
        }
    }

    private void createStraight (Card[] cards){
        for (int i=0; i<5; i++){
            Card card = new Card();
            card.setSuit(i>2?CardSuit.CLUBS:CardSuit.DIAMONDS);
            card.setValue(CardValue.values()[i]);
            cards[i] = card;
        }
    }
}
