package blackjacktest;

import java.util.ArrayList;
import java.util.Random;

public class BlackjackGame {

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

    private static String getCardName(int cardNumber) {
        final int suitIndex = cardNumber/CARDS_IN_SUIT; // note integer division
        final int cardIndex = cardNumber - (suitIndex * CARDS_IN_SUIT);
        return CARD_NAMES[cardIndex] + " of " + SUIT_NAMES[suitIndex];
    }

    private static int getCardValue(int cardNumber) {
        final int suitIndex = cardNumber/CARDS_IN_SUIT; // note integer division
        final int cardIndex = cardNumber - (suitIndex * CARDS_IN_SUIT);
        return CARD_VALUES[cardIndex];
    }

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

    public static String getUpcareDescription(ArrayList<Integer> hand) {
        String upcardDescription = "[ " + getCardName(hand.get(0)) + "]";
        return upcardDescription;
    }


    public static String getHandDescription(ArrayList<Integer> hand) {
        String cardNames = "[ ";
        int i = 0;
        for (int card: hand) {
            cardNames += Deck.getCardName(card) + " ";
        }
        cardNames += " ]";
        return cardNames;
    }

    public static int getHandValue(ArrayList<Integer> hand) {
        int value = 0;
        for (int card : hand) {
            value += getCardValue(card);
        }
        return value;
    }

    public static int pullNextCard() {
        // reshuffle if not enough cards
        final int reshuffleMinimum = 10;
        if (deck.length <= reshuffleMinimum) {
            deck = initializeDeck();
        }

        // pop the first card off the top of the deck
        int card = deck[0];
        int[] remainingDeck = new int[deck.length-1];
        System.arraycopy(deck, 1, remainingDeck, 0, deck.length-1);
        deck = remainingDeck;
        return card;
    }

    public static ArrayList<Integer> pullHand() {
        ArrayList<Integer> hand = new  ArrayList<>();
        hand.add(pullNextCard());
        hand.add(pullNextCard());
        return hand;
    }

    public static int[] deck;

    public BlackjackGame() {
        deck = initializeDeck();
    }


    public static void main(String[] args) {

        BlackjackGame blackjackGame = new BlackjackGame();
        float playerScore = 0;
        float dealerScore = 0;
        String dealerName;
        String playerName;
        ArrayList<Integer> dealerHand;
        ArrayList<Integer> playerHand;


        // get dealer and player names



        // deal dealer and player hands
        dealerHand = blackjackGame.pullHand();
        playerHand = blackjackGame.pullHand();
        System.out.println("hand value: " + getHandValue(playerHand));
        System.out.println("hand description: " + getHandDescription(playerHand));
        System.out.println("hand upcard: " + getUpcareDescription(playerHand));

        // player plays hand



        // dealer plays hand



        // determine winner



    }



}
