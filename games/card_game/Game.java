import game.*;
import java.util.*;

public class Game {
  public static Deck deck;
  public static ArrayList<Player> players = new ArrayList<Player>();
  public Player player, player1, player2;
  public Random random;
  public int activePlayer;
  public Card currentCard;

  //GAME CONSTRUCTOR
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

//MAIN ENTRY POINT
  public static void main(String[] args){
    // deck = new Deck();
    Game game = new Game();
    deck = game.createDeck();
    deck.dropQueens();
    Hand p1Hand = new Hand(deck, 0);
    Hand p2Hand = new Hand(deck, 0);
    Player player1 = new Player("Brian", p1Hand);
    Player player2 = new Player("Josh", p2Hand);
    // game.createPlayer("Brian", p1Hand);
    // game.createPlayer("Josh", p2Hand);
    // game.getPlayers();
    deck.dealCardToPlayer(player1);
    //print deck
    System.out.println(deck.getDeck());
    System.out.println(deck.getCardsLeft()); 
    System.out.println(player1.getHandSize());

  }

}


