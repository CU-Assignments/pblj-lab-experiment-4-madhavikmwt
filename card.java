import java.util.*;

class Card {
    String suit;
    String rank;

    Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}

public class CardCollectionSystem {
    private static HashMap<String, HashSet<String>> cards = new HashMap<>();

    public static void addCard(String rank, String suit) {
        cards.putIfAbsent(suit, new HashSet<>());
        if (!cards.get(suit).add(rank)) {
            System.out.println("Error: Card \"" + rank + " of " + suit + "\" already exists.");
            return;
        }
        System.out.println("Card added: " + rank + " of " + suit);
    }

    public static void findCardsBySuit(String suit) {
        if (cards.containsKey(suit) && !cards.get(suit).isEmpty()) {
            for (String rank : cards.get(suit)) {
                System.out.println(rank + " of " + suit);
            }
        } else {
            System.out.println("No cards found for " + suit + ".");
        }
    }

    public static void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards found.");
            return;
        }
        for (Map.Entry<String, HashSet<String>> entry : cards.entrySet()) {
            for (String rank : entry.getValue()) {
                System.out.println(rank + " of " + entry.getKey());
            }
        }
    }

    public static void removeCard(String rank, String suit) {
        if (cards.containsKey(suit) && cards.get(suit).remove(rank)) {
            System.out.println("Card removed: " + rank + " of " + suit);
            if (cards.get(suit).isEmpty()) {
                cards.remove(suit);
            }
        } else {
            System.out.println("Error: Card \"" + rank + " of " + suit + "\" not found.");
        }
    }

    public static void main(String[] args) {
        // Test Case 1
        displayAllCards();
        
        // Test Case 2
        addCard("Ace", "Spades");
        addCard("King", "Hearts");
        addCard("10", "Diamonds");
        addCard("5", "Clubs");
        
        // Test Case 3
        findCardsBySuit("Hearts");
        
        // Test Case 4
        findCardsBySuit("Diamonds");
        
        // Test Case 5
        displayAllCards();
        
        // Test Case 6
        addCard("King", "Hearts");
        
        // Test Case 7
        removeCard("10", "Diamonds");
    }
}
//Madhavi kumawat_22BCS12660
