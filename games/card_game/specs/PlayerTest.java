import static org.junit.Assert.*;
import org.junit.*;
import game.*;

public class PlayerTest {
  Player player, player1, player2;
  Card card1, card2, card3, card4, card5;
  Hand hand, hand1, hand2;
  Deck deck;
  @Before 
  public void before(){
    card1 = new Card("Spades", "A");
    card2 = new Card("Hearts", "7");
    card3 = new Card("Diamonds", "K");
    card4 = new Card("Spades", "3");
    card5 = new Card("Clubs", "7");
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
    Card card = player.addCard(card1);
    assertEquals(player.hand.getLength(), 1);
    assertEquals(card.getValue(), "A");
  }
  @Test
  public void canRemoveByIndex(){
    hand = new Hand(deck, 0);
    player = new Player("Brian", hand);
    player.addCard(card1);
    player.addCard(card2);
    Card card = player.removeByIndex(1);
    assertEquals(card.getSuit(), "Hearts");
  }
  @Test 
  public void canGetHandFromPlayer(){
    hand = new Hand(deck, 5);
    player = new Player("Brian", hand);
    assertEquals(5, player.getHand().cards.size());
  }
  @Test 
  public void canGetHandSize(){
    hand = new Hand(deck, 5);
    player = new Player("Brian", hand);
    assertEquals(5, player.getHandSize());
  }
  @Test 
  public void canDropPairs(){
    hand = new Hand(deck, 0);
    player = new Player("Brian", hand);
    player.addCard(card1);
    player.addCard(card2);
    player.addCard(card3);
    player.addCard(card4);
    player.addCard(card5);
    player.dropPairs();
    assertEquals(3, player.getHandSize());
  }
  @Test 
  public void canTakeFromPlayer(){
    hand1 = new Hand(deck, 5);
    hand2 = new Hand(deck, 5);
    player1 = new Player("Brian", hand1);
    player2 = new Player("Theo", hand2);
    player1.takeFromPlayer(player2, 1);
    assertEquals(6, player1.getHandSize());
    assertEquals(4, player2.getHandSize());
  }
  
}