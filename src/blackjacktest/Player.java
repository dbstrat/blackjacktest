package blackjacktest;

public class Player {

    private String name;
    private boolean isDealer;
    private Hand hand;

    Player(String name, boolean isDealer, Hand startingHand) {
        this.name = name;
        this.isDealer = isDealer;
        this.hand = startingHand;
    }

    public Hand getHand() {
        return hand;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public String getName() {
        return name;
    }

    public void addCardToHand(int card) {
        hand.addCard(card);
    }

}
