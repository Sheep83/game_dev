package com.sheep83.games.turnmonster;

import java.util.ArrayList;

/**
 * Created by user on 02/09/2016.
 */
public class Loot {

    public enum Slot {
        WEAPON,
        ARMOUR
    };

    public enum Rarity {
        COMMON,
        RARE,
        EPIC
    };

    private String mName, mEnchant, mType;
    private ArrayList<String> mNameOptions, mTypeOptions,  mEnchantOptions;
    private int mLevel, mIntellect, mStrength, mVitality, mBaseDamage;
    private Slot enumType;
    private Rarity mEnumRarity;

    public Loot (Slot slot, Rarity rarity, Integer level) {
//        mType = type;
//        mEnchant = enchant;
//        mIntellect = 0;
//        mVitality = 0;
        enumType = slot;
        mEnumRarity = rarity;
        mLevel = level;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public Slot getEnumType() {
        return this.enumType;
    }

    public void setEnumType(Slot type) {
        this.enumType = type;
    }

    public Rarity getEnumRarity() {
        return this.mEnumRarity;
    }

    public void setEnumRarity(Rarity rarity) {
        this.mEnumRarity = rarity;
    }

    public int getBaseDamage() {
        return this.mBaseDamage;
    }

    public void setBaseDamage(int value) {
        this.mBaseDamage = value;
    }

    public String getEnchant() {
        return this.mEnchant;
    }

    public void setEnchant(String enchant) {
        this.mEnchant = enchant;
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

    public String getRarity(){
        if(this.getEnumRarity() == Rarity.COMMON){
            return "Common";
        }else if (this.getEnumRarity() == Rarity.RARE){
            return "Rare";
        }else if (this.getEnumRarity() == Rarity.EPIC){
            return "Epic";
        }
        return null;
    }

    public String getRarityColour(){
        if(this.getEnumRarity() == Rarity.COMMON){
            return "#00cc00";
        }else if (this.getEnumRarity() == Rarity.RARE){
            return "#0066ff";
        }else if (this.getEnumRarity() == Rarity.EPIC){
            return "#cc00ff";
        }
        return null;
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
        return String.valueOf(this.getType()) + " of " + String.valueOf(this.getEnchant());
    }

    public void setVitality(int value) {
        this.mVitality = value;
    }
}

