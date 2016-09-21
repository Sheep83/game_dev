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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView mNameHeader, mPlayerName, mLevelheader, mPlayerLevel, mHealthHeader, mHealth, mIntHeader, mInt, mManaHeader, mMana;
    Button mViewInventory, mHome, mHomeButton, mCharacterView, mEquipButton, mDeleteButton;

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
        mHealthHeader = (TextView) findViewById(R.id.health_header);
        mHealth = (TextView) findViewById(R.id.player_health);
        mIntHeader = (TextView) findViewById(R.id.int_header);
        mInt = (TextView) findViewById(R.id.player_int);
        mManaHeader = (TextView) findViewById(R.id.mana_header);
        mMana = (TextView) findViewById(R.id.player_mana);
        mPlayerName.setText(mPlayer.getName());
        mPlayerLevel.setText(String.valueOf(mPlayer.getLevel()));
        mHealth.setText(String.valueOf(mPlayer.getHealth()));
        mInt.setText(String.valueOf(mPlayer.getIntellect()));
        mMana.setText(String.valueOf(mPlayer.getMana()));
        mViewInventory = (Button) findViewById(R.id.inventory_button);
        mHome = (Button) findViewById(R.id.home_button);
        mEquipped = mPlayer.getEquippedArray();
        mKnownSkills = mPlayer.getSkillsArray();
        mEquipListView = (ListView)findViewById(R.id.equip_list_view);
        mSkillListView = (ListView)findViewById(R.id.skill_list_view);
        ArrayAdapter<Skill> skillarrayAdapter = new SkillArrayListAdapter(this, android.R.layout.simple_list_item_1, mKnownSkills);
        ArrayAdapter<Loot> equiparrayAdapter = new LootArrayListAdapter(this, android.R.layout.simple_list_item_1, mEquipped);
        mEquipListView.setAdapter(equiparrayAdapter);
        mSkillListView.setAdapter(skillarrayAdapter);

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

        mEquipListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                setContentView(R.layout.activity_item_view);
                mNameHeader = (TextView) findViewById(R.id.name_header);
                TextView mItemName = (TextView) findViewById(R.id.item_name);
                mLevelheader = (TextView) findViewById(R.id.level_header);
                TextView mItemLevel = (TextView) findViewById(R.id.item_level);
                mIntHeader = (TextView) findViewById(R.id.int_header);
                mInt = (TextView) findViewById(R.id.item_intellect);
                TextView mVitHeader = (TextView) findViewById(R.id.vit_header);
                TextView mVit = (TextView) findViewById(R.id.item_vitality);
                mViewInventory = (Button) findViewById(R.id.inventory_button);
                mCharacterView = (Button) findViewById(R.id.character_button);
                mEquipButton = (Button) findViewById(R.id.equip_item);
//                mHomeButton = (Button) findViewById(R.id.home_button);
                mDeleteButton = (Button) findViewById(R.id.delete_button);
                mDeleteButton.setVisibility(View.GONE);
                mEquipButton.setVisibility(View.GONE);
//                mHomeButton.setVisibility(View.GONE);
                Loot selectedItem = mPlayer.getEquippedArray().get(position);
                mItemName.setText(String.valueOf(selectedItem.getName()));
                mItemLevel.setText(String.valueOf(selectedItem.getLevel()));
                mInt.setText(String.valueOf(selectedItem.getIntellect()));
                mVit.setText(String.valueOf(selectedItem.getVitality()));
                Toast.makeText(getApplicationContext(), selectedItem.getName() + " selected", Toast.LENGTH_SHORT).show();

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

                mCharacterView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = view.getContext();
                        String json_saved = SavedTaskPreferences.getStoredPlayer(context);
                        if (json_saved == null) {
                            Intent intent = new Intent(ActivityCharacterView.this, ActivityCharacterView.class);
                            Toast.makeText(ActivityCharacterView.this, R.string.back_to_character,
                                    Toast.LENGTH_SHORT).show();
                            Log.d("Game:", "Returning to character view");
                            startActivity(intent);
                        } else {
                            Gson gson = new Gson();
                            Type objectType = new TypeToken<ArrayList<Player>>() {
                            }.getType();
                            ArrayList<Player> mPlayersArray = new ArrayList<Player>();
                            mPlayersArray.add(mPlayer);
                            Log.d("Saved Player : ", mPlayersArray.get(0).getName() + "");
                            String playerjson = new Gson().toJson(mPlayersArray);
                            Intent intent = new Intent(ActivityCharacterView.this, ActivityCharacterView.class);
                            intent.putExtra("player", playerjson);
                            Log.d("Game:", "Character View Button Clicked!");
                            startActivity(intent);
                        }
                    }

                });
            }
        });

        }

    }


