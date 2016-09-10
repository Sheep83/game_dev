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

    String mMonsterName, mMonsterType;
    TextView mGameInitHeader, mPlayerName, mPlayerLevel, mMNameDisplay, mMTypeDisplay;
    Button mStartGame, mCharacterView;
    Player mPlayer;
    Monster monster;
    Dice nullDice = new Dice();
    Dice gameDice = new Dice(100);
    ArrayList<String> mMonsterNames = new ArrayList<String>(Arrays.asList("theo", "val", "jay"));
    ArrayList<String> mMonsterTypes = new ArrayList<String>(Arrays.asList("orc", "dragon", "troll", "ghost"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_init);
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
        if(mPlayer.getEquipped() > 0){
            for(Loot item : mPlayer.getEquippedArray()){
                mPlayer.incFocus(item.getFocus());
            }
        }
        monster = new Monster(mMonsterName, mMonsterType, 100, 1);
        mGameInitHeader = (TextView) findViewById(R.id.init_header);
        mPlayerName = (TextView) findViewById(R.id.player_text);
        mPlayerLevel = (TextView) findViewById(R.id.player_level);
        mMNameDisplay = (TextView) findViewById(R.id.monster_text);
        mMTypeDisplay = (TextView) findViewById(R.id.monster_type);
        mStartGame = (Button) findViewById(R.id.start_button);
        mCharacterView = (Button) findViewById(R.id.character_button);
        mPlayerName.setText(mPlayer.getName());
        mPlayerLevel.setText(String.valueOf(mPlayer.getLevel()));
        mMNameDisplay.setText(String.valueOf(monster.getName()));
        mMTypeDisplay.setText(String.valueOf(monster.getType()));

        mStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        mCharacterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                String json_saved = SavedTaskPreferences.getStoredPlayer(context);
                if (json_saved == null) {
                    Intent intent = new Intent(GameInit.this, ActivityNewPlayer.class);
                    Toast.makeText(GameInit.this, R.string.no_player_present,
                            Toast.LENGTH_SHORT).show();
                    Log.d("Game:", "Character Button Clicked but no saved player present");
                    startActivity(intent);
                } else {
//                        Gson gson = new Gson();
//                        Type objectType = new TypeToken<ArrayList<Player>>() {
//                        }.getType();
//                        ArrayList<Player> mPlayersArray = gson.fromJson(json_saved, objectType);
//                        Log.d("Saved Player : ", mPlayersArray.get(0).getName() + "");
//                        String playerjson = new Gson().toJson(mPlayersArray.get(0));
                    Intent intent = new Intent(GameInit.this, ActivityCharacterView.class);
                    intent.putExtra("player", json_saved);
                    Log.d("Game:", "Character View Button Clicked!");
                    startActivity(intent);
                }
            }

        });

    }
}
