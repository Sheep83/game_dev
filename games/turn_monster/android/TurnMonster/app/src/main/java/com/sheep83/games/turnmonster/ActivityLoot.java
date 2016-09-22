package com.sheep83.games.turnmonster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
 * Created by user on 09/09/2016.
 */
public class ActivityLoot extends AppCompatActivity {

    private Button mTake, mDiscard, continueButton;
    private TextView mXpGained, mPlayerHeader, mPlayerXpHeader, mPlayerName, mPlayerXp, mLootHeader, mLootText, mRarity, mType, mNameHeader, mItemName, mInvName, mLevelheader, mItemLevel, mInvLevel,  mDamageHeader, mDamage, mIntHeader, mInt, mInvInt,  mVitHeader, mVit, mInvVit;
    private Player mPlayer;
    private Loot mLoot;
    private ArrayList<Skill> learnableSkills = new ArrayList<>();


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
        mTake = (Button) findViewById(R.id.take);
        mDiscard = (Button) findViewById(R.id.discard);
        continueButton = (Button) findViewById(R.id.restart);
//        if(mLoot != null)
//        {
//            mLootText.setText(mLoot.getType() + " of " + mLoot.getEnchant());
//        }
        mLootHeader = (TextView) findViewById(R.id.loot_header);
//        mXpGained = (TextView) findViewById(R.id.xp_gained);
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
        if(extras.getString("looted") != null) {
            continueButton.setVisibility(View.GONE);
            mLootHeader.setText("You have looted an item!");
            mItemName.setText(String.valueOf(mLoot.getName()));
            mItemLevel.setText(String.valueOf(mLoot.getLevel()));
            mInt.setText(String.valueOf(mLoot.getIntellect()));
            mVit.setText(String.valueOf(mLoot.getVitality()));
            mType.setText(String.valueOf(mLoot.getType()));
            mDamage.setText(String.valueOf(mLoot.getBaseDamage()));
            mRarity.setTextColor(Color.parseColor(mLoot.getRarityColour()));
            mRarity.setText(String.valueOf(mLoot.getRarity()));
//            mXpGained.setText(String.valueOf(mPlayer.getXpGained()));

        }else{
            mLootHeader.setText("No item found :-(");
//            mXpGained.setVisibility(View.GONE);
            mItemName.setVisibility(View.GONE);
            mItemLevel.setVisibility(View.GONE);
            mInt.setVisibility(View.GONE);
            mVit.setVisibility(View.GONE);
            mType.setVisibility(View.GONE);
            mDamage.setVisibility(View.GONE);
            mRarity.setVisibility(View.GONE);
            mTake.setVisibility(View.GONE);
            mDiscard.setVisibility(View.GONE);

            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    if (mPlayer.getLevelUp()) {
                        setContentView(R.layout.activity_level_up);
                        mPlayer.setLevelUp(false);
                        TextView name = (TextView) findViewById(R.id.player_name);
                        TextView skillpoints = (TextView) findViewById(R.id.skill_points);
                        name.setText(String.valueOf(mPlayer.getName()));
                        skillpoints.setText(String.valueOf(mPlayer.getSkillPoints()));
                        ListView skillslist = (ListView) findViewById(R.id.skills_list);
                        continueButton = (Button) findViewById(R.id.continue_button);
                        for (Skill skill : mPlayer.getSkillTree()) {
                            if (skill.getSkillCost() <= mPlayer.getSkillPoints() && !skill.getKnown()) {
                                learnableSkills.add(skill);
                                if (learnableSkills.size() > 0) {
                                    continueButton.setVisibility(View.GONE);
                                }
                            }
                        }
                        ArrayAdapter<Skill> skillsAdapter = new SkillArrayListAdapter(context, android.R.layout.simple_list_item_1, learnableSkills);
                        skillslist.setAdapter(skillsAdapter);
                        skillslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                                Skill selectedSkill = learnableSkills.get(position);
                                mPlayer.addSkill(selectedSkill);
                                selectedSkill.setKnown(true);
                                mPlayer.decSkillPoints(selectedSkill.getSkillCost());
                                for (Skill skill : mPlayer.getSkillTree()) {
                                    if (skill.getName() == selectedSkill.getName()) {
                                        skill.setKnown(true);
                                    }
                                }
                                Toast.makeText(getApplicationContext(), mPlayer.getName() + " selects " + selectedSkill.getName(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ActivityLoot.this, ActivityCharacterView.class);
                                ArrayList<Player> mPlayers = new ArrayList<>();
                                mPlayer.resetStats();
                                mPlayers.add(mPlayer);
                                String newplayerjson = new Gson().toJson(mPlayers);
                                SavedTaskPreferences.setStoredPlayer(ActivityLoot.this, newplayerjson);
                                startActivity(intent);
                                intent.putExtra("player", newplayerjson);
                                startActivity(intent);
                            }
                        });

                        continueButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Context context = view.getContext();
                                Intent intent = new Intent(ActivityLoot.this, Game.class);
                                mPlayer.resetStats();
                                //reset health and mana here
                                ArrayList<Player> mPlayers = new ArrayList<>();
                                mPlayers.add(mPlayer);
                                mPlayer.setLevelUp(false);
                                String newplayerarrayjson = new Gson().toJson(mPlayers);
                                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                                intent.putExtra("player", newplayerarrayjson);
                                startActivity(intent);
                            }
                        });

                    } else {
                        Intent intent = new Intent(ActivityLoot.this, Game.class);
                        //reset health and mana here
                        mPlayer.resetStats();
                        ArrayList<Player> mPlayers = new ArrayList<>();
                        mPlayers.add(mPlayer);
                        String newplayerjson = new Gson().toJson(mPlayers);
                        SavedTaskPreferences.setStoredPlayer(context, newplayerjson);
                        startActivity(intent);
                    }

                }
            });

        }

        mTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                mPlayer.addToInventory(mLoot);
                if (mPlayer.getLevelUp()) {
                    setContentView(R.layout.activity_level_up);
                    mPlayer.setLevelUp(false);
                    TextView name = (TextView) findViewById(R.id.player_name);
                    TextView skillpoints = (TextView) findViewById(R.id.skill_points);
                    name.setText(String.valueOf(mPlayer.getName()));
                    skillpoints.setText(String.valueOf(mPlayer.getSkillPoints()));
                    ListView skillslist = (ListView) findViewById(R.id.skills_list);
                    continueButton = (Button) findViewById(R.id.continue_button);
                    for (Skill skill : mPlayer.getSkillTree()) {
                        if (skill.getSkillCost() <= mPlayer.getSkillPoints() && !skill.getKnown()) {
                            learnableSkills.add(skill);
                            if (learnableSkills.size() > 0) {
                                continueButton.setVisibility(View.GONE);
                            }
                        }
                    }
                    ArrayAdapter<Skill> skillsAdapter = new SkillArrayListAdapter(context, android.R.layout.simple_list_item_1, learnableSkills);
                    skillslist.setAdapter(skillsAdapter);
                    skillslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                            Skill selectedSkill = learnableSkills.get(position);
                            mPlayer.addSkill(selectedSkill);
                            selectedSkill.setKnown(true);
                            mPlayer.decSkillPoints(selectedSkill.getSkillCost());
                            for (Skill skill : mPlayer.getSkillTree()) {
                                if (skill.getName() == selectedSkill.getName()) {
                                    skill.setKnown(true);
                                }
                            }
                            Toast.makeText(getApplicationContext(), mPlayer.getName() + " selects " + selectedSkill.getName(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ActivityLoot.this, ActivityCharacterView.class);
                            ArrayList<Player> mPlayers = new ArrayList<>();
                            mPlayer.resetStats();
                            mPlayers.add(mPlayer);
                            String newplayerjson = new Gson().toJson(mPlayers);
                            SavedTaskPreferences.setStoredPlayer(ActivityLoot.this, newplayerjson);
                            startActivity(intent);
                            intent.putExtra("player", newplayerjson);
                            startActivity(intent);
                        }
                    });

                    continueButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Context context = view.getContext();
                            Intent intent = new Intent(ActivityLoot.this, Game.class);
                            mPlayer.resetStats();
                            mPlayer.setLevelUp(false);
                            //reset health and mana here
                            ArrayList<Player> mPlayers = new ArrayList<>();
                            mPlayers.add(mPlayer);
                            String newplayerarrayjson = new Gson().toJson(mPlayers);
                            SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                            intent.putExtra("player", newplayerarrayjson);
                            startActivity(intent);
                        }
                    });

                } else {
                    Intent intent = new Intent(ActivityLoot.this, Game.class);
                    Log.d("Game:", "Item Looted!");
                    //reset health and mana here
                    mPlayer.resetStats();
                    ArrayList<Player> mPlayers = new ArrayList<>();
                    mPlayers.add(mPlayer);
                    String newplayerjson = new Gson().toJson(mPlayers);
                    SavedTaskPreferences.setStoredPlayer(context, newplayerjson);
                    startActivity(intent);
                }
            }
        });

        mDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                if(mPlayer.getLevelUp()){
                    setContentView(R.layout.activity_level_up);
                    mLevelheader = (TextView) findViewById(R.id.level_header);
                    mPlayerName = (TextView) findViewById(R.id.player_name);
                    TextView skillpoints = (TextView) findViewById(R.id.skill_points);
                    mPlayerName.setText(String.valueOf(mPlayer.getName()));
                    skillpoints.setText(String.valueOf(mPlayer.getSkillPoints()));
                    ListView skillslist = (ListView) findViewById(R.id.skills_list);
                    continueButton = (Button) findViewById(R.id.continue_button);
                    for (Skill skill : mPlayer.getSkillTree()) {
                        if (skill.getSkillCost() <= mPlayer.getSkillPoints() && !skill.getKnown()) {
                            learnableSkills.add(skill);
//                            if (learnableSkills.size() > 0) {
//                                continueButton.setVisibility(View.GONE);
//                            }
//                            if(learnableSkills.size() == 0){
//                                skillslist.setVisibility(View.GONE);
//                        }
                    }
                    ArrayAdapter<Skill> skillsAdapter = new SkillArrayListAdapter(context, android.R.layout.simple_list_item_1, learnableSkills);
                    skillslist.setAdapter(skillsAdapter);

                    skillslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                            Skill selectedSkill = learnableSkills.get(position);
                            mPlayer.addSkill(selectedSkill);
                            selectedSkill.setKnown(true);
                            mPlayer.decSkillPoints(selectedSkill.getSkillCost());
                            for (Skill skill : mPlayer.getSkillTree()) {
                                if (skill.getName() == selectedSkill.getName()) {
                                    skill.setKnown(true);
                                }
                            }
                            Toast.makeText(getApplicationContext(), mPlayer.getName() + " selects " + selectedSkill.getName(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ActivityLoot.this, ActivityCharacterView.class);
                            ArrayList<Player> mPlayers = new ArrayList<>();
                            mPlayer.resetStats();
                            mPlayer.setLevelUp(false);
                            mPlayers.add(mPlayer);
                            String newplayerjson = new Gson().toJson(mPlayers);
                            SavedTaskPreferences.setStoredPlayer(ActivityLoot.this, newplayerjson);
                            startActivity(intent);
                            intent.putExtra("player", newplayerjson);
                            startActivity(intent);
                        }
                    });

                        continueButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Context context = view.getContext();
                                Intent intent = new Intent(ActivityLoot.this, Game.class);
                                mPlayer.resetStats();
                                mPlayer.setLevelUp(false);
                                //reset health and mana here
                                ArrayList<Player> mPlayers = new ArrayList<>();
                                mPlayers.add(mPlayer);
                                String newplayerarrayjson = new Gson().toJson(mPlayers);
                                SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                                intent.putExtra("player", newplayerarrayjson);
                                startActivity(intent);
                            }
                        });
                }
                }else {
                    Intent intent = new Intent(ActivityLoot.this, Game.class);
                    Log.d("Game:", "Item Discarded!");
                    //reset health and mana here
                    mPlayer.resetStats();
                    ArrayList<Player> mPlayers = new ArrayList<>();
                    mPlayers.add(mPlayer);
                    //save player here
                    String newplayerarrayjson = new Gson().toJson(mPlayers);
                    SavedTaskPreferences.setStoredPlayer(context, newplayerarrayjson);
                    startActivity(intent);
                }
            }
        });


    }


}
