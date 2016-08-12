import game.*;
import java.util.*;

public class Game {
  public static Deck deck;
  public static ArrayList<Player> players = new ArrayList<Player>();
  public Player player, player1, player2;
  public Random random;
  public int activePlayer;
  public Card currentCard;

  public Game(){
  }

  public Player createPlayer(String name, Hand hand){
    Player player = new Player (name, hand);
    players.add(player);
    System.out.println("Player " + player.getName() + " created with a hand of " + player.getHandSize());
    return player;
  }
  public ArrayList<Player> getPlayers(){
    System.out.println("There are " + players.size() + " players");
    System.out.println(players);
    return players;
  }
  public Deck createDeck(){
    return new Deck();
  }

  public static void main(String[] args){
    // deck = new Deck();
    // Player player1 = new Player("Brian", p1Hand);
    // Player player2 = new Player("Josh", p2Hand);
    Game game = new Game();
    deck = game.createDeck();
    deck.dropQueens();
    Hand p1Hand = new Hand(deck, 25);
    Hand p2Hand = new Hand(deck, 24);
    game.createPlayer("Brian", p1Hand);
    game.createPlayer("Josh", p2Hand);
    game.getPlayers();
    //print deck
    System.out.println(deck.getDeck());
    System.out.println(deck.getCardsLeft());
    

  }

  // public void dealCard (){
  //   Card card = deck.getDeck(0);
  //   player1.hand.add(card);
  //   System.out.println(player1.hand.size());
  //   // return dealt;
  // }

}


