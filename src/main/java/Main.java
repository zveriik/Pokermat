import entity.*;

import javax.xml.soap.SOAPPart;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zveriki on 09.02.2015.
 */
public class Main {
    public static void main(String[] args) {

        Player player = new Player("Noob");
        player.setCash(5);
        player.setBet(1);
        Game game = new Game(player);

        Deck deck = game.createDeck();
        game.shuffleDeck(deck);

        System.out.println(deck);
        System.out.println();

        System.out.println(game.getPlayer());

        Table table = new Table();
        int round = 0;

        while (player.getCash() > 0) {

            Card[] cardsOnDesk = table.getCardsOnDesk();
            for (int i = 0; i < cardsOnDesk.length; i++) {
                cardsOnDesk[i] = deck.removeFirst();
            }

            System.out.println(table);
//            System.out.println(deck.getDeck().size());

            List<Integer> holds = game.readHolds();
            System.out.println(holds);

            for (Integer index : holds) {
                deck.getDeck().add(cardsOnDesk[index - 1]);
                cardsOnDesk[index - 1] = deck.removeFirst();
            }

            System.out.println(table);
            System.out.println(deck.getDeck().size());

            WinCondition winCondition = game.checkCombination(table);
            game.getPlayer().setCash(game.getPlayer().getCash() + winCondition.getCost() * game.getPlayer().getBet());
            System.out.println(winCondition);
            System.out.println(game.getPlayer());

            deck.getDeck().addAll(Arrays.asList(cardsOnDesk));
            round++;
            if (round > 10) {
                game.shuffleDeck(deck);
                System.out.println("Deck shuffled");
                round = 0;
            }
        }
        System.out.println("You lost");
    }
}
