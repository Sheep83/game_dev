package game;
import java.util.*;

public class Card {


 public String suit;
 public String value;


 public Card(String suit, String value){
    this.suit = suit;
    this.value = value;
}
public String getSuit(){
    return suit;
}
public void setSuit(String suit){
    this.suit = suit;
}
public String getValue(){
    return value;
}
public void setValue(String value){
    this.value = value;
}
public String toString(){
    return "\n"+value + " of "+ suit;
}
}