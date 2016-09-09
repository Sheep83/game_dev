package com.sheep83.games.turnmonster;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 03/09/2016.
 */
public class MonsterTest {

    Player player;
    Monster monster;

    @Before
    public void before() {
        player = new Player("test");
        monster = new Monster("testname", "testtype", 50, 1);
    }

    @Test
    public void getNameTest() {
        assertEquals("testname", monster.getName());
    }

    @Test
    public void getTypeTest() {
        assertEquals("testtype", monster.getType());
    }

    @Test
    public void getHealthTest() {
        assertEquals(50, monster.getHealth());
    }

    @Test
    public void decHealthTest() {
        monster.decHealth(20);
        assertEquals(30, monster.getHealth());
    }

    @Test
    public void incHealthTest() {
        monster.decHealth(20);
        monster.incHealth(10);
        assertEquals(40, monster.getHealth());
    }

    @Test public void attackTargetTest(){
        monster.meleeAttack(player, 20);
        assertEquals(80, player.getHealth());
    }

    @Test
    public void getTurnCountTest()
    {
        assertEquals(0, monster.getTurnCount());
    }

    @Test
    public void incTurnCountTest()
    {
        monster.incTurn(1);
        assertEquals(1, monster.getTurnCount());
    }

    @Test
    public void canCheckDeath()
    {
        monster.setHealth(0);
        assertEquals(true, monster.deadCheck());
    }

}