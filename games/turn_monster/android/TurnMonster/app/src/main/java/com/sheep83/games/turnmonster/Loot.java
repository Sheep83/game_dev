package com.sheep83.games.turnmonster;

import java.util.ArrayList;

/**
 * Created by user on 02/09/2016.
 */
public class Loot {

    private String mName, mEnchant, mType;
    private ArrayList<String> mNameOptions, mTypeOptions,  mEnchantOptions;
    private int mLevel, mFocus;

    public Loot (String type, String enchant, int level) {
        mType = type;
        mEnchant = enchant;
        mLevel = level;

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

    public int getFocus() {
        return this.mFocus;
    }

    public void setFocus(int value) {
        this.mFocus = value;
    }
}
