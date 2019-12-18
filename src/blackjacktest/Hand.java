package blackjacktest;

import java.util.ArrayList;

public class Hand {

    private static int UPCARD_INDEX = 0;
    private ArrayList<Integer> cards = new ArrayList<>();

    public static Hand pullHand(Deck deck) {
        return new Hand(deck.pullNextCard(), deck.pullNextCard());
    }

    public Hand(int upcard, int firstDownCard) {
        cards.add(upcard);
        cards.add(firstDownCard);
    }

    public void addCard(int card) {
        cards.add(card);
    }

    public int[] getCardsArray() {
        int[] cardsArray = new int[this.cards.size()];
        int i=0;
        for (int card : cards) {
            cardsArray[i++] = card;
        }
        return cardsArray;
    }

    private int getUpcard() {
        return cards.get(UPCARD_INDEX);
    }

    private static String getCardsDescription(int[] someCards) {
        String cardNames = "[ ";
        int i = 0;
        for (int card: someCards) {
            cardNames += Deck.getCardName(card) + " ";
        }
        cardNames += " ]";
        return cardNames;
    }

    public String getUpcardDescription() {
        int[] upcards = { getUpcard() } ;
        return getCardsDescription(upcards);
    }

    public String getHandDescription() {
        return getCardsDescription(getCardsArray());
    }

    public int getHandValue() {
        int value = 0;
        for (int card : this.cards) {
            value += Deck.getCardValue(card);
        }
        return value;
    }

    // this main just used to test the hand class
    public static void main(String[] args) {
        Deck deck = new Deck();
        Hand hand =  deck.pullHand();
        hand.addCard(deck.pullNextCard());
        hand.addCard(deck.pullNextCard());
        System.out.println("hand value: " + hand.getHandValue());
        System.out.println("hand description: " + hand.getHandDescription());
        System.out.println("hand upcard: " + hand.getUpcardDescription());
    }
}
