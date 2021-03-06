package com.sheep83.games.turnmonster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by user on 04/09/2016.
 */
public class ActivityGameOver extends AppCompatActivity {

    TextView mCommentary;
    Button mRestart;
    Player mPlayer;
    Monster mMonster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mCommentary = (TextView) findViewById(R.id.commentary);
        mCommentary.setText(String.valueOf(mPlayer.getName() + " was killed by " + mMonster.getName() + " :-("));
        mRestart = (Button) findViewById(R.id.restart);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Gson gson = new Gson();
        String player = extras.getString("player");
        String monster = extras.getString("monster");
        Log.d("Player : ", "" + player);
        Log.d("Monster : ", "" + monster);
        mPlayer = gson.fromJson(player, Player.class);
        mMonster = gson.fromJson(monster, Monster.class);

        mRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                mPlayer.resetStats();
                ArrayList<Player> mPlayers = new ArrayList<>();
                mPlayers.add(mPlayer);
                //save player here
                String newplayerarrayjson = new Gson().toJson(mPlayers);
                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                Intent intent = new Intent(ActivityGameOver.this, Game.class);
                Log.d("Game:", "Restart Button Clicked!");
                startActivity(intent);
            }
        });

        }

    }
