package game;
import java.util.*;

public class Player {

  private String name;
  private int score;
  public Hand hand;
  public int index;

  public Player(String name, Hand hand){
    this.name = name;
    this.score = 0;
    this.hand = hand;
  }

  public String getName(){
    return name;
  }
  public Card addCard(Card card){
    this.hand.cards.add(card);
    return card;
  }
  public void takeFromPlayer(Player player, int randCard){
    this.hand.cards.add(player.hand.cards.remove(randCard));
    // this.dropPairs();
  }
  public Card removeByIndex(int index){
    Card card = this.hand.cards.remove(index);
    return card;
  }
  public Hand getHand(){
    return hand;
  }
  public int getHandSize(){
    return hand.cards.size();
  }
  public boolean checkIfOut(){
    if(this.getHandSize() == 0){
      System.out.println(this.getName() + " is out!");
      return true;
    }else
    {
      return false;
    }
  }
  public void dropPairs(){
    for(int i=0; i<hand.cards.size(); i++)
    {
      for(int j=i+1; j<hand.cards.size(); j++)
      {
        Card card = hand.cards.get(i);
        Card card2 = hand.cards.get(j);
        if(card.value == card2.value)
          try{
            System.out.println("");
            System.out.println("Pair Found!");
            // System.out.println(hand.cards.get(i).toString());
            // System.out.println(hand.cards.get(j).toString());
            hand.cards.remove(card);
            System.out.println(card.toString() + " removed.");
            hand.cards.remove(card2); 
            System.out.println(card2.toString() + " removed.");
          }
          catch (IndexOutOfBoundsException ex){
            System.out.println("Pairs Removed!");
          } 

        }
      }
      this.checkIfOut();
    }
  }
