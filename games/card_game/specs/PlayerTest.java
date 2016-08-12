import static org.junit.Assert.*;
import org.junit.*;
import game.*;

public class PlayerTest {
    Player player;
    Card card1;
    Card card2;
    Hand hand, hand1;
    Deck deck;
  @Before 
  public void before(){
    // player = new Player("Brian");
    card1 = new Card("Spades", "A");
    card2 = new Card("Hearts", "Q");
    deck = new Deck();

  }
  @Test
  public void canGetName(){
    hand = new Hand(deck, 0);
    player = new Player("Brian", hand);
    assertEquals(player.getName(), "Brian");
  }
  @Test
  public void hasEmptyHand(){
    hand = new Hand(deck, 0);
    player = new Player("Brian", hand);
    assertEquals(player.hand.getLength(), 0);
  }
  @Test 
  public void canGetCard(){
    hand = new Hand(deck, 0);
    player = new Player("Brian", hand);
    Card card = player.getCard(card1);
    assertEquals(player.hand.getLength(), 1);
    assertEquals(card.getValue(), "A");
  }
  @Test
  public void canRemoveByIndex(){
    hand = new Hand(deck, 0);
    player = new Player("Brian", hand);
    player.getCard(card1);
    player.getCard(card2);
    Card card = player.removeByIndex(1);
    assertEquals(card.getSuit(), "Hearts");

  }
  @Test 
  public void canGetHandFromPlayer(){
    hand = new Hand(deck, 5);
    player = new Player("Brian", hand);
    assertEquals(player.getHand().cards.size(), 5);
  }
  @Test 
  public void canGetHandSize(){
    hand = new Hand(deck, 5);
    player = new Player("Brian", hand);
    assertEquals(player.getHandSize(), 5);
  }
  
}