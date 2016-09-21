package com.sheep83.games.turnmonster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;


import java.util.ArrayList;

/**
 * Created by user on 03/09/2016.
 */
public class ActivityNewPlayer extends AppCompatActivity{

    EditText mPlayerName;
    Button mCreatePlayer;
    ArrayList<Player> mPlayersArray;
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        mPlayerName = (EditText) findViewById(R.id.player_name_input);
        mCreatePlayer = (Button) findViewById(R.id.create_button);


        mCreatePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                    mPlayersArray = new ArrayList<Player>();
                    player = new Player (mPlayerName.getText().toString());
                    Skill melee = new Skill("Melee", "Hit it with a stick", 40, 0, 0);
                    melee.setMinDamage(20);
                    melee.setKnown(true);
                    Skill fireball = new Skill("Fireball", "Huge ball of flame", 70, 40, 1);
                    fireball.setMinDamage(35);
                    Skill wallofflame = new Skill("Wall Of Flame", "Thou shalt not pass!", 85, 70, 2);
                    wallofflame.setMinDamage(45);
                    player.addSkill(melee);
                    player.addToSkillTree(fireball);
                    player.addToSkillTree(wallofflame);
                    mPlayersArray.add(player);
                    String json = new Gson().toJson(player);
                    Log.d("New Player : ", json + "");
                    Intent intent = new Intent(ActivityNewPlayer.this, GameInit.class);
                    intent.putExtra("tm_player", json);
                    String arrayjson = new Gson().toJson(mPlayersArray);
                    SavedTaskPreferences.setStoredPlayer(context, arrayjson);
                    startActivity(intent);
            }
        });

    }
}
