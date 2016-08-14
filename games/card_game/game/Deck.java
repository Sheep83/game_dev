package game;
import java.util.*;

public class Deck {
    public ArrayList<Card> cards = new ArrayList<Card>();
    String[] values = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
    String[] suit = {"Clubs", "Spades", "Diamonds", "Hearts"};

    public Deck(){
        for (int i = 0; i<suit.length; i++) {
            for(int j=0; j<values.length; j++){
                this.cards.add(new Card(suit[i],values[j]));
            }
        }
        //shuffle 
        Collections.shuffle(this.cards);
    }
    public ArrayList<Card> getDeck(){
        return cards;
    }
    public Card addToDeck(Card card){
        cards.add(card);
        return card;
    } 
    public Card drawCard(){
        return cards.remove(0);
    }
    public void dealCardToPlayer(Player player){
        player.addCard(this.drawCard());
    }
    public int getCardsLeft(){
        return cards.size();
    }
    public void dealToAll (ArrayList<Player> players){
        do {
         for(Player player : players)try {
            this.dealCardToPlayer(player);
            System.out.println("Cards left in deck: " + cards.size());
            System.out.println(player.getName());
            System.out.println("Cards in " + player.getName() + "'s hand: " + player.getHandSize());
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println("No Cards Left!");
        }
    }
    while (cards.size() > 0);
}
public void dropQueens(){
    Iterator<Card> card = cards.iterator();
    while (card.hasNext()) {
        Card newCard = card.next();
        if (newCard.value == "Queen" && newCard.suit != "Spades")
            card.remove();
    }
}

}


