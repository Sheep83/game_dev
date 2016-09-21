package com.sheep83.games.turnmonster;

import java.util.ArrayList;

/**
 * Created by user on 04/09/2016.
 */
public class Skill {

    private String mName, mDescription;
    private int mDamage, mMinDamage, mManaCost, mSkillCost;
    private boolean mKnown;

    public Skill (String name, String description, int damage, int manacost, int skillcost) {
        mName = name;
        mDescription = description;
        mDamage = damage;
        mManaCost = manacost;
        mSkillCost = skillcost;
    }

    public String getName() {
        return this.mName;
    }

    public void setKnown(boolean value) {
        this.mKnown = value;
    }

    public boolean getKnown() {
        return this.mKnown;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getDamage() {
        return this.mDamage;
    }

    public int getMinDamage() {
        return this.mMinDamage;
    }

    public void setMinDamage(int value) {
        this.mMinDamage = value;
    }

    public int getManaCost() {
        return this.mManaCost;
    }

    public int getSkillCost() {
        return this.mSkillCost;
    }



}



