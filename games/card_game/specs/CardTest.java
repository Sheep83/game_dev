import static org.junit.Assert.*;
import org.junit.*;
import game.*;

public class CardTest {

  Card card;
  
  @Before 
  public void before(){
    card = new Card("Spades", "A");
  }
  @Test
  public void canGetSuit(){
    assertEquals(card.getSuit(),"Spades");
  }
  @Test
  public void canGetValue(){
    assertEquals(card.getValue(), "A");
  }
}