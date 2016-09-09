package com.sheep83.games.turnmonster;

import java.util.ArrayList;

/**
 * Created by user on 03/09/2016.
 */
public class Monster implements Fightable{

    private String mName, mType;
    private int mLevel, mHealth, mTurnCount, mBaseDamage;
    private boolean mActive;

    public Monster(String name, String type, int health, int level) {

        mName = name;
        mType = type;
        mHealth = health;
        mLevel = level;
        mTurnCount = 0;
        mBaseDamage = 25;
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

    @Override
    public int getBaseDamage() {
        return this.mBaseDamage;
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
