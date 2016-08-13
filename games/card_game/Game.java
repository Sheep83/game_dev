import game.*;
import java.util.*;

public class Game {
  public static Deck deck;
  public static ArrayList<Player> players = new ArrayList<Player>();
  public static Viewer viewer;
  public static ArrayList<String> initNames = new ArrayList<String>();
  public Player player, player1, player2;
  public Random random;
  public int activePlayer;
  public Card currentCard;
  public static boolean gameActive = true;
  public int outCount = 0;
  public Hand hand;

  //GAME CONSTRUCTOR
  public Game(){
  }

  public Player createPlayer(String name, Hand hand){
    Player player = new Player (name, hand);
    players.add(player);
    System.out.println("Player " + player.getName() + " created with a hand of " + player.getHandSize());
    return player;
  }
  public void createPlayers(ArrayList<String> initNames, Deck deck){
    for (String name : initNames){
      Hand hand = new Hand(deck, 0);
      this.createPlayer(name, hand);
    }
  }
  public ArrayList<Player> getPlayers(){
    System.out.println("There are " + players.size() + " players");
    System.out.println(players);
    return players;
  }
  public Player getPlayerByIndex(Integer index){
    return players.get(index);
  }
  public void allTakeCards(ArrayList<Player> players){
    if (players.size() > 1){
      for (int i=0;i<players.size()-1;i++) {
        Player takingPlayer = this.players.get(i);
        Player givingPlayer = this.players.get(i+1);
        Random random = new Random();
        int  n = random.nextInt(givingPlayer.getHandSize() + 0);
        System.out.println(n);
        takingPlayer.takeFromPlayer(givingPlayer, n);
        System.out.println(takingPlayer.getName() + " takes a card from " + givingPlayer.getName());
        if(n == 0 && givingPlayer.checkIfOut() == true){
          players.remove(givingPlayer);
          this.outCount +=1;
        }
        takingPlayer.dropPairs();
        if(takingPlayer.checkIfOut() == true){
          players.remove(takingPlayer);
          this.outCount +=1;
        }  
      }
      Player lastPlayer = this.players.get(this.players.size()-1);
      Player firstPlayer = this.players.get(0);
      Random random = new Random();
      int  x = random.nextInt(firstPlayer.getHandSize() + 0);
      System.out.println(x);
      if(x == 0 && firstPlayer.checkIfOut() == true){
        players.remove(firstPlayer);
        this.outCount +=1;
      }
      lastPlayer.takeFromPlayer(firstPlayer, x);
      System.out.println(lastPlayer.getName() + " takes a card from " + firstPlayer.getName());
      lastPlayer.dropPairs();
      if(lastPlayer.checkIfOut() == true){
        this.outCount +=1;
        players.remove(lastPlayer);
      }
    }else
    {
      this.gameOver(players);
    }
  }
  public void getAllHandSizes(){
    for (Player player : players){
      System.out.println(player.getHandSize());
    }
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
  public void dropAllPairs(ArrayList<Player> players){
    for (Player player : players){
      player.dropPairs();
      System.out.println(player.getHandSize());
    }
  }
  public void gameOver(ArrayList<Player> players){
    System.out.println(" ");
    System.out.println("Game Over! " + players.get(0).getName() + " wins/loses :)");
  }


//MAIN ENTRY POINT
  public static void main(String[] args){
    while (gameActive == true){
    // deck = new Deck();
      Game game = new Game();
      deck = game.createDeck();
      viewer = new Viewer();

    //Interface goes here
      initNames = viewer.gameStart();
      System.out.println(initNames);
      game.createPlayers(initNames, deck);
      deck.dropQueens();
    // Hand p1Hand = new Hand(deck, 0);
    // Hand p2Hand = new Hand(deck, 0);
    // Hand p3Hand = new Hand(deck, 0);
    // Hand p4Hand = new Hand(deck, 0);
    // Player player1 = new Player("Brian", p1Hand);
    // Player player2 = new Player("Josh", p2Hand);
    // game.createPlayer("Brian", p1Hand);
    // game.createPlayer("Josh", p2Hand);
    // game.createPlayer("Theo", p3Hand);
    // game.createPlayer("Yoda", p4Hand);
    // game.getPlayers();
      deck.dealToAll(players);
    //print deck
    // System.out.println(deck.getDeck());
      System.out.println(deck.getCardsLeft());
      game.dropAllPairs(players);
      game.dropAllPairs(players);
      while(game.outCount < initNames.size()-1){
        game.showAllHands(players);
        game.allTakeCards(players);
        game.showAllHands(players);
      }
      game.gameOver(players);
      
      if (viewer.playAgain() == true){
          gameActive = true;
      }else
      {
        gameActive = false;
      }
    

    }

  }

}




