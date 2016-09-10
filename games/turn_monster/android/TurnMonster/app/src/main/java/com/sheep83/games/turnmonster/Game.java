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
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by user on 03/09/2016.
 */
public class Game extends AppCompatActivity {
    TextView mTitleText;
    Button mStartButton, mClearButton, mCharacterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        mTitleText = (TextView) findViewById(R.id.title_text);
        mStartButton = (Button) findViewById(R.id.start_button);
        mClearButton = (Button) findViewById(R.id.clear_button);
        mCharacterButton = (Button) findViewById(R.id.character_button);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                String json_saved = SavedTaskPreferences.getStoredPlayer(context);
                if (json_saved == null) {
                    Intent intent = new Intent(Game.this, ActivityNewPlayer.class);
                    Log.d("Game:", "Start Button Clicked!");
                    startActivity(intent);
                } else {
                    Gson gson = new Gson();
                    Type objectType = new TypeToken<ArrayList<Player>>() {
                    }.getType();
                    ArrayList<Player> mPlayersArray = gson.fromJson(json_saved, objectType);
                    Log.d("Saved Player : ", mPlayersArray.get(0).getName() + "");
                    String playerjson = new Gson().toJson(mPlayersArray.get(0));
                    Intent intent = new Intent(Game.this, GameInit.class);
                    intent.putExtra("tm_player", playerjson);
                    Log.d("Game:", "Create Button Clicked!");
                    startActivity(intent);
                }
            }
        });

        mCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Context context = view.getContext();
                    String json_saved = SavedTaskPreferences.getStoredPlayer(context);
                    if (json_saved == null) {
                        Intent intent = new Intent(Game.this, ActivityNewPlayer.class);
                        Toast.makeText(Game.this, R.string.no_player_present,
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
                        Intent intent = new Intent(Game.this, ActivityCharacterView.class);
                        intent.putExtra("player", json_saved);
                        Log.d("Game:", "Character View Button Clicked!");
                        startActivity(intent);
                    }
                }

        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Game:", "Clear Button Clicked!");
                Toast.makeText(Game.this, R.string.settings_clear,
                        Toast.LENGTH_SHORT).show();
                Context context = view.getContext();
                SavedTaskPreferences.clearPrefs(context);
            }
        });



    }
}