package com.sheep83.games.turnmonster;

/**
 * Created by user on 03/09/2016.
 */
public interface Fightable {

    void decHealth(int value);

    void incHealth(int value);

    void meleeAttack(Fightable target, int value);

    void specialAttack(Fightable target, int value);

    void incTurn(int value);

    int getHealth();

    String getName();

    void setHealth(int value);

    boolean deadCheck();

    int getBaseDamage();



}
