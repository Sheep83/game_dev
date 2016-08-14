package game;
import java.util.*;

public class Viewer {

  Scanner user_input = new Scanner( System.in );
  int numPlayers;
  public ArrayList<String> playerNames;

  public Viewer(){
    this.playerNames = new ArrayList<String>();  
  }
  public ArrayList<String> gameStart(){
    System.out.print("\f");
    System.out.println("Welcome to Scabby Queen!");
    System.out.println("How many players? ");
    String numString = user_input.nextLine();
    int numPlayers = Integer.parseInt(numString);
    System.out.println(numPlayers + " players entered.");
    for (int i=0;i<numPlayers ;i++ ) {
      System.out.println("Player Name? ");
      String name = user_input.nextLine();
      playerNames.add(name);
      System.out.println(playerNames);
    }
    return playerNames;
  }
  public boolean playAgain(){
    System.out.println("Play Again? (y/n)");
    String decision = user_input.nextLine();
    boolean yn = true;
    switch(decision){
      case "y":
      yn = true;
      break;
      case "n": 
      yn = false;
      break;
    }
    return yn;
  }
  public int chooseCard(Player takingPlayer, Player givingPlayer){
    System.out.println(takingPlayer.getName() + ", choose card to take from " + givingPlayer.getName() + "(1 to " + givingPlayer.getHandSize() + ")");
    String cardString = user_input.nextLine();
    int cardInt = Integer.parseInt(cardString);
    cardInt -=1;
    return cardInt;
  }
  public int chooseGame(){
    System.out.println("Choose game to play : ");
    System.out.println("1 : Scabby Queen");
    System.out.println("2 : Blackjack");
    String gameString = user_input.nextLine();
    int gameInt = Integer.parseInt(gameString);
    return gameInt;
  }
}

