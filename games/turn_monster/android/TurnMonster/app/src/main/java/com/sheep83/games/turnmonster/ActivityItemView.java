package com.sheep83.games.turnmonster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by user on 19/09/2016.
 */
public class ActivityItemView extends AppCompatActivity {
    ArrayList<Player> mPlayers;
    ArrayList<Skill> mKnownSkills = new ArrayList<>();
    ArrayList<Loot> mInventory = new ArrayList<>();
    ArrayList<Loot> mEquipped = new ArrayList<>();
    Integer mIndex;
    Loot mItem;
    Player mPlayer;
    Bundle extras;
    TextView mNameHeader, mItemName, mLevelheader, mItemLevel, mDamageHeader, mDamage, mIntHeader, mInt, mVitHeader, mVit;
    Button mViewInventory, mHome, mEquipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Gson gson = new Gson();
            String playerjson = extras.getString("player");
            Log.d("Player JSON String : ", "" + playerjson);
            String itemjson = extras.getString("item");
            Log.d("Item JSON String : ", "" + itemjson);
            mPlayer = gson.fromJson(playerjson, Player.class);
            mItem = gson.fromJson(itemjson, Loot.class);
            String indexjson = extras.getString("index");
            mIndex = Integer.parseInt(indexjson);
        } else {
            Log.d("Game: ", "Time and space is broken :(");
            Intent newIntent = new Intent(ActivityItemView.this, ActivityNewPlayer.class);
            startActivity(newIntent);
        }

        mNameHeader = (TextView) findViewById(R.id.name_header);
        mItemName = (TextView) findViewById(R.id.item_name);
        mLevelheader = (TextView) findViewById(R.id.level_header);
        mItemLevel = (TextView) findViewById(R.id.item_level);
        mIntHeader = (TextView) findViewById(R.id.int_header);
        mInt = (TextView) findViewById(R.id.item_intellect);
        mVitHeader = (TextView) findViewById(R.id.vit_header);
        mVit = (TextView) findViewById(R.id.item_vitality);
//        mPlayerName.setText(mPlayer.getName());
//        mPlayerLevel.setText(String.valueOf(mPlayer.getLevel()));
//        mHealth.setText(String.valueOf(mPlayer.getHealth()));
        mItemName.setText(String.valueOf(mItem.getName()));
        mItemLevel.setText(String.valueOf(mItem.getLevel()));
        mInt.setText(String.valueOf(mItem.getIntellect()));
        mVit.setText(String.valueOf(mItem.getVitality()));
        mViewInventory = (Button) findViewById(R.id.inventory_button);
        mHome = (Button) findViewById(R.id.home_button);
        mEquipButton = (Button) findViewById(R.id.equip_item);

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(ActivityItemView.this, Game.class);
                Log.d("Game:", "Home button pressed");
                ArrayList<Player> mPlayers = new ArrayList<>();
                mPlayers.add(mPlayer);
                //save player here
                String newplayerarrayjson = new Gson().toJson(mPlayers);
                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                startActivity(intent);
            }
        });

        mViewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(ActivityItemView.this, ActivityInventoryView.class);
                Log.d("Game:", "Inventory button pressed");
                ArrayList<Player> mPlayers = new ArrayList<>();
                mPlayers.add(mPlayer);
                //save player here
                String newplayerarrayjson = new Gson().toJson(mPlayers);
                intent.putExtra("player", newplayerarrayjson);
                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                startActivity(intent);
            }
        });

        mEquipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(ActivityItemView.this, ActivityCharacterView.class);
                mPlayer.equipFromInventory(mItem, mIndex);
                Log.d("Game:", "" + mItem + "equipped");
                ArrayList<Player> mPlayers = new ArrayList<>();
                mPlayers.add(mPlayer);
                //save player here
                String newplayerarrayjson = new Gson().toJson(mPlayers);
                intent.putExtra("player", newplayerarrayjson);
                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                startActivity(intent);
            }
        });

    }
}
