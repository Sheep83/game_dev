package game;
import java.util.*;

public class Player {

  // public ArrayList<Card> hand = new ArrayList<Card>();
  private String name;
  private int score;
  public Hand hand;
  public int index;



  public Player(String name, Hand hand){
    this.name = name;
    this.score = 0;
    this.hand = hand;
  }
  // public Hand addHand(Hand hand){
  //   this.hand = hand;
  //   return this.hand;
  // }

  public String getName(){
    return name;
  }
  public Card getCard(Card card){
    this.hand.cards.add(card);
    return card;
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
}