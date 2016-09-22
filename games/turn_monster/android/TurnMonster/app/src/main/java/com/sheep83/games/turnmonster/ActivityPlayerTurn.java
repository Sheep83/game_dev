package com.sheep83.games.turnmonster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by user on 04/09/2016.
 */
public class ActivityPlayerTurn extends AppCompatActivity {
    ListView mSkillListView;
    TextView mCommentary, mPlayerNameHeader, mPlayerName, mPlayerHealthHeader, mPlayerHealth, mPlayerManaHeader, mPlayerMana, mPlayerLevel, mMNameHeader, mMNameDisplay, mMTypeHeader, mMTypeDisplay, mMHealthHeader, mMonsterHealth;
    Button mContinue;
    Player mPlayer;
    Monster mMonster;
    Dice mNullDice, mGamedice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_player);
        mCommentary = (TextView) findViewById(R.id.commentary);
        mContinue = (Button) findViewById(R.id.proceed);
        mContinue.setVisibility(View.INVISIBLE);
        mPlayerNameHeader = (TextView) findViewById(R.id.player_name_header);
        mPlayerHealthHeader = (TextView) findViewById(R.id.player_health_header);
        mPlayerManaHeader = (TextView) findViewById(R.id.player_mana_header);
        mMNameHeader = (TextView) findViewById(R.id.monster_name_header);
        mMTypeHeader = (TextView) findViewById(R.id.monster_type_header);
        mMHealthHeader = (TextView) findViewById(R.id.monster_health_header);
        mPlayerName = (TextView) findViewById(R.id.player_name);
        mPlayerHealth = (TextView) findViewById(R.id.player_health);
        mPlayerMana = (TextView) findViewById(R.id.player_mana);
        mMNameDisplay = (TextView) findViewById(R.id.monster_name);
        mMTypeDisplay = (TextView) findViewById(R.id.monster_type);
        mMonsterHealth = (TextView) findViewById(R.id.monster_health);
        mSkillListView = (ListView)findViewById(R.id.skill_list_view);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Gson gson = new Gson();
        String playerjson = extras.getString("player");
        Log.d("Player JSON String : ", "" + playerjson);
        String monsterjson = extras.getString("monster");
        Log.d("Monster JSON String : ", "" + monsterjson);
        final String gamedicejson = extras.getString("gamedice");
        Log.d("Gamedice JSON String : ", "" + gamedicejson);
        String nulldicejson = extras.getString("nulldice");
        Log.d("Nulldice JSON String : ", "" + nulldicejson);
        mPlayer = gson.fromJson(playerjson, Player.class);
        mMonster = gson.fromJson(monsterjson, Monster.class);
        mGamedice = gson.fromJson(gamedicejson, Dice.class);
        mNullDice = gson.fromJson(nulldicejson, Dice.class);
        mPlayerName.setText(mPlayer.getName());
        mMNameDisplay.setText(String.valueOf(mMonster.getName()));
        mMTypeDisplay.setText(String.valueOf(mMonster.getType()));
        mMonsterHealth.setText(String.valueOf(mMonster.getHealth()));
        mPlayerHealth.setText(String.valueOf(mPlayer.getHealth()));
        mPlayerMana.setText(String.valueOf(mPlayer.getMana()));
        ArrayAdapter<Skill> arrayAdapter = new SkillArrayListAdapter(this, android.R.layout.simple_list_item_1, mPlayer.getSkillsArray());
        mSkillListView.setAdapter(arrayAdapter);

        mSkillListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                Skill selectedSkill = mPlayer.getSkillsArray().get(position);
                if (mPlayer.manaCheck(selectedSkill)) {
                    mPlayer.attack(mMonster, selectedSkill, mNullDice);
                    if (mMonster.deadCheck()) {
                        mMonster.setHealth(0);
                    }

                    mCommentary.setText(mPlayer.getName() + " attacks " + mMonster.getName() + " with " + selectedSkill.getName() + " and deals " + mPlayer.getDealtDamage() + " damage!");
                    mMNameDisplay.setText(String.valueOf(mMonster.getName()));
                    mMTypeDisplay.setText(String.valueOf(mMonster.getType()));
                    mMonsterHealth.setText(String.valueOf(mMonster.getHealth()));
                    mPlayerHealth.setText(String.valueOf(mPlayer.getHealth()));
                    mPlayerMana.setText(String.valueOf(mPlayer.getMana()));
                    mPlayer.setActive(false);
                    mMonster.setActive(true);
                    mContinue.setVisibility(View.VISIBLE);
                    mSkillListView.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getApplicationContext(), mPlayer.getName() + " has insufficient mana to cast  " + selectedSkill.getName(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Log.d("Game:", "Continuing...");
                Intent intent = new Intent(ActivityPlayerTurn.this, ActivityCombat.class);
                String playerjson = new Gson().toJson(mPlayer);
                String monsterjson = new Gson().toJson(mMonster);
                String gamedicejson = new Gson().toJson(mGamedice);
                String nulldicejson = new Gson().toJson(mNullDice);
                intent.putExtra("player", playerjson);
                intent.putExtra("monster", monsterjson);
                intent.putExtra("gamedice", gamedicejson);
                intent.putExtra("nulldice", nulldicejson);
                startActivity(intent);
            }
        });

    }
}
