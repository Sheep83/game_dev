package com.sheep83.games.turnmonster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by user on 09/09/2016.
 */
public class ActivityCharacterView extends AppCompatActivity {
    ArrayList<Player> mPlayers = new ArrayList<>();
    ArrayList<Skill> mKnownSkills = new ArrayList<>();
    ArrayList<Loot> mInventory = new ArrayList<>();
    ArrayList<Loot> mEquipped = new ArrayList<>();
    Player mPlayer;
    Bundle extras;
    ListView mSkillListView, mEquipListView, mInvListView;
    TextView mNameHeader, mPlayerName, mLevelheader, mPlayerLevel;
    Button mViewInventory, mHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_view);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Gson gson = new Gson();
            String playerjson = extras.getString("player");
            Log.d("Player JSON String : ", "" + playerjson);
            Type objectType = new TypeToken<ArrayList<Player>>() {
            }.getType();
            mPlayers = gson.fromJson(playerjson, objectType);
            mPlayer = mPlayers.get(0);
        } else {
            Log.d("Game: ", "Time and space is broken :(");
            Intent newIntent = new Intent(ActivityCharacterView.this, ActivityNewPlayer.class);
            startActivity(newIntent);
        }

        mNameHeader = (TextView) findViewById(R.id.name_header);
        mPlayerName = (TextView) findViewById(R.id.player_name);
        mLevelheader = (TextView) findViewById(R.id.level_header);
        mPlayerLevel = (TextView) findViewById(R.id.player_level);
        mPlayerName.setText(mPlayer.getName());
        mPlayerLevel.setText(String.valueOf(mPlayer.getLevel()));
        mViewInventory = (Button) findViewById(R.id.inventory_button);
        mHome = (Button) findViewById(R.id.home_button);
        mKnownSkills = mPlayer.getSkillsArray();
        mSkillListView = (ListView)findViewById(R.id.skill_list_view);
        ArrayAdapter<Skill> arrayAdapter = new SkillArrayListAdapter(this, android.R.layout.simple_list_item_1, mKnownSkills);
        mSkillListView.setAdapter(arrayAdapter);

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(ActivityCharacterView.this, Game.class);
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
                Intent intent = new Intent(ActivityCharacterView.this, ActivityInventoryView.class);
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

        }

    }


