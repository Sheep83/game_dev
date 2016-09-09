package com.sheep83.games.turnmonster;

import java.util.ArrayList;

/**
 * Created by user on 02/09/2016.
 */
public class Loot {

    private String mName, mEnchant, mType;
    private ArrayList<String> mNameOptions, mTypeOptions,  mEnchantOptions;
    private int mLevel;

    public Loot (String type, String enchant, int level) {
//        mName = name;
        mType = type;
        mEnchant = enchant;
        mLevel = level;

    }

    public String getName() {
        return this.mName;
    }

    public String getType() {
        return this.mType;
    }

    public String getEnchant() {
        return this.mEnchant;
    }

    public int getLevel() {
        return this.mLevel;
    }
}
