package blackjacktest;

public class Hand {

    private static int MAX_CARDS_IN_HAND = 10;  // if you add 10 card values together they must be > 21
    private static int UPCARD = 0;
    private int[] cards = new int[MAX_CARDS_IN_HAND];
    private int numCards = 0;


    public static Hand pullHand(Deck deck) {
        return new Hand(deck.pullNextCard(), deck.pullNextCard());
    }

    public Hand(int upcard, int firstDownCard) {
        this.cards[UPCARD] = upcard;
        this.cards[1] = firstDownCard;
        this.numCards = 2;
    }

    public void addCard(int card) {
        this.cards[numCards++] = card;
    }

    public int getUpcard() {
        return this.cards[UPCARD];
    }

    public int[] getCards() {
        return this.cards;
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

    public String getHandShowing() {
        int[] upcards = new int[1];
        upcards[UPCARD] = this.cards[UPCARD];
        return getCardsDescription(upcards);
    }

    public String getHandDescription() {
        return getCardsDescription(this.cards);
    }

    public int getHandValue() {
        int value = 0;
        for (int card : this.cards) {
            value += Deck.getCardValue(card);
        }
        return value;
    }
}
