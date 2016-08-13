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
    assertEquals("Spades", card.getSuit());
  }
  @Test
  public void canGetValue(){
    assertEquals("A", card.getValue());
  }
  @Test
  public void canSetSuit(){
    card.setSuit("Diamonds");
    assertEquals("Diamonds", card.getSuit());
  }@Test
  public void canSetValue(){
    card.setValue("7");
    assertEquals("7", card.getValue());
  }
}