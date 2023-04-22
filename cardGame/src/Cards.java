package CardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Cards {

    private Random rand = new Random();
    private int[] cards = {11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 36, 37, 38, 39, 41, 42, 43, 44, 45, 46, 47, 48, 49, 110, 111, 112, 113, 210, 211, 212, 213, 310, 311, 312, 313, 410, 411, 412, 413};
    private List<Integer> deck = new ArrayList<Integer>();
    
    /*shuffle the deck every round 
     * @param deck is a hand with deck of cards
     * @param card refers to every card in the list card
     */
    public void resetDeck() {
        deck.clear();
        for (int card : cards) {
            deck.add(card);
        }
        Collections.shuffle(deck);
    }
    
    /*draw cards from the top of the deck
     * @param deck is a hand with shuffled deck
     * @cardValue refers to the value of the card (its unique id)
     * @return the value of the card from the top of the deck
     */
    public int getCard() {
        if (deck.size() == 0) {
            resetDeck();
        }
        int cardValue = deck.get(0);
        deck.remove(0);
        return cardValue;
    }
    
    /*check whether the card drawn is a special card
     * @param cardValue isthe value of the card
     * @return 1 if the card is a special card
     * @return 0 otherwise
     */
    public int isSpecialCard(int cardValue) {
        if (cardValue == 111 || cardValue == 112 || cardValue == 113 || cardValue == 211 || cardValue == 212 || cardValue == 213 || cardValue == 311 || cardValue == 312 || cardValue == 131 || cardValue == 411 || cardValue == 412 || cardValue == 413) {
            return 1;
        }
        return 0;
    }
}
