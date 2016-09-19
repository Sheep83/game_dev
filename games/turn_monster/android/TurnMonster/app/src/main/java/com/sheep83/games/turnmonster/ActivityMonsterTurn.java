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

import java.util.ArrayList;

/**
 * Created by user on 04/09/2016.
 */
public class ActivityMonsterTurn extends AppCompatActivity {

    TextView mCommentary, mPlayerHealth;
    Button mContinue;
    Player mPlayer;
    Monster mMonster;
    Dice mNullDice, mGamedice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_monster);
        mCommentary = (TextView) findViewById(R.id.commentary);
        mPlayerHealth = (TextView) findViewById(R.id.player_health);
        mContinue = (Button) findViewById(R.id.proceed);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Gson gson = new Gson();
        String playerjson = extras.getString("player");
        Log.d("Player JSON String : ", "" + playerjson);
        String monsterjson = extras.getString("monster");
        Log.d("Monster JSON String : ", "" + monsterjson);
        String gamedicejson = extras.getString("gamedice");
        Log.d("Gamedice JSON String : ", "" + gamedicejson);
        String nulldicejson = extras.getString("nulldice");
        Log.d("Nulldice JSON String : ", "" + nulldicejson);
        mPlayer = gson.fromJson(playerjson, Player.class);
        mMonster = gson.fromJson(monsterjson, Monster.class);
        mGamedice = gson.fromJson(gamedicejson, Dice.class);
        mNullDice = gson.fromJson(nulldicejson, Dice.class);
        mNullDice.setSides(mMonster.getBaseDamage());
        int damage = mNullDice.roll();
        mMonster.meleeAttack(mPlayer, damage);
        mCommentary.setText(mMonster.getName() + " attacks " + mPlayer.getName() + " and deals " + damage + " damage");
        mPlayerHealth.setText("Player Health: " + mPlayer.getHealth());
        if (mPlayer.deadCheck()) {
            mPlayer.setHealth(0);
        }
        mPlayer.setActive(true);
        mMonster.setActive(false);

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Log.d("Game:", "Continuing...");
                String newplayerjson = new Gson().toJson(mPlayer);
                String newmonsterjson = new Gson().toJson(mMonster);
                String newgamedicejson = new Gson().toJson(mGamedice);
                String newnulldicejson = new Gson().toJson(mNullDice);
                Intent newIntent = new Intent(ActivityMonsterTurn.this, ActivityCombat.class);
                newIntent.putExtra("player", newplayerjson);
                newIntent.putExtra("monster", newmonsterjson);
                newIntent.putExtra("gamedice", newgamedicejson);
                newIntent.putExtra("nulldice", newnulldicejson);
                startActivity(newIntent);
            }
        });



    }
}