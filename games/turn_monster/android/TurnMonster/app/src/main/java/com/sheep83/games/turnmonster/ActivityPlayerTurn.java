package com.sheep83.games.turnmonster;

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
    TextView mPlayerNameHeader, mPlayerName, mPlayerHealthHeader, mPlayerHealth, mPlayerManaHeader, mPlayerMana, mPlayerLevel, mMNameHeader, mMNameDisplay, mMTypeHeader, mMTypeDisplay, mMHealthHeader, mMonsterHealth;
//    Button mMelee;
//    Button mFireball;
//    Button mEvocation;
    Player mPlayer;
    Monster mMonster;
    Dice mNullDice, mGamedice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_player);
        mPlayerNameHeader = (TextView) findViewById(R.id.player_name_header);
        mPlayerHealthHeader = (TextView) findViewById(R.id.player_health_header);
        mPlayerManaHeader = (TextView) findViewById(R.id.player_mana_header);
        mMNameHeader = (TextView) findViewById(R.id.monster_name_header);
        mMTypeHeader = (TextView) findViewById(R.id.monster_type_header);
        mMHealthHeader = (TextView) findViewById(R.id.monster_health_header);
        mPlayerName = (TextView) findViewById(R.id.player_name);
//        mPlayerLevel = (TextView) findViewById(R.id.player_level);
        mPlayerHealth = (TextView) findViewById(R.id.player_health);
        mPlayerMana = (TextView) findViewById(R.id.player_mana);
        mMNameDisplay = (TextView) findViewById(R.id.monster_name);
        mMTypeDisplay = (TextView) findViewById(R.id.monster_type);
        mMonsterHealth = (TextView) findViewById(R.id.monster_health);
        mSkillListView = (ListView)findViewById(R.id.skill_list_view);
//        mMelee = (Button) findViewById(R.id.melee);
//        mFireball = (Button) findViewById(R.id.fireball);
//        mEvocation = (Button) findViewById(R.id.evocation);
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
//        mPlayerLevel.setText(String.valueOf(mPlayer.getLevel()));
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
                Toast.makeText(getApplicationContext(), mPlayer.getName() + " uses " + selectedSkill.getName() + " on " + mMonster.getName() , Toast.LENGTH_SHORT).show();
//                String json = new Gson().toJson(selectedTask);
                mPlayer.attack(mMonster, selectedSkill, mNullDice);
//                mPlayer.incMana(10 + mPlayer.getManaMod());
                if (mMonster.deadCheck())
                {
                    mMonster.setHealth(0);
                }
                mPlayer.setActive(false);
                mMonster.setActive(true);
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


//        mMelee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mPlayer.attack(mMonster, mPlayer.getSkill(0), mNullDice);
//                mPlayer.incMana(5);
//                if (mMonster.deadCheck())
//                {
//                    mMonster.setHealth(0);
//                }
//                mPlayer.setActive(false);
//                mMonster.setActive(true);
//                String playerjson = new Gson().toJson(mPlayer);
//                String monsterjson = new Gson().toJson(mMonster);
//                String gamedicejson = new Gson().toJson(mGamedice);
//                String nulldicejson = new Gson().toJson(mNullDice);
//                Intent intent = new Intent(ActivityPlayerTurn.this, ActivityCombat.class);
//                intent.putExtra("player", playerjson);
//                intent.putExtra("monster", monsterjson);
//                intent.putExtra("gamedice", gamedicejson);
//                intent.putExtra("nulldice", nulldicejson);
//                Log.d("Game:", "Melee Button Clicked!");
//                startActivity(intent);
//            }
//        });
//
//        mFireball.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mPlayer.attack(mMonster, mPlayer.getSkill(1), mNullDice);
//                mPlayer.incMana(5);
//                if (mMonster.deadCheck())
//                {
//                    mMonster.setHealth(0);
//                }
//                mPlayer.setActive(false);
//                mMonster.setActive(true);
//                String playerjson = new Gson().toJson(mPlayer);
//                String monsterjson = new Gson().toJson(mMonster);
//                String gamedicejson = new Gson().toJson(mGamedice);
//                String nulldicejson = new Gson().toJson(mNullDice);
//                Intent intent = new Intent(ActivityPlayerTurn.this, ActivityCombat.class);
//                intent.putExtra("player", playerjson);
//                intent.putExtra("monster", monsterjson);
//                intent.putExtra("gamedice", gamedicejson);
//                intent.putExtra("nulldice", nulldicejson);
//                Log.d("Game:", "Fireball Button Clicked!");
//                startActivity(intent);
//            }
//        });
//
//        mEvocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ActivityPlayerTurn.this, ActivityCombat.class);
//                Log.d("Game:", "Evocation Button Clicked!");
////                startActivity(intent);
//            }
//        });
    }
}
