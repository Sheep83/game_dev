import game.*;
import java.util.*;

public class Game {
  public static Deck deck;
  public static ArrayList<Player> players;
  public static Viewer viewer;
  public static ArrayList<String> initNames = new ArrayList<String>();
  public Player player, player1, player2;
  public Random random;
  public static boolean gameActive = true;
  public int outCount = 0;
  public Hand hand;

  //GAME CONSTRUCTOR
  public Game(){
    this.players = new ArrayList<Player>();
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
        // Random random = new Random();
        // int  n = random.nextInt(givingPlayer.getHandSize() + 0);
        // System.out.println(n);
        int n = viewer.chooseCard(takingPlayer, givingPlayer);
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
      int x = viewer.chooseCard(lastPlayer, firstPlayer);
      // Random random = new Random();
      // int  x = random.nextInt(firstPlayer.getHandSize() + 0);
      // System.out.println(x);
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
      System.out.print(player.getName() + "'s Hand : ");
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
      Game game = new Game();
      deck = game.createDeck();
      deck.dropQueens();
      viewer = new Viewer();
    //Interface goes here
      int gameType = viewer.chooseGame();
      if (gameType == 1){
      initNames = viewer.gameStart();
      game.createPlayers(initNames, deck);
      deck.dealToAll(players);
      System.out.println(deck.getCardsLeft());
      game.dropAllPairs(players);
      game.dropAllPairs(players);
      while(game.outCount < initNames.size()-1){
        game.allTakeCards(players);
        game.showAllHands(players);
      }
      game.gameOver(players);
      if (viewer.playAgain() == false){
        gameActive = false;
      }
    }
  }
}
}




