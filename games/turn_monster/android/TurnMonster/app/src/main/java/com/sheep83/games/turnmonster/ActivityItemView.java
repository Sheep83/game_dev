package com.sheep83.games.turnmonster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView mRarity, mInvNameHeader, mInvLevelHeader, mInvIntHeader, mInvVitHeader, mType, mNameHeader, mItemName, mInvName, mLevelheader, mItemLevel, mInvLevel,  mDamageHeader, mDamage, mIntHeader, mInt, mInvInt,  mVitHeader, mVit, mInvVit;
    Button mViewInventory, mHome, mEquipButton, mYes, mNo, mDeleteButton, mCharacterButton;

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
        mRarity = (TextView) findViewById(R.id.rarity);
        mType = (TextView) findViewById(R.id.type);
        mItemName = (TextView) findViewById(R.id.item_name);
        mLevelheader = (TextView) findViewById(R.id.level_header);
        mItemLevel = (TextView) findViewById(R.id.item_level);
        mIntHeader = (TextView) findViewById(R.id.int_header);
        mInt = (TextView) findViewById(R.id.item_intellect);
        mVitHeader = (TextView) findViewById(R.id.vit_header);
        mVit = (TextView) findViewById(R.id.item_vitality);
        mDamageHeader = (TextView) findViewById(R.id.damage_header);
        mDamage = (TextView) findViewById(R.id.damage);
        mItemName.setText(String.valueOf(mItem.getName()));
        mItemLevel.setText(String.valueOf(mItem.getLevel()));
        mInt.setText(String.valueOf(mItem.getIntellect()));
        mVit.setText(String.valueOf(mItem.getVitality()));
        mType.setText(String.valueOf(mItem.getType()));
        mDamage.setText(String.valueOf(mItem.getBaseDamage()));
        mRarity.setTextColor(Color.parseColor(mItem.getRarityColour()));
        mRarity.setText(String.valueOf(mItem.getRarity()));
        mViewInventory = (Button) findViewById(R.id.inventory_button);
        mEquipButton = (Button) findViewById(R.id.equip_item);
        mDeleteButton = (Button) findViewById(R.id.delete_button);
        mCharacterButton = (Button) findViewById(R.id.character_button);


        mCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ArrayList<Player> mPlayersArray = new ArrayList<Player>();
                    mPlayersArray.add(mPlayer);
                    Log.d("Saved Player : ", mPlayersArray.get(0).getName() + "");
                    String playerjson = new Gson().toJson(mPlayersArray);
                    Intent intent = new Intent(ActivityItemView.this, ActivityCharacterView.class);
                    intent.putExtra("player", playerjson);
                    Log.d("Game:", "Character View Button Clicked!");
                    startActivity(intent);
                }


        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(ActivityItemView.this, ActivityCharacterView.class);
                Log.d("Game:", "Delete button pressed");
                mPlayer.removeFromInventory(mIndex);
                ArrayList<Player> mPlayers = new ArrayList<>();
                mPlayers.add(mPlayer);
                //save player here
                String newplayerarrayjson = new Gson().toJson(mPlayers);
                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                intent.putExtra("player", newplayerarrayjson);
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
                if (mPlayer.getLevel() >= mItem.getLevel()) {
                    if (mPlayer.checkInventory(mItem)) {
                        mPlayer.equipItem(mItem);
                        mPlayer.removeFromInventory(mIndex);
                        Log.d("Game:", "" + mItem + "equipped");
                        ArrayList<Player> mPlayers = new ArrayList<>();
                        mPlayers.add(mPlayer);
                        //save player here
                        String newplayerarrayjson = new Gson().toJson(mPlayers);
                        intent.putExtra("player", newplayerarrayjson);
                        SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), mPlayer.getName() + " already has an item of that type equipped", Toast.LENGTH_SHORT).show();
                        setContentView(R.layout.activity_replace_equip);
                        Loot equippedItem = mPlayer.findItemBySlot(mItem.getEnumType());
                        mItemName = (TextView) findViewById(R.id.equip_name);
                        mLevelheader = (TextView) findViewById(R.id.level_header);
                        mItemLevel = (TextView) findViewById(R.id.equip_level);
                        mIntHeader = (TextView) findViewById(R.id.int_header);
                        mInt = (TextView) findViewById(R.id.equip_int);
                        mVitHeader = (TextView) findViewById(R.id.vit_header);
                        mVit = (TextView) findViewById(R.id.equip_vit);
                        mInvNameHeader = (TextView) findViewById(R.id.inv_name);
                        mInvName = (TextView) findViewById(R.id.inv_name);
                        mInvLevelHeader = (TextView) findViewById(R.id.inv_level_header);
                        mInvLevel = (TextView) findViewById(R.id.inv_level);
                        mInvIntHeader = (TextView) findViewById(R.id.inv_int_header);
                        mInvInt = (TextView) findViewById(R.id.inv_int);
                        mInvVitHeader = (TextView) findViewById(R.id.inv_int_header);
                        mInvVit = (TextView) findViewById(R.id.inv_vit);
                        mYes = (Button) findViewById(R.id.yes_button);
                        mNo = (Button) findViewById(R.id.no_button);
                        mItemName.setText(String.valueOf(equippedItem.getName()));
                        mItemLevel.setText(String.valueOf(equippedItem.getLevel()));
                        mInt.setText(String.valueOf(equippedItem.getIntellect()));
                        mVit.setText(String.valueOf(equippedItem.getVitality()));
                        mInvName.setText(String.valueOf(mItem.getName()));
                        mInvLevel.setText(String.valueOf(mItem.getLevel()));
                        mInvInt.setText(String.valueOf(mItem.getIntellect()));
                        mInvVit.setText(String.valueOf(mItem.getVitality()));

                        mYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Context context = view.getContext();
                                Intent intent = new Intent(ActivityItemView.this, ActivityCharacterView.class);
                                Log.d("Game:", "Yes button pressed");
                                Loot equippedItem = mPlayer.findItemBySlot(mItem.getEnumType());
                                mPlayer.removeFromEquipped(equippedItem);
                                mPlayer.equipItem(mItem);
                                mPlayer.removeFromInventory(mIndex);
                                ArrayList<Player> mPlayers = new ArrayList<>();
                                mPlayers.add(mPlayer);
                                //save player here
                                String newplayerarrayjson = new Gson().toJson(mPlayers);
                                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                                intent.putExtra("player", newplayerarrayjson);
                                startActivity(intent);
                            }
                        });

                        mNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Context context = view.getContext();
                                Intent intent = new Intent(ActivityItemView.this, ActivityCharacterView.class);
                                Log.d("Game:", "No button pressed");
                                ArrayList<Player> mPlayers = new ArrayList<>();
                                mPlayers.add(mPlayer);
                                //save player here
                                String newplayerarrayjson = new Gson().toJson(mPlayers);
                                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                                intent.putExtra("player", newplayerarrayjson);
                                startActivity(intent);
                            }
                        });
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(), mPlayer.getName() + "does not meet the level requirement for that item", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
