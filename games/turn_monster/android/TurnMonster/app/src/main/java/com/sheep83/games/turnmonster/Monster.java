package com.sheep83.games.turnmonster;

import java.util.ArrayList;

/**
 * Created by user on 03/09/2016.
 */
public class Monster implements Fightable{

    private String mName, mType;
    private int mLevel, mMinLevel, mHealth, mMaxHealth, mTurnCount, mBaseDamage, mMaxDamage;
    private double mHealthMod, mDmgMod;
    private boolean mActive;

    public Monster(String type, int health, int minlevel, double healthMod, double dmgMod) {

//        mName = name;
        mType = type;
        mHealth = health;
        mMinLevel = minlevel;
        mTurnCount = 0;
        mBaseDamage = 25;
        mHealthMod = healthMod;
        mDmgMod = dmgMod;
    }

    public void setName(String name){
        this.mName = name;
    }

    public String getName(){
        return this.mName;
    }

    public void setHealth(int health){
        this.mHealth = health;
    }

    public int getHealth(){
        return this.mHealth;
    }

    public double getHealthMod(){
        return this.mHealthMod;
    }

    public double getDmgMod(){
        return this.mDmgMod;
    }

    public int getMaxHealth(){
        return this.mMaxHealth;
    }

    public int getMinLevel(){
        return this.mMinLevel;
    }

    public void setLevel(int level){
        this.mLevel = level;
    }

    public int getLevel(){
        return this.mLevel;
    }

    public String getType(){
        return this.mType;
    }

    public void decHealth(int value){
        this.mHealth -= value;
    }

    public void incHealth(int value){
        this.mHealth += value;
    }

    public void meleeAttack(Fightable target, int value){
        target.decHealth(value);
    }

    public void specialAttack(Fightable target, int value){
        target.decHealth(value);
    }

    public void incTurn(int value){
        this.mTurnCount += value;
    }

    public int getTurnCount(){
        return this.mTurnCount;
    }

    public void setActive(Boolean state) {
        this.mActive = state;
    }

    public Boolean getActive() {
        return this.mActive;
    }

    public void calcStats(){
        this.mMaxHealth = (int)Math.ceil((this.getHealth() + this.getLevel()) * this.getHealthMod());
        this.mMaxDamage = (int)Math.ceil((this.getBaseDamage() + this.getLevel()) * this.getDmgMod());
        this.setHealth(this.getMaxHealth());
        this.setBaseDamage(this.getMaxDamage());
        }

    @Override
    public int getBaseDamage() {
        return this.mBaseDamage;
    }

    public int getMaxDamage() {
        return this.mMaxDamage;
    }

    public void setBaseDamage(int value) {
        this.mBaseDamage = value ;
    }

    public boolean deadCheck(){
        if (this.getHealth() <= 0){
            return true;
        }else
        {
            return false;
        }
    }

}
