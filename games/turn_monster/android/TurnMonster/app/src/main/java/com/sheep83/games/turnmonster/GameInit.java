package com.sheep83.games.turnmonster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 03/09/2016.
 */
public class GameInit extends AppCompatActivity{

    String mMonsterName, mMonsterType;
    TextView mPlayerName, mPlayerLevel, mMNameDisplay, mMTypeDisplay;
//    Button mStartGame;
    Player mPlayer;
    Monster monster;
    Dice nullDice = new Dice();
    Dice gameDice = new Dice(100);
    ArrayList<String> mMonsterNames = new ArrayList<String>(Arrays.asList("theo", "val", "jay"));
    ArrayList<String> mMonsterTypes = new ArrayList<String>(Arrays.asList("orc", "dragon", "troll", "ghost"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nullDice.setSides(mMonsterNames.size());
        mMonsterName = mMonsterNames.get(nullDice.roll());
        nullDice.setSides(mMonsterTypes.size());
        mMonsterType = mMonsterTypes.get(nullDice.roll());
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String json = extras.getString("tm_player");
        Log.d("Player JSON String : ", "" + json);
        Gson gson = new Gson();
        mPlayer = gson.fromJson(json, Player.class);
        monster = new Monster(mMonsterName, mMonsterType, 100, 1);
//        mPlayerName.setText(mPlayer.getName());
//        mPlayerLevel.setText(String.valueOf(mPlayer.getLevel()));
//        mMNameDisplay.setText(String.valueOf(monster.getName()));
//        mMTypeDisplay.setText(String.valueOf(monster.getType()));

//        mStartGame.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                String playerjson = new Gson().toJson(mPlayer);
                String monsterjson = new Gson().toJson(monster);
                String gamedicejson = new Gson().toJson(gameDice);
                String nulldicejson = new Gson().toJson(nullDice);
                Intent newIntent = new Intent(GameInit.this, ActivityCombat.class);
                newIntent.putExtra("player", playerjson);
                newIntent.putExtra("monster", monsterjson);
                newIntent.putExtra("gamedice", gamedicejson);
                newIntent.putExtra("nulldice", nulldicejson);
//                Log.d("Game:", "Start Button Clicked!");
                startActivity(newIntent);
//            }
//        });

    }
}
