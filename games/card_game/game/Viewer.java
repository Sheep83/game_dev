package game;
import java.util.*;

public class Viewer {

Scanner user_input = new Scanner( System.in );
int numPlayers;
public ArrayList<String> playerNames = new ArrayList<String>();

   // public String 
   // public String 


  public Viewer(){
  
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
}