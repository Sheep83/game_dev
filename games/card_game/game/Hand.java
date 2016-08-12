package game;
import java.util.*;

public class Hand {
    public ArrayList<Card> cards = new ArrayList<Card>();

    public Hand(Deck deck, int size){
        for (int i = 0; i<size; i++) {
            cards.add(deck.drawCard());
            }
        }
        public int getLength(){
            return cards.size();
        }


    }