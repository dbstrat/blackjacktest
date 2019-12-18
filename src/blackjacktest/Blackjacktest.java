package blackjacktest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Blackjacktest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to blackjack");
        System.out.println("what is your name?");
        String nameOne = scanner.next();
        String nameTwo = "joe";
        System.out.println("hello " + nameOne + " I am joe, joe mamma");
        //intitalizes the deck full of the card names 
        String cardNames[] = {
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "J",
            "Q",
            "K",
            "A",};

        //initializes the suits 
        String suitNames[] = {
            "spades",
            "hearts",
            "diamonds",
            "clubs",};

        //this gives a total amount of cards that are in the deck 
        int cardsInSuit = cardNames.length;
        int suitsInDeck = suitNames.length;
        int cardsInDeck = cardsInSuit * suitsInDeck;
        int[] playerValue = new int[2];
        //these are all the calls to my methods 
        int orderedDeck[] = makeDeck(cardsInDeck);
        int shuffledDeck[] = shuffleDeck(orderedDeck, cardsInDeck);
        for (int cardNumber : shuffledDeck) {
            printCard(cardNumber, cardsInSuit, cardNames, suitNames);

        }
        double money = 100;
        int cardsLeft = 0;
        int distribute = 0;
        int times = 0;
        int tempVar = 0;

        int bet = 0;
        System.out.println("how much do you want to bet?");
        boolean loop = true;
        while (loop == true) {
            if (scanner.hasNextInt()) {
                bet = scanner.nextInt();
                loop = false;
            } else {
                System.out.println("no you have to put a number try again");
                scanner.next();
            }

        }
        System.out.println(bet);
        System.out.println();
        pullCard(cardsLeft, shuffledDeck, suitNames, nameOne, 0, 3, playerValue);
        cardsLeft += 3;
        System.out.println(Arrays.toString(playerValue));
        System.out.println(cardsLeft);
        String answer = "h";
        String active = "y";

        while (money > 0 && active.equals("y")) {
            int playing = 0;
            while (playing == 0) {

                if (playerValue[0] < 21) {
                    System.out.println("would you like to [h]it or [s]tand? hand value: " + playerValue[0]);
                    answer = scanner.next();
                    if (answer.equals("h")) {
                        pullCard(cardsLeft, shuffledDeck, suitNames, nameOne, 0, 1, playerValue);
                        System.out.println(Arrays.toString(playerValue));
                        System.out.println(cardsLeft);
                        cardsLeft++;

                    } else if (answer.equals("s")) {
                        if (playerValue[1] < 17) {
                            pullCard(cardsLeft, shuffledDeck, suitNames, nameOne, 1, 2, playerValue);
                            System.out.println(Arrays.toString(playerValue));
                            System.out.println(cardsLeft);
                            cardsLeft++;
                        }
                        if (playerValue[1] > 17 && playerValue[1] < 22) {
                            if (playerValue[0] > playerValue[1]) {
                                System.out.println("you win");
                                money = money + bet;
                                playing = 1;
                            } else if (playerValue[0] < playerValue[1]) {
                                System.out.println("you lose");
                                money = money - bet;
                                playing = 1;
                            } else if (playerValue[0] == playerValue[1]) {
                                System.out.println("push");
                                playing = 1;
                            }
                        }
                        if (playerValue[1] > 22) {
                            System.out.println("you win");
                            money = money + bet;
                            playing = 1;
                        }
                    }

                } else if (playerValue[0] > 21) {
                    System.out.println("you lose");
                    money = money - bet;
                    playing = 1;
                } else if (playerValue[0] == 21) {
                    if (playerValue[1] != 21) {
                        System.out.println("Blackjack, you win");
                        money = money + (1.5 * bet);
                        playing = 1;
                    } else if (playerValue[1] == 21) {
                        System.out.println("Push");
                        playing = 1;
                    }
                }

            }
            if (cardsLeft > 42) {
                for (int cardNumber : shuffledDeck) {
                    printCard(cardNumber, cardsInSuit, cardNames, suitNames);

                }
            }
            System.out.println(money);
            System.out.println("would you like to continue? [y]es or [n]o");
            active = scanner.next();
        }
    }

    public static int pullCard(int cardsLeft, int shuffledDeck[], String suitNames[], String nameOne, int times, int tempVar, int playerValue[]) {

        while (times < tempVar) {

            double suitTracker = (double) shuffledDeck[cardsLeft] / 13;
            if (suitTracker <= 1.0) {

                if (shuffledDeck[cardsLeft] == 1) {
                    if (times % 2 == 0) {
                        if (playerValue[0] + 11 <= 21) {
                            playerValue[0] += 11;
                            System.out.println(nameOne + " shows Ace of " + suitNames[0]);
                        } else {
                            playerValue[0] += 1;
                            System.out.println(nameOne + " shows Ace of " + suitNames[0]);
                        }
                    } else if (times % 2 == 1) {
                        if (playerValue[1] + 11 <= 21) {
                            playerValue[1] += 11;
                            System.out.println("Dealer shows Ace of " + suitNames[0]);
                        } else {
                            playerValue[1] += 1;
                            System.out.println("Dealer shows Ace of " + suitNames[0]);
                        }
                    }
                } else if (shuffledDeck[cardsLeft] == 11) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows Jack" + " of " + suitNames[0]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows Jack" + " of " + suitNames[0]);
                    }

                } else if (shuffledDeck[cardsLeft] == 12) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows Queen" + " of " + suitNames[0]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows Queen" + " of " + suitNames[0]);
                    }
                } else if (shuffledDeck[cardsLeft] == 13) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows King" + " of " + suitNames[0]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows King" + " of " + suitNames[0]);
                    }
                } else {
                    if (times % 2 == 0) {
                        playerValue[0] += shuffledDeck[cardsLeft];
                        System.out.println(nameOne + " shows " + (shuffledDeck[cardsLeft]) + " of " + suitNames[0]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += shuffledDeck[cardsLeft];
                        System.out.println("Dealer shows " + (shuffledDeck[cardsLeft]) + " of " + suitNames[0]);
                    }
                }

            } else if (suitTracker >= 1.0 && suitTracker < 2.0) {
                if (shuffledDeck[cardsLeft] - 13 == 1) {
                    if (times % 2 == 0) {
                        if (playerValue[0] + 11 <= 21) {
                            playerValue[0] += 11;
                            System.out.println(nameOne + " shows Ace" + " of " + suitNames[1]);
                        } else {
                            playerValue[0] += 1;
                            System.out.println(nameOne + " shows Ace" + " of " + suitNames[1]);
                        }
                    } else if (times % 2 == 1) {
                        if (playerValue[1] + 11 <= 21) {
                            playerValue[1] += 11;
                            System.out.println("Dealer shows Ace" + " of " + suitNames[1]);
                        } else {
                            playerValue[1] += 1;
                            System.out.println("Dealer shows Ace" + " of " + suitNames[1]);
                        }
                    }
                } else if (shuffledDeck[cardsLeft] - 13 == 11) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows Jack" + " of " + suitNames[1]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows Jack" + " of " + suitNames[1]);
                    }

                } else if (shuffledDeck[cardsLeft] - 13 == 12) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows Queen" + " of " + suitNames[1]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows Queen" + " of " + suitNames[1]);
                    }
                } else if (shuffledDeck[cardsLeft] - 13 == 13) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows King" + " of " + suitNames[1]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows King" + " of " + suitNames[1]);
                    }
                } else {
                    if (times % 2 == 0) {
                        playerValue[0] += shuffledDeck[cardsLeft] - 13;
                        System.out.println(nameOne + " shows " + (shuffledDeck[cardsLeft] - 13) + " of " + suitNames[1]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += shuffledDeck[cardsLeft] - 13;
                        System.out.println("Dealer shows " + (shuffledDeck[cardsLeft] - 13) + " of " + suitNames[1]);
                    }
                }

            } else if (suitTracker >= 2.0 && suitTracker < 3.0) {
                if (shuffledDeck[cardsLeft] - 26 == 1) {
                    if (times % 2 == 0) {
                        if (playerValue[0] + 11 <= 21) {
                            playerValue[0] += 11;
                            System.out.println(nameOne + " shows Ace" + " of " + suitNames[2]);
                        } else {
                            playerValue[0] += 1;
                            System.out.println(nameOne + " shows Ace" + " of " + suitNames[2]);
                        }
                    } else if (times % 2 == 1) {
                        if (playerValue[1] + 11 <= 21) {
                            playerValue[1] += 11;
                            System.out.println("Dealer shows Ace" + " of " + suitNames[2]);
                        } else {
                            playerValue[1] += 1;
                            System.out.println("Dealer shows Ace" + " of " + suitNames[2]);
                        }
                    }
                } else if (shuffledDeck[cardsLeft] - 26 == 11) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows Jack" + " of " + suitNames[2]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows Jack" + " of " + suitNames[2]);
                    }

                } else if (shuffledDeck[cardsLeft] - 26 == 12) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows Queen" + " of " + suitNames[2]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows Queen" + " of " + suitNames[2]);
                    }
                } else if (shuffledDeck[cardsLeft] - 26 == 13) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows King" + " of " + suitNames[2]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows King" + " of " + suitNames[2]);
                    }
                } else {
                    if (times % 2 == 0) {
                        playerValue[0] += shuffledDeck[cardsLeft] - 26;
                        System.out.println(nameOne + " shows " + (shuffledDeck[cardsLeft] - 26) + " of " + suitNames[2]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += shuffledDeck[cardsLeft] - 26;
                        System.out.println("Dealer shows " + (shuffledDeck[cardsLeft] - 26) + " of " + suitNames[2]);
                    }

                }

            } else if (suitTracker >= 3.0 && suitTracker <= 4.0) {
                if (shuffledDeck[cardsLeft] - 39 == 1) {
                    if (times % 2 == 0) {
                        if (playerValue[0] + 11 <= 21) {
                            playerValue[0] += 11;
                            System.out.println(nameOne + " shows Ace" + " of " + suitNames[3]);
                        } else {
                            playerValue[0] += 1;
                            System.out.println(nameOne + " shows Ace" + " of " + suitNames[3]);
                        }
                    } else if (times % 2 == 1) {
                        if (playerValue[1] + 11 <= 21) {
                            playerValue[1] += 11;
                            System.out.println("Dealer shows Ace" + " of " + suitNames[3]);
                        } else {
                            playerValue[1] += 1;
                            System.out.println("Dealer shows Ace" + " of " + suitNames[3]);
                        }
                    }
                } else if (shuffledDeck[cardsLeft] - 39 == 11) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows Jack" + " of " + suitNames[3]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows Jack" + " of " + suitNames[3]);
                    }

                } else if (shuffledDeck[cardsLeft] - 39 == 12) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows Queen" + " of " + suitNames[3]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows Queen" + " of " + suitNames[3]);
                    }
                } else if (shuffledDeck[cardsLeft] - 39 == 13) {
                    if (times % 2 == 0) {
                        playerValue[0] += 10;
                        System.out.println(nameOne + " shows King" + " of " + suitNames[3]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += 10;
                        System.out.println("Dealer shows King" + " of " + suitNames[3]);
                    }
                } else {
                    if (times % 2 == 0) {
                        playerValue[0] += shuffledDeck[cardsLeft] - 39;
                        System.out.println(nameOne + " shows " + (shuffledDeck[cardsLeft] - 39) + " of " + suitNames[3]);
                    } else if (times % 2 == 1) {
                        playerValue[1] += shuffledDeck[cardsLeft] - 39;
                        System.out.println("Dealer shows " + (shuffledDeck[cardsLeft] - 39) + " of " + suitNames[3]);
                    }
                }

            }
            times++;
            cardsLeft++;
            System.out.println();

        }
        return cardsLeft;
    }

    //this makes the actual deck based on how many cards there are 
    public static int[] makeDeck(int cardsInDeck) {

        int deck[] = new int[cardsInDeck];
        for (int i = 0; i < cardsInDeck; i++) {
            deck[i] = i;
        }
        return deck;
    }
    //this method prints the  
    //shuffled deck 

    public static void printCard(int cardNumber, int cardsInSuit, String cardNames[], String suitNames[]) {
        int suitIndex = cardNumber / cardsInSuit;
        int cardIndex = cardNumber - (suitIndex * cardsInSuit);
        System.out.println(cardNames[cardIndex] + " of " + suitNames[suitIndex]);
    }

    //this shuffles the deck by going through each index in the  
    //deck length and swaps them with different index's 
    public static int[] shuffleDeck(int inputDeck[], int cardsInDeck) {

        Random rand = new Random();
        int shuffledDeck[] = new int[cardsInDeck];
        for (int i = 0; i < shuffledDeck.length; i++) {
            shuffledDeck[i] = inputDeck[i];
        }

        for (int currentIndex = 0; currentIndex < cardsInDeck; currentIndex++) {
            int currentCard = shuffledDeck[currentIndex];
            int swapIndex = rand.nextInt(cardsInDeck);
            shuffledDeck[currentIndex] = shuffledDeck[swapIndex];
            shuffledDeck[swapIndex] = currentCard;
        }
        return shuffledDeck;
    }
}
