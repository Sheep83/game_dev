package com.sheep83.games.turnmonster;

import java.util.ArrayList;

/**
 * Created by user on 02/09/2016.
 */
public class Loot {

    private String mName, mEnchant, mType;
    private ArrayList<String> mNameOptions, mTypeOptions,  mEnchantOptions;
    private int mLevel, mIntellect, mStrength, mVitality;

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

    public int getIntellect() {
        return this.mIntellect;
    }

    public void setIntellect(int value) {
        this.mIntellect = value;
    }

    public int getStrength() {
        return this.mStrength;
    }

    public void setStrength(int value) {
        this.mStrength = value;
    }

    public int getVitality() {
        return this.mVitality;
    }

    public String getName() {
        return String.valueOf(this.getType()) + String.valueOf(this.getEnchant());
    }

    public void setVitality(int value) {
        this.mVitality = value;
    }
}
