import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import entity.Card;
import entity.CardSuit;
import entity.CardValue;
import entity.Game;
import entity.Table;
import entity.WinCondition;

import static org.junit.Assert.*;

/**
 * Created by Aleksey_Zverkov on 5/7/2015.
 */

@RunWith(Parameterized.class)
public class WinConditionParametrizedTest {

    private Game game = new Game();
    private Table table = new Table();

    private Card[] testedCombination;
    private WinCondition executeCondition;

    @Parameterized.Parameters
    public static Collection<Object[]> tests() {
        return Arrays.asList(
                new Object[][]{
                        {WinCondition.NONE, new Card[]{new Card(CardSuit.SPADES, CardValue.TWO), new Card(CardSuit.SPADES, CardValue.FOUR),
                                new Card(CardSuit.HEARTS, CardValue.ACE), new Card(CardSuit.DIAMONDS, CardValue.SIX), new Card(CardSuit.SPADES, CardValue.EIGHT)}},
                        {WinCondition.PAIR, new Card[]{new Card(CardSuit.SPADES, CardValue.TWO), new Card(CardSuit.SPADES, CardValue.FOUR),
                                new Card(CardSuit.HEARTS, CardValue.TWO), new Card(CardSuit.SPADES, CardValue.SIX), new Card(CardSuit.SPADES, CardValue.EIGHT)}},
                        {WinCondition.TWO_PAIR, new Card[]{new Card(CardSuit.SPADES, CardValue.EIGHT), new Card(CardSuit.CLUBS, CardValue.EIGHT),
                                new Card(CardSuit.SPADES, CardValue.TWO), new Card(CardSuit.HEARTS, CardValue.TWO), new Card(CardSuit.SPADES, CardValue.ACE)}},
                        {WinCondition.TWO_PAIR, new Card[]{new Card(CardSuit.SPADES, CardValue.EIGHT), new Card(CardSuit.CLUBS, CardValue.EIGHT),
                                new Card(CardSuit.SPADES, CardValue.TWO), new Card(CardSuit.HEARTS, CardValue.TWO), new Card(CardSuit.SPADES, CardValue.ACE)}},
                        {WinCondition.THREE_OF_KIND, new Card[]{new Card(CardSuit.SPADES, CardValue.EIGHT), new Card(CardSuit.CLUBS, CardValue.EIGHT),
                                new Card(CardSuit.DIAMONDS, CardValue.EIGHT), new Card(CardSuit.HEARTS, CardValue.TWO), new Card(CardSuit.SPADES, CardValue.ACE)}},
                        {WinCondition.FULL_HOUSE, new Card[]{new Card(CardSuit.SPADES, CardValue.EIGHT), new Card(CardSuit.CLUBS, CardValue.EIGHT),
                                new Card(CardSuit.SPADES, CardValue.TWO), new Card(CardSuit.HEARTS, CardValue.TWO), new Card(CardSuit.DIAMONDS, CardValue.TWO)}},
                        {WinCondition.FOUR_OF_KIND, new Card[]{new Card(CardSuit.SPADES, CardValue.EIGHT), new Card(CardSuit.CLUBS, CardValue.EIGHT),
                                new Card(CardSuit.DIAMONDS, CardValue.EIGHT), new Card(CardSuit.HEARTS, CardValue.EIGHT), new Card(CardSuit.SPADES, CardValue.ACE)}},
                        {WinCondition.FLUSH, new Card[]{new Card(CardSuit.SPADES, CardValue.EIGHT), new Card(CardSuit.SPADES, CardValue.TWO),
                                new Card(CardSuit.SPADES, CardValue.FIVE), new Card(CardSuit.SPADES, CardValue.KING), new Card(CardSuit.SPADES, CardValue.ACE)}},
                        {WinCondition.STRAIGHT_FLUSH, new Card[]{new Card(CardSuit.SPADES, CardValue.EIGHT), new Card(CardSuit.SPADES, CardValue.NINE),
                                new Card(CardSuit.SPADES, CardValue.FIVE), new Card(CardSuit.SPADES, CardValue.SIX), new Card(CardSuit.SPADES, CardValue.SEVEN)}},
                        {WinCondition.ROYAL_FLUSH, new Card[]{new Card(CardSuit.SPADES, CardValue.TEN), new Card(CardSuit.SPADES, CardValue.ACE),
                                new Card(CardSuit.SPADES, CardValue.JACK), new Card(CardSuit.SPADES, CardValue.KING), new Card(CardSuit.SPADES, CardValue.QUEEN)}}
                }
        );
    }

    public WinConditionParametrizedTest(WinCondition executeCondition, Card[] testedCombination) {
        this.testedCombination = testedCombination;
        this.executeCondition = executeCondition;
    }

    @Test
    public void testWinCondition() {
        table.setCardsOnDesk(testedCombination);
        assertEquals(executeCondition, game.checkCombination(table));
    }
}
