package blackjacktest;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJackGame {

    private static final int NUM_PLAYERS = 2;
    private static final Scanner scanner = new Scanner(System.in);

    private static final int HIT = 0;
    private static final int STAY = 1;

    public static int getDealerAction(Player player) {
        Hand hand = player.getHand();
        int handValue = hand.getHandValue();
        return HIT;  /// placeholder -- needs to be coded
    }

    public static int getPlayerAction(Player player) {
        Hand hand = player.getHand();
        int handValue = hand.getHandValue();
        return HIT;  /// placeholder -- needs to be coded
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        ArrayList<Player> players = new ArrayList<Player>(NUM_PLAYERS);

        System.out.println("Welcome to Blackjack!");

        // collect player names
        for (int i = 0; i< NUM_PLAYERS; i++) {
            boolean isDealer = (i == 0); // zeroth player is dealer
            if (isDealer) {
                System.out.println("Please enter the name of player " + i + " (dealer)");
            } else {
                System.out.println("Please enter the name of player " + i + " (player)");
            }
            String name = scanner.next();

            Hand startingHand = Hand.pullHand(deck);
            players.add(new Player(name,isDealer,startingHand));
        }


        // play each player's hand
        for (int i=1; i< NUM_PLAYERS; i++) {
            Player player = players.get(i);
            String name = player.getName();
            System.out.println(name + " it is your turn.");
            System.out.println(name + " you have " + player.getHand().getHandDescription());
            int action = getPlayerAction(player);
            if (action == HIT) {
                // do something
            } else {
                // do something else
            }
        }

        // play dealer's hand
        Player dealer = players.get(0);
        String name = dealer.getName();
        System.out.println(name + " it is your turn.");
        int action = getDealerAction(dealer);
        if (action == HIT) {
            // do something
        } else {
            // do something else
        }

        // determine winner

        // update statistics
    }
}
