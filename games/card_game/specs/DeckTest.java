import static org.junit.Assert.*;
import org.junit.*;
import game.*;

public class DeckTest {
  Deck deck;
  
  @Before 
  public void before(){
    deck = new Deck();
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


}