package entity;

import entity.*;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Zveriki on 09.02.2015.
 */
public class Game {

    private Player player;

    public Game() {
    }

    public Game(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Integer> readHolds() {
        List<Integer> choose = new ArrayList<Integer>();
        boolean valid = false;
        while (!valid) {
            Scanner input = new Scanner(System.in);
            System.out.print("Choose Holds [1-5]: ");
            String numberString = input.nextLine();
            numberString.replaceAll("\\D", "");
            char[] charArray = numberString.toCharArray();
            System.out.println(charArray);
            for (int i = 0; i < charArray.length; i++) {
                choose.add(Character.getNumericValue(charArray[i]));
                valid = choose.get(i) <= 5;
            }
            if (!valid) choose.clear();
        }
        List<Integer> holds = new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5}));
        holds.removeAll(choose);
        return holds;
    }

    public Deck createDeck() {
        LinkedList<Card> deck = new LinkedList<Card>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                deck.add(new Card(suit, value));
            }
        }
        return new Deck(deck);
    }

    public Deck shuffleDeck(Deck deck) {
        LinkedList<Card> cards = deck.getDeck();
        Collections.shuffle(cards, new Random(System.nanoTime()));
        deck.setDeck(cards);
        return deck;
    }

    public WinCondition checkCombination(Table table) {
        Card[] cardsOnDesk = table.getCardsOnDesk();
        WinCondition win = checkForKind(cardsOnDesk);
        return win;
    }

    private WinCondition checkForFlushAndStraight(Card[] cards) {
        boolean flush = false;
        List<Card> checkList = Arrays.asList(cards);

        Collections.sort(checkList, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getSuit().ordinal()>o2.getSuit().ordinal()?1:o1.getSuit().ordinal() <o2.getSuit().ordinal()?-1:0;
            }
        });

        if (checkList.get(0).getSuit() == checkList.get(4).getSuit()) flush = true;

        Collections.sort(checkList, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getValue().ordinal()>o2.getValue().ordinal()?1:o1.getValue().ordinal() <o2.getValue().ordinal()?-1:0;
            }
        });

        if (checkList.get(4).getValue().ordinal() - checkList.get(0).getValue().ordinal() == 4)
            return flush ? checkList.get(4).getValue() == CardValue.ACE ? WinCondition.ROYAL_FLUSH : WinCondition.STRAIGHT_FLUSH : WinCondition.STRAIGHT;
        return flush?WinCondition.FLUSH:WinCondition.NONE;
    }

    private WinCondition checkForKind(Card[] cards) {

        Map<CardValue, Integer> index = new HashMap<CardValue, Integer>();

        for (Card card : cards) {
            Integer count = index.get(card.getValue());
            count = count == null ? 1 : count + 1;
            index.put(card.getValue(), count);
        }

        if (index.containsValue(4)) return WinCondition.FOUR_OF_KIND;
        if (index.containsValue(3) && index.size() == 3) return WinCondition.THREE_OF_KIND;
        if (index.containsValue(2)) {
            if (index.containsValue(3)) return WinCondition.FULL_HOUSE;
            if (index.size() == 3) return WinCondition.TWO_PAIR;
            return WinCondition.PAIR;
        }

        return checkForFlushAndStraight(cards);
    }
}
