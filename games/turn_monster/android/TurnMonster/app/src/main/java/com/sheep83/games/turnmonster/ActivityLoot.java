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
 * Created by user on 09/09/2016.
 */
public class ActivityLoot extends AppCompatActivity {


    private TextView mPlayerHeader, mPlayerXpHeader, mPlayerName, mPlayerXp, mLootHeader, mLootText;
    private Button mTake, mDiscard;
    private Player mPlayer;
    private Loot mLoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loot);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Gson gson = new Gson();
        if(extras.getString("looted") != null)
        {
            String lootjson = extras.getString("looted");
            Log.d("Looted JSON String : ", "" + lootjson);
            mLoot = gson.fromJson(lootjson, Loot.class);
        }
        String playerjson = extras.getString("player");
        Log.d("Player JSON String : ", "" + playerjson);
        mPlayer = gson.fromJson(playerjson, Player.class);
        mPlayerHeader = (TextView) findViewById(R.id.player_header);
        mPlayerName = (TextView) findViewById(R.id.player_name);
        mPlayerXpHeader = (TextView) findViewById(R.id.xp_header);
        mPlayerXp = (TextView) findViewById(R.id.player_xp);
        mLootHeader = (TextView) findViewById(R.id.loot_header);
        mLootText = (TextView) findViewById(R.id.loot_text);
        mTake = (Button) findViewById(R.id.take);
        mDiscard = (Button) findViewById(R.id.discard);
        if(mLoot != null)
        {
            mLootText.setText(mLoot.getType() + "" + mLoot.getEnchant());
        }
        mPlayerName.setText(mPlayer.getName());
        mPlayerXp.setText(String.valueOf(mPlayer.getXp()));

        mTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(ActivityLoot.this, Game.class);
                mPlayer.addToInventory(mLoot);
                Log.d("Game:", "Item Looted!");
                //reset health and mana here
                mPlayer.resetStats();
                mPlayer.checkLevel();
                ArrayList<Player> mPlayers = new ArrayList<>();
                mPlayers.add(mPlayer);
                //save player here
                String newplayerjson = new Gson().toJson(mPlayers);
                SavedTaskPreferences.setStoredPlayer(context, newplayerjson);
                startActivity(intent);
            }
        });

        mDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(ActivityLoot.this, Game.class);
                Log.d("Game:", "Item Discarded!");
                //reset health and mana here
                mPlayer.resetStats();
                mPlayer.checkLevel();
                ArrayList<Player> mPlayers = new ArrayList<>();
                mPlayers.add(mPlayer);
                //save player here
                String newplayerarrayjson = new Gson().toJson(mPlayers);
                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                startActivity(intent);
            }
        });
    }


}
