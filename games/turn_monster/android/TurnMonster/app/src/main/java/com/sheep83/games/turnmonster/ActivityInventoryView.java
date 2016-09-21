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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by user on 10/09/2016.
 */
public class ActivityInventoryView extends AppCompatActivity {
    ArrayList<Player> mPlayers = new ArrayList<>();
    ArrayList<Loot> mInventory = new ArrayList<>();
    ArrayList<Loot> mEquipped = new ArrayList<>();
    Player mPlayer;
    Bundle extras;
    ListView mEquipListView, mInvListView;
    TextView mInventoryHeader, mEquippedHeader;
    Button mHome, mCharacterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_view);
        Intent intent = getIntent();
        extras = intent.getExtras();
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
            Intent newIntent = new Intent(ActivityInventoryView.this, ActivityNewPlayer.class);
            startActivity(newIntent);
        }
        mInventory = mPlayer.getInventoryArray();
        mHome = (Button) findViewById(R.id.home_button);
        mCharacterView = (Button) findViewById(R.id.character_button);
        mInventoryHeader = (TextView) findViewById(R.id.inventory_header);
        mInvListView = (ListView)findViewById(R.id.inventory_list_view);
        ArrayAdapter<Loot> inventoryarrayAdapter = new LootArrayListAdapter(this, android.R.layout.simple_list_item_1, mInventory);
        mInvListView.setAdapter(inventoryarrayAdapter);

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(ActivityInventoryView.this, Game.class);
                Log.d("Game:", "Home button pressed");
                ArrayList<Player> mPlayers = new ArrayList<>();
                mPlayers.add(mPlayer);
                //save player here
                String newplayerarrayjson = new Gson().toJson(mPlayers);
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
                    Intent intent = new Intent(ActivityInventoryView.this, ActivityCharacterView.class);
                    Toast.makeText(ActivityInventoryView.this, R.string.back_to_character,
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
                    Intent intent = new Intent(ActivityInventoryView.this, ActivityCharacterView.class);
                    intent.putExtra("player", playerjson);
                    Log.d("Game:", "Character View Button Clicked!");
                    startActivity(intent);
                }
            }

        });

        mInvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                Loot selectedItem = mPlayer.getInventoryArray().get(position);
                Toast.makeText(getApplicationContext(), mPlayer.getName() + " selects " + selectedItem.getType() + " " + selectedItem.getEnchant(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityInventoryView.this, ActivityItemView.class);
                String playerjson = new Gson().toJson(mPlayer);
                String itemjson = new Gson().toJson(selectedItem);
                String indexjson = new Gson().toJson(position);
                intent.putExtra("index", indexjson);
                intent.putExtra("player", playerjson);
                intent.putExtra("item", itemjson);
                startActivity(intent);
            }
        });

    }
}