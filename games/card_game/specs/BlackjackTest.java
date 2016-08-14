// import static org.junit.Assert.*;
// import org.junit.*;
// import game.*;

// public class BlackjackTest {
//   Player player, player1, player2;
//   Card card, card1, card2;
//   Deck deck;
//   Hand hand;
//   Blackjack blackjack;
//   ArrayList<Player> players;

  
//   @Before 
//   public void before(){
//     deck = new Deck();
//     hand = new Hand(deck, 0);
//     player1 = new Player ("Test1", hand);
//     player2 = new Player ("Test1", hand);
//     players.add(player1);
//     players.add(player2);
//     blackjack = new Blackjack(players, deck);

//   }
//   @Test
//   public void canDealCards(){
//     blackjack.dealToPlayers(players);
//     assertEquals(2, players.get(0).getHandSize());
//     assertEquals(2, players.get(1).getHandSize());
//   }
// }