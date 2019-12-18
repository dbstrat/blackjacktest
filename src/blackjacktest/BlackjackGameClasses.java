package blackjacktest;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGameClasses {

    private static final int NUM_PLAYERS = 2;
    private static final Scanner scanner = new Scanner(System.in);

    private static final int ACTION_HIT = 0;
    private static final int ACTION_STAY = 1;

    public static int getDealerAction(Player player) {
        Hand hand = player.getHand();
        int handValue = hand.getHandValue();
        return ACTION_HIT;  /// placeholder -- needs to be coded
    }

    public static int getPlayerAction(Player player) {
        Hand hand = player.getHand();
        int handValue = hand.getHandValue();
        return ACTION_HIT;  /// placeholder -- needs to be coded
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        ArrayList<Player> players = new ArrayList<Player>(NUM_PLAYERS);

        System.out.println("Welcome to Blackjack!");

        // create players and dealer
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

            while (player.getHand().getHandValue() <= 21) {
                int action = getPlayerAction(player);
                if (action == ACTION_HIT) {
                    // do something
                } else {
                    // do something else
                }
            }

            if (player.getHand().getHandValue() <= 21) {
                // do something
            } else {
                // do something else
            }
        }

        // play dealer's hand
        Player dealer = players.get(0);
        String name = dealer.getName();
        System.out.println(name + " it is your turn.");
        while (dealer.getHand().getHandValue() <= 21) {
            int action = getDealerAction(dealer);
            if (action == ACTION_HIT) {
                // do something
            } else {
                // do something else
            }
        }

        if (dealer.getHand().getHandValue() <= 21) {
            // do something
        } else {
            // do something else
        }

        // determine winner


        // update statistics
    }
}
