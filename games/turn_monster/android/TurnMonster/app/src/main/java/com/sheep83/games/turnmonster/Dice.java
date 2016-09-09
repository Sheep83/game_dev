package com.sheep83.games.turnmonster;

import android.util.Log;

import java.util.Random;

/**
 * Created by user on 03/09/2016.
 */
public class Dice {

    private Integer sides;

    public Dice(){
        this.sides = null;
    }

    public Dice(Integer sides){
        this.sides = sides;

    }

    //replicated in player class. undecided.
    public int roll(){
         Random random = new Random();
         int  n = random.nextInt(this.sides);
//         Log.d("dice rolled ", "" + n);
        return n;
    }

    public Integer getSides(){
        if(this.sides != null){
            return this.sides;
        }else {
            return null;
        }
    }

    public void setSides(int sides){
        this.sides = sides;
    }


}
