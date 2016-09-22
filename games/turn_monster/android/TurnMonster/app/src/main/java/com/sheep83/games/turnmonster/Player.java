package com.sheep83.games.turnmonster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by user on 02/09/2016.
 */
public class Player implements Fightable{

    private String mName;
    private int mXpGained, mDealtDamage, mSkillPoints, mHealth, mMaxHealth, mXp, mMana, mMaxMana,  mLevel, mTurnCount, mBaseDamage, mIntMod, mManaMod, mDmgMod, mHealthMod, mIntellect, mMaxInt, mVitality;
    private ArrayList<Loot> mInventory, mEquipped;
    private ArrayList<Skill> mSkillTree, mSkillsKnown;
    private boolean mActive, mLevelUp;
    private Loot loot;

    public Player (String name) {
        mName = name;
        mInventory = new ArrayList<Loot>();
        mEquipped = new ArrayList<Loot>();
        mSkillTree = new ArrayList<Skill>();
        mSkillsKnown = new ArrayList<Skill>();
        mHealth = 100;
        mIntellect = 1;
        mVitality = 1;
        mMana = 100;
        mLevel = 1;
        mXp = 0;
        mXpGained = 0;
        mSkillPoints = 0;
//        mTurnCount = 0;
        mDmgMod = 1;
        mMaxHealth = mHealth;
        mMaxMana = mMana;
        mMaxInt = mIntellect;
        mActive = true;
        mBaseDamage = 40;
        mDealtDamage = 0;
        mLevelUp = false;
    }

    public void attack(Fightable target, Skill skill, Dice dice){
        if (this.manaCheck(skill)) {
            Log.d("Player attacking with ", skill.getName());
            dice.setSides(skill.getDamage());
            int damage = dice.roll();
            if (damage < skill.getMinDamage()){
                damage = skill.getMinDamage();
            }
            double mod = (this.getDmgMod() / 10) + 1;
            double exactDamage = (damage * mod);
            damage = (int)Math.ceil(exactDamage);
            target.decHealth(damage);
            this.setDealtDamage(damage);
            int manaRegen = this.getIntellect() + 10;
            int manaCost = skill.getManaCost() - this.getIntellect();
            this.decMana(manaCost);
            this.incMana(manaRegen);
            if(this.getMana() > this.getMaxMana()){
                this.setMana(this.getMaxMana());
            }

        }

    }

    public void equipItem(Loot item) {
        if (this.getLevel() >= item.getLevel()) {
            this.mEquipped.add(item);
            this.resetStats();
        }
    }

    public void setXpGained(int value){
        this.mXpGained = value;
    }

    public void setVitality(int value){
        this.mVitality = value;
    }

    public int getXpGained(){
        return this.mXpGained;
    }

    public int getVitality(){
        return this.mVitality;
    }

    public void setMaxMana(int value){
        this.mMaxMana = value;
    }

    public int getMaxMana(){
        return this.mMaxMana;
    }

    public void setLevelUp(boolean value){
        this.mLevelUp = value;
    }

    public boolean getLevelUp(){
        return this.mLevelUp;
    }

    public void setManaMod(int value){
        this.mManaMod = value;
    }

    public int getManaMod(){
        return this.mManaMod;
    }

    public int getDmgMod(){
        return this.mDmgMod;
    }

    public void setDmgMod(int value){
        this.mDmgMod = value;
    }

    public void setHealthMod(int value){
        this.mHealthMod = value;
    }

    public void setIntMod(int value){
        this.mIntMod = value;
    }

    public void addToInventory(Loot item){
        this.mInventory.add(item);
    }

    public void addSkill(Skill skill){
        this.mSkillsKnown.add(skill);
    }

    public void addToSkillTree(Skill skill){
        this.mSkillTree.add(skill);
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

    public void setXp(int value){
        this.mXp = value;
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

    public ArrayList<Skill> getSkillTree(){
        return this.mSkillTree;
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

    public void incSkillPoints(){
        this.mSkillPoints += 1;
    }

    public void decSkillPoints(int value){
        this.mSkillPoints -= value;
    }

    public int getSkillPoints(){
        return this.mSkillPoints;
    }

    public boolean deadCheck(){
        if (this.getHealth() <= 0){
            return true;
        }else
        {
            return false;
        }
    }

    public Loot rollForLoot(Dice dice, Dice nulldice) {
        ArrayList<String> mLootEnchants = new ArrayList<>();
        mLootEnchants.add("Destruction");
        mLootEnchants.add("Despair");
        mLootEnchants.add("Savagery");
        mLootEnchants.add("Domination");
        mLootEnchants.add("Terror");
        mLootEnchants.add("Enlightenment");

        int level = this.getLevel();
        int levelRoll = dice.roll();
        if (levelRoll < 30) {
            level = this.getLevel() - 1;
        } else if (levelRoll >= 30 && levelRoll < 75) {
            level = this.getLevel();
        } else {
            level = this.getLevel() + 1;
        }
        Loot loot = new Loot(this.rollForSlot(dice), this.rollForRarity(dice), level);
        if (loot.getEnumType() == Loot.Slot.WEAPON) {
            ArrayList<String> mWeaponTypes = new ArrayList<String>();
            mWeaponTypes.add("Sword");
            mWeaponTypes.add("Staff");
            mWeaponTypes.add("Dagger");
            mWeaponTypes.add("Axe");
            mWeaponTypes.add("Hammer");
            nulldice.setSides(mWeaponTypes.size());
            loot.setType(mWeaponTypes.get(nulldice.roll()));
            nulldice.setSides(mLootEnchants.size());
            loot.setEnchant(mLootEnchants.get(nulldice.roll()));
            loot.setBaseDamage(40 + (loot.getLevel() * 3));
        } else {
            ArrayList<String> mArmourTypes = new ArrayList<String>();
            mArmourTypes.add("Robes");
            mArmourTypes.add("Epaulets");
            mArmourTypes.add("Boots");
            mArmourTypes.add("Gloves");
            mArmourTypes.add("Hat");
            nulldice.setSides(mArmourTypes.size());
            loot.setType(mArmourTypes.get(nulldice.roll()));
            nulldice.setSides(mLootEnchants.size());
            loot.setEnchant(mLootEnchants.get(nulldice.roll()));
        }
        if (loot.getEnumRarity() == Loot.Rarity.COMMON) {
            nulldice.setSides(this.getLevel());
            loot.setVitality(nulldice.roll());
            if (loot.getVitality() == 0) {
                loot.setVitality((int) Math.ceil(this.getLevel() / 2));
            }
        } else if (loot.getEnumRarity() == Loot.Rarity.RARE) {
            nulldice.setSides(this.getLevel() + 1);
            loot.setVitality(nulldice.roll());
            if (loot.getVitality() == 0) {
                loot.setVitality((int) Math.ceil(this.getLevel() / 2));
            }
            loot.setIntellect(nulldice.roll());
            if (loot.getIntellect() == 0) {
                loot.setIntellect((int) Math.ceil(this.getLevel() / 2));
            }
        } else if (loot.getEnumRarity() == Loot.Rarity.EPIC) {
            nulldice.setSides(this.getLevel() + 2);
            loot.setVitality(nulldice.roll());
            if (loot.getVitality() == 0) {
                loot.setVitality((int) Math.ceil(this.getLevel()));
            }
            loot.setIntellect(nulldice.roll());
            if (loot.getIntellect() == 0) {
                loot.setIntellect((int) Math.ceil(this.getLevel()));
            }

        }
        return loot;
    }


    public Loot.Rarity rollForRarity(Dice dice){
        int roll = dice.roll();

        if (roll < 60){
            return Loot.Rarity.COMMON;
        }else if (roll >= 60 && roll < 95){
            return Loot.Rarity.RARE;
        }else if (roll >= 95){
            return Loot.Rarity.EPIC;
        }
        return Loot.Rarity.COMMON;
    }

    public int rollForMonsterLevel(Dice dice){
        int roll = dice.roll();
        if (roll < 30){
            return this.getLevel() - 1;
        }else if (roll >= 30 && roll < 80){
            return this.getLevel();
        }else
        {
            return this.getLevel() + 1;
        }
    }

    public Loot.Slot rollForSlot(Dice dice){
        int roll = dice.roll();
        if (roll < 50){
            return Loot.Slot.ARMOUR;
        }else{
            return Loot.Slot.WEAPON;
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
        this.mIntellect = 1;
        this.mVitality = 1;
        this.calcStats();
    }

    public int getBaseDamage() {
        return this.mBaseDamage;
    }

    public void setBaseDamage(int value) {
        this.mBaseDamage = value;
    }

    public int getDealtDamage() {
        return this.mDealtDamage;
    }

    public void setDealtDamage(int value) {
        this.mDealtDamage = value;
    }

    public int getIntellect() {
        return this.mIntellect;
    }

    public void setIntellect(int value) {
        this.mIntellect = value;
    }

    public void incIntellect(int value) {
        this.mIntellect += value;
    }


    public Skill getSkill(int value) {
        return this.mSkillsKnown.get(value);
    }

    public void levelUp() {
            this.incLevel();
    }

    public boolean levelUpCheck(){
        if (this.getXp() >= (this.getLevel() * 120)){
            return true;
        }else
        {
            return false;
        }
    }

    public void calcStats(){
        int healthMod = 1;
        int manaMod = 1;
        int intMod = 1;

        if(this.getEquipped() > 0){
            for (Loot loot : this.getEquippedArray()){
                if(loot.getEnumType() == Loot.Slot.WEAPON){
                    this.setBaseDamage(loot.getBaseDamage());
                }
                healthMod += loot.getVitality();
                manaMod += loot.getIntellect();
                intMod += loot.getIntellect();
            }
            this.setManaMod(manaMod + 1);
            this.setHealthMod(healthMod);
            this.setIntMod(intMod);
            this.setVitality(healthMod);
            this.setDmgMod((int) Math.ceil(intMod * 0.8));
            this.setHealth(100 + ((healthMod * 5) + (this.getLevel() * 2)));
            this.setIntellect(intMod);
            this.setMana(100 + (this.getIntellect() * this.getLevel()) + this.getLevel());
            this.mMaxHealth = this.getHealth();
            this.mMaxInt = this.getIntellect();
            this.mMaxMana = this.getMana();
        }
    }

    public boolean checkInventory(Loot item) {
        for (Loot invItem : this.mEquipped) {
            if (item.getEnumType() == invItem.getEnumType()) {
                return false;
            }
        }
        return true;
    }

    public void removeFromInventory(int index){
        this.mInventory.remove(index);
    }

    public void removeFromEquipped(Loot item){
        this.mEquipped.remove(item);
    }

    public Loot findItemBySlot(Loot.Slot slot){
        for(Loot item : this.getEquippedArray()){
            if(item.getEnumType() == slot){
                return item;
            }
        }
        return null;
    }

    public boolean manaCheck(Skill skill){
        if (this.getMana() >= skill.getManaCost()){
            return true;
        }
        else
        {
            return false;
        }
    }


}
