package blackjacktest;

import java.util.Random;

public class Deck {

    private static final Random rand = new Random();

    private static final String[] CARD_NAMES = {
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "Ten",
            "Jack",
            "Queen",
            "King",
            "Ace",
    };

    private static final int[] CARD_VALUES = {
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10, // ten
            10, // jack
            10, // queen
            10, // king
            1,  // ace
    };

    private static final String[] SUIT_NAMES = {
            "Spades",
            "Hearts",
            "Diamonds",
            "Clubs"
    };

    private static final int CARDS_IN_SUIT = CARD_NAMES.length;
    private static final int SUITS_IN_DECK = SUIT_NAMES.length;
    private static final int CARDS_IN_DECK = CARDS_IN_SUIT * SUITS_IN_DECK;

    private int[] deck;

    private static int[] initializeDeck() {
        int[] deck = new int[CARDS_IN_DECK];
        for (int i=0; i<CARDS_IN_DECK; i++) {
            deck[i] = i;
        }
        return shuffleDeck(deck);
    }

    private static int[] shuffleDeck(int[] inputDeck) {
        int[] shuffledDeck = inputDeck.clone();
        for (int currentIndex = 0; currentIndex < CARDS_IN_DECK; currentIndex++) {
            int currentCard = shuffledDeck[currentIndex];
            int swapIndex = rand.nextInt(CARDS_IN_DECK);
            shuffledDeck[currentIndex] = shuffledDeck[swapIndex];
            shuffledDeck[swapIndex] = currentCard;
        }
        return shuffledDeck;
    }

    public static String getCardName(int cardNumber) {
        final int suitIndex = cardNumber/CARDS_IN_SUIT; // note integer division
        final int cardIndex = cardNumber - (suitIndex * CARDS_IN_SUIT);
        return CARD_NAMES[cardIndex] + " of " + SUIT_NAMES[suitIndex];
    }

    public static int getCardValue(int cardNumber) {
        final int suitIndex = cardNumber/CARDS_IN_SUIT; // note integer division
        final int cardIndex = cardNumber - (suitIndex * CARDS_IN_SUIT);
        return CARD_VALUES[cardIndex];
    }

    public int pullNextCard() {
        // reshuffle if not enough cards
        final int reshuffleMinimum = 10;
        if (this.deck.length <= reshuffleMinimum) {
            this.deck = initializeDeck();
        }

        // pop the first card off the top of the deck
        int card = this.deck[0];
        int[] remainingDeck = new int[deck.length-1];
        System.arraycopy(this.deck, 1, remainingDeck, 0, deck.length-1);
        this.deck = remainingDeck;
        return card;
    }

    public Hand pullHand() {
        int upcard = pullNextCard();
        int firstDownCard = pullNextCard();
        return new Hand(upcard,firstDownCard);
    }

    public Deck() {
        this.deck = initializeDeck();
    }

    // this main just used to test the deck class
    public static void main(String[] args) {
        Deck deck = new Deck();
        for (int i=0; i < 80; i++) {
            System.out.println(getCardName(deck.pullNextCard()));
        }
    }
}
