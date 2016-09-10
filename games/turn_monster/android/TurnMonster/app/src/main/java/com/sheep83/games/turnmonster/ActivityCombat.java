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

/**
 * Created by user on 03/09/2016.
 */
public class ActivityCombat extends AppCompatActivity {

    ArrayList<String> mLootTypes = new ArrayList<>();
    ArrayList<String> mLootEnchants = new ArrayList<>();
    Player mPlayer;
    Monster mMonster;
    Dice mNullDice, mGamedice;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
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
        } else {
            Log.d("Howd that happen? ", "");
            Intent newIntent = new Intent(ActivityCombat.this, ActivityNewPlayer.class);
            startActivity(newIntent);

        }
//        mPlayerName.setText(mPlayer.getName());
//        mPlayerLevel.setText(String.valueOf(mPlayer.getLevel()));
//        mPlayerHealth.setText(String.valueOf(mPlayer.getHealth()));
//        mMNameDisplay.setText(mMonster.getName());
//        mMTypeDisplay.setText(mMonster.getType());
//        mMonsterHealth.setText(String.valueOf(mMonster.getHealth()));
        do {
            if (mPlayer.getActive() && !mPlayer.deadCheck()) {
                setContentView(R.layout.activity_combat_player);
                String playerjson = new Gson().toJson(mPlayer);
                String monsterjson = new Gson().toJson(mMonster);
                String gamedicejson = new Gson().toJson(mGamedice);
                String nulldicejson = new Gson().toJson(mNullDice);
                Intent playerIntent = new Intent(ActivityCombat.this, ActivityPlayerTurn.class);
                playerIntent.putExtra("player", playerjson);
                playerIntent.putExtra("monster", monsterjson);
                playerIntent.putExtra("gamedice", gamedicejson);
                playerIntent.putExtra("nulldice", nulldicejson);
                startActivity(playerIntent);
            } else {
                setContentView(R.layout.activity_combat_monster);
                String playerjson = new Gson().toJson(mPlayer);
                String monsterjson = new Gson().toJson(mMonster);
                String gamedicejson = new Gson().toJson(mGamedice);
                String nulldicejson = new Gson().toJson(mNullDice);
                Intent playerIntent = new Intent(ActivityCombat.this, ActivityMonsterTurn.class);
                playerIntent.putExtra("player", playerjson);
                playerIntent.putExtra("monster", monsterjson);
                playerIntent.putExtra("gamedice", gamedicejson);
                playerIntent.putExtra("nulldice", nulldicejson);
                startActivity(playerIntent);
            }

        }
        while (mPlayer.deadCheck() && mMonster.deadCheck());

        if(mPlayer.deadCheck() || mMonster.deadCheck()){
            if(mMonster.deadCheck()){
                mPlayer.incXp(100);
                // check for level up here
                if(mPlayer.rollForLoot(mGamedice)){
                    // create random loot item here
                    mLootTypes.add("Sword");
                    mLootTypes.add("Staff");
                    mLootTypes.add("Dagger");
                    mLootTypes.add("Axe");
                    mLootTypes.add("Hammer");
                    mLootEnchants.add("Fire");
                    mLootEnchants.add("Ice");
                    mLootEnchants.add("Arcane");
                    mNullDice.setSides(mLootTypes.size());
                    String lootType = mLootTypes.get(mNullDice.roll());
                    mNullDice.setSides(mLootEnchants.size());
                    String lootEnchant = mLootEnchants.get(mNullDice.roll());
                    Loot loot = new Loot(lootType, lootEnchant, 1);
                    loot.setFocus(2);
                    // push loot to extras here
                    String looted = new Gson().toJson(loot);
                    String player = new Gson().toJson(mPlayer);
                    Intent lootIntent = new Intent(ActivityCombat.this, ActivityLoot.class);
                    lootIntent.putExtra("player", player);
                    lootIntent.putExtra("looted", looted);
                    startActivity(lootIntent);
                }
            }else
            {
                String newplayerjson = new Gson().toJson(mPlayer);
                String newmonsterjason = new Gson().toJson(mMonster);
                Intent gameOverIntent = new Intent(ActivityCombat.this, ActivityGameOver.class);
                gameOverIntent.putExtra("player", newplayerjson);
                gameOverIntent.putExtra("monster", newmonsterjason);
                startActivity(gameOverIntent);
            }
        }

        }
    }




