package com.sheep83.games.turnmonster;

import java.util.ArrayList;

/**
 * Created by user on 04/09/2016.
 */
public class Skill {

        private String mName, mDescription;
        private int mDamage, mManaCost;

        public Skill (String name, String description, int damage, int manacost) {
            mName = name;
            mDescription = description;
            mDamage = damage;
            mManaCost = manacost;
        }

        public String getName() {
            return this.mName;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public int getDamage() {
            return this.mDamage;
        }

        public int getManaCost() {
        return this.mManaCost;
    }

    }



