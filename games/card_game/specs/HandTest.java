import static org.junit.Assert.*;
import org.junit.*;
import game.*;

public class HandTest {
  Deck deck;
  Hand hand, hand1, hand2;
  Player player;
  
  @Before 
  public void before(){
    deck = new Deck();
  }
  @Test
  public void allCardsInHand(){
    hand = new Hand(deck, 52);
    assertEquals(52, hand.getLength());
  }
  @Test
  public void isFive(){
    hand = new Hand(deck, 5);
    assertEquals(5, hand.getLength());
  }
  

}