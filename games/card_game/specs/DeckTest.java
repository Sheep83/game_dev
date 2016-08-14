import static org.junit.Assert.*;
import org.junit.*;
import game.*;

public class DeckTest {
  Deck deck;
  Player player;
  Hand hand;
  Card card;
  
  @Before 
  public void before(){
    deck = new Deck();
    hand = new Hand(deck, 0);
    player = new Player("test", hand);
  }
  @Test
  public void deckIsFull(){
    assertEquals(deck.getCardsLeft(), 52);
  }
  @Test
  public void canRemoveCard(){
    deck.drawCard();
    deck.drawCard();
    assertEquals(deck.getCardsLeft(), 50);
  }
  @Test
  public void canDropQueens(){
    deck.dropQueens();
    assertEquals(deck.getCardsLeft(), 49);
  }
  @Test
  public void canDealToPlayer(){
    deck.dropQueens();
    deck.dealCardToPlayer(player);
    assertEquals(deck.getCardsLeft(), 48);
    assertEquals(1, player.getHandSize());
  }
  @Test
  public void canAddToDeck(){
    deck.dropQueens();
    card = new Card("A", "Spades");
    deck.addToDeck(card);
    assertEquals(50, deck.getCardsLeft());
  }


}