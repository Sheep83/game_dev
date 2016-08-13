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
  public Player getPlayerByIndex(Integer index){
    return players.get(index);
  }
  public Deck createDeck(){
    return new Deck();
  }
  public void showAllHands(ArrayList<Player> players){
    for (Player player : players){
      System.out.println("");
      System.out.println(player.getName() + "'s Hand : ");
      player.hand.printCards();
      // System.out.println("");
    }
  }

//MAIN ENTRY POINT
  public static void main(String[] args){
    // deck = new Deck();
    Game game = new Game();
    deck = game.createDeck();
    deck.dropQueens();
    Hand p1Hand = new Hand(deck, 0);
    Hand p2Hand = new Hand(deck, 0);
    Hand p3Hand = new Hand(deck, 0);
    Hand p4Hand = new Hand(deck, 0);
    // Hand p5Hand = new Hand(deck, 0);

    // Player player1 = new Player("Brian", p1Hand);
    // Player player2 = new Player("Josh", p2Hand);
    game.createPlayer("Brian", p1Hand);
    game.createPlayer("Josh", p2Hand);
    game.createPlayer("Theo", p3Hand);
    game.createPlayer("Yoda", p4Hand);
    // game.createPlayer("Han", p5Hand);

    // game.getPlayers();
    deck.dealToAll(players);
    //print deck
    System.out.println(deck.getDeck());
    System.out.println(deck.getCardsLeft());
    game.showAllHands(players);
    // System.out.println(game.getPlayerByIndex(0)); 
    // System.out.println(player1.getHandSize());

  }

}


