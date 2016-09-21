package com.sheep83.games.turnmonster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 03/09/2016.
 */
public class GameInit extends AppCompatActivity{

    TextView mVs, mGameInitHeader, mPlayerName, mPlayerLevel, mMNameDisplay, mMTypeDisplay, mPlayerNameHeader, mLevelHeader, mHealthHeader, mPlayerHealth, mMonsterNameHeader, mMonsterTypeHeader, mMonsterLevelHeader, mMLevelDisplay;
    Button mStartGame;
    Player mPlayer;
    Monster mGhost, mGoblin, mOrc, mTroll, mDragon, mMonster;
    Dice nullDice = new Dice();
    Dice gameDice = new Dice(100);
    ArrayList<String> mMonsterNames = new ArrayList<String>(Arrays.asList("Theo", "Val", "Jay", "Mike", "Marie", "Ashleigh", "Chris", "Joe", "Russell", "Iain", "Euan", "Sam", "Natalie", "Megan", "Renwick", "Josh", "Nico", "Davide"));
    ArrayList<Monster> mMonsters = new ArrayList<>();
    ArrayList<Monster> levelAppropriate = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_init);
        mGhost = new Monster("Ghost", 100, 1, 1.1, 0.9);
        mMonsters.add(mGhost);
        mGoblin = new Monster("Goblin", 110, 2, 1.2, 1.1);
        mMonsters.add(mGoblin);
        mOrc = new Monster("Orc", 125, 3, 1.1, 1.3);
        mMonsters.add(mOrc);
        mTroll = new Monster("Troll", 140, 5, 1.2, 1.3);
        mMonsters.add(mTroll);
        mDragon = new Monster("Dragon", 200, 9, 1.4, 1.4);
        mMonsters.add(mDragon);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String json = extras.getString("tm_player");
        Log.d("Player JSON String : ", "" + json);
        Gson gson = new Gson();
        mPlayer = gson.fromJson(json, Player.class);
        for(Monster monster : mMonsters){
            if(monster.getMinLevel() <= mPlayer.getLevel()){
                levelAppropriate.add(monster);
            }
        }
        nullDice.setSides(levelAppropriate.size());
        mMonster = levelAppropriate.get(nullDice.roll());
        nullDice.setSides(mMonsterNames.size());
        mMonster.setName(mMonsterNames.get(nullDice.roll()));
        mMonster.setLevel(mPlayer.rollForMonsterLevel(gameDice));
        mMonster.calcStats();
        mGameInitHeader = (TextView) findViewById(R.id.init_header);
        mPlayerNameHeader = (TextView) findViewById(R.id.player_name_header);
        mPlayerName = (TextView) findViewById(R.id.player_name);
        mLevelHeader = (TextView) findViewById(R.id.player_level_header);
        mPlayerLevel = (TextView) findViewById(R.id.player_level);
        mHealthHeader = (TextView) findViewById(R.id.player_health_header);
        mPlayerHealth = (TextView) findViewById(R.id.player_health);
        mVs = (TextView) findViewById(R.id.vs);
        mMonsterNameHeader = (TextView) findViewById(R.id.monster_name_header);
        mMNameDisplay = (TextView) findViewById(R.id.monster_name);
        mMonsterTypeHeader = (TextView) findViewById(R.id.monster_type_header);
        mMTypeDisplay = (TextView) findViewById(R.id.monster_type);
        mMonsterLevelHeader = (TextView) findViewById(R.id.monster_level_header);
        mMLevelDisplay = (TextView) findViewById(R.id.monster_level);
        mStartGame = (Button) findViewById(R.id.start_button);
//        mCharacterView = (Button) findViewById(R.id.character_button);
        mPlayerName.setText(mPlayer.getName());
        mPlayerHealth.setText(String.valueOf(mPlayer.getHealth()));
        mPlayerLevel.setText(String.valueOf(mPlayer.getLevel()));
        mMNameDisplay.setText(String.valueOf(mMonster.getName()));
        mMTypeDisplay.setText(String.valueOf(mMonster.getType()));
        mMLevelDisplay.setText(String.valueOf(mMonster.getLevel()));


        mStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playerjson = new Gson().toJson(mPlayer);
                String monsterjson = new Gson().toJson(mMonster);
                String gamedicejson = new Gson().toJson(gameDice);
                String nulldicejson = new Gson().toJson(nullDice);
                Intent newIntent = new Intent(GameInit.this, ActivityCombat.class);
                newIntent.putExtra("player", playerjson);
                newIntent.putExtra("monster", monsterjson);
                newIntent.putExtra("gamedice", gamedicejson);
                newIntent.putExtra("nulldice", nulldicejson);
//                Log.d("Game:", "Start Button Clicked!");
                startActivity(newIntent);
            }
        });

//        mCharacterView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context context = view.getContext();
//                String json_saved = SavedTaskPreferences.getStoredPlayer(context);
//                if (json_saved == null) {
//                    Intent intent = new Intent(GameInit.this, ActivityNewPlayer.class);
//                    Toast.makeText(GameInit.this, R.string.no_player_present,
//                            Toast.LENGTH_SHORT).show();
//                    Log.d("Game:", "Character Button Clicked but no saved player present");
//                    startActivity(intent);
//                } else {
////                        Gson gson = new Gson();
////                        Type objectType = new TypeToken<ArrayList<Player>>() {
////                        }.getType();
////                        ArrayList<Player> mPlayersArray = gson.fromJson(json_saved, objectType);
////                        Log.d("Saved Player : ", mPlayersArray.get(0).getName() + "");
////                        String playerjson = new Gson().toJson(mPlayersArray.get(0));
//                    Intent intent = new Intent(GameInit.this, ActivityCharacterView.class);
//                    intent.putExtra("player", json_saved);
//                    Log.d("Game:", "Character View Button Clicked!");
//                    startActivity(intent);
//                }
//            }
//
//        });

    }
}
