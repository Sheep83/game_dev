package com.sheep83.games.turnmonster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 02/09/2016.
 */
public class Player implements Fightable{

    private String mName;
    private int mHealth, mXp, mMana, mLevel, mTurnCount, mBaseDamage, mHitMod,mDefMod, mDmgMod, mMaxHealth, mHealthMod, mFocus;
    private ArrayList<Loot> mInventory, mEquipped;
    private ArrayList<Skill> mSkillTree, mSkillsKnown;
    private boolean mActive;

    public Player (String name) {
        mName = name;
        mInventory = new ArrayList<Loot>();
        mEquipped = new ArrayList<Loot>();
        mHealth = 100;
        mMana = 100;
        mLevel = 1;
        mXp = 0;
        mTurnCount = 0;
        mSkillTree = new ArrayList<Skill>();
        mSkillsKnown = new ArrayList<Skill>();
        mHitMod = 1;
        mDefMod = 1;
        mDmgMod = 1;
        mHealthMod = 1;
        mFocus = 1;
        mMaxHealth = mHealth;
        mActive = true;
        mBaseDamage = 40;
    }

    public void attack(Fightable target, Skill skill, Dice dice){
        if (this.manaCheck(skill)) {
            Log.d("Player attacking with ", skill.getName());
            this.setBaseDamage(skill.getDamage());
            dice.setSides(this.getBaseDamage());
            target.decHealth(dice.roll());
            this.decMana(skill.getManaCost());
        }else
        {
            return;
        }

    }

    public void equipItem(Loot item){
        this.mEquipped.add(item);
    }

    public void addToInventory(Loot item){
        this.mInventory.add(item);
    }

    public void addSkill(Skill skill){
        this.mSkillsKnown.add(skill);
    }

    public String getName() {
        return this.mName;
    }

    public void setActive(Boolean state) {
        this.mActive = state;
    }

    public Boolean getActive() {
        return this.mActive;
    }

    public int getXp(){
        return this.mXp;
    }

    public int getHealth(){
        return this.mHealth;
    }

    public int getInventory(){
        return this.mInventory.size();
    }

    public int getEquipped(){
        return this.mEquipped.size();
    }

    public ArrayList<Loot> getEquippedArray(){
        return this.mEquipped;
    }

    public ArrayList<Loot> getInventoryArray(){
        return this.mInventory;
    }

    public ArrayList<Skill> getSkillsArray(){
        return this.mSkillsKnown;
    }

    public int getLevel(){
        return this.mLevel;
    }

    public int getTurnCount(){
        return this.mTurnCount;
    }


    public int getMana(){
        return this.mMana;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void incXp(int xp){
        this.mXp += xp;
    }

    public void setHealth(int health){
        this.mHealth = health;
    }

    public void setLevel(int level){
        this.mLevel = level;
    }

    public void incTurn(int value){
        this.mTurnCount += value;
    }

    public void setMana(int mana){
        this.mMana = mana;
    }

    public void decHealth(int value){
        this.mHealth -= value;
    }

    public void incHealth(int value){
        this.mHealth += value;
    }

    public void decMana(int value){
        this.mMana -= value;
    }

    public void incMana(int value){
        this.mMana += value;
    }

    public void incLevel(){
        this.mLevel += 1;
    }

    public boolean deadCheck(){
        if (this.getHealth() <= 0){
            return true;
        }else
        {
            return false;
        }
    }

    public boolean rollForLoot(Dice dice){
        if (dice.roll() > 30){
            return true;
        }else
        {
            return false;
        }
    }

    public void meleeAttack(Fightable target, int value){
        target.decHealth(value);
    }

    //replicated in dice class. still undecided.
    public int rollDice(Dice dice){
        return dice.roll();
    }

    public void specialAttack(Fightable target, int value){
        target.decHealth(value);
    }

    public int getInventorySize(){
        return this.mInventory.size();
    }

    public void resetStats(){
        this.setHealth(100);
        this.setMana((100));
        this.mActive = true;
        this.mFocus = 1;
    }

    public int getBaseDamage() {
        return this.mBaseDamage;
    }

    public int getFocus() {
        return this.mFocus;
    }

    public void incFocus(int value) {
        this.mFocus += value;
    }

    public void setBaseDamage(int value) {
        this.mBaseDamage = value;
    }

    public Skill getSkill(int value) {
        return this.mSkillsKnown.get(value);
    }

    public void checkLevel() {
        if (this.getXp() >= (this.getLevel() * 100)){
            this.incLevel();
            return;
        }else
        {
           return;
        }
    }

    public boolean manaCheck(Skill skill){
        if (this.getMana() > skill.getManaCost()){
            return true;
        }
        else
        {
            return false;
        }
    }

    //    public void savePlayer(){
//        //method to save player object to sharedprefs here
//    }
//
//    public void loadPlayer(){
//        //method to load player object from sharedprefs here
//    }


}
