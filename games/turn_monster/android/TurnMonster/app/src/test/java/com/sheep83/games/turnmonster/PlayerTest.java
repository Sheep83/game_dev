package com.sheep83.games.turnmonster;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;


/**
 * Created by user on 21/08/2016.
 */
public class PlayerTest {

    Player player;
    Monster monster;
    Dice dice;
    Loot loot;

    @Before
    public void before(){
        player = new Player("test");
        monster = new Monster("testname", "testtype", 50, 1);
        dice = new Dice();
        loot = new Loot("Sword", "Fire", 2);
    }

    @Test
    public void getNameTest()
    {
        assertEquals("test", player.getName());
    }

    @Test
    public void setNameTest()
    {
        player.setName("test2");
        assertEquals("test2", player.getName());
    }

    @Test
    public void setLevelTest()
    {
        player.setLevel(2);
        assertEquals(2, player.getLevel());
    }

    @Test
    public void getInvSizeTest()
    {
        assertEquals(0, player.getInventory());
    }

    @Test
    public void getHealthTest()
    {
        assertEquals(100, player.getHealth());
    }

    @Test
    public void getManaTest()
    {
        assertEquals(100, player.getMana());
    }

    @Test public void decHealthTest(){
        player.decHealth(20);
        assertEquals(80, player.getHealth());
    }

    @Test public void incHealthTest(){
        player.decHealth(20);
        player.incHealth(10);
        assertEquals(90, player.getHealth());
    }

    @Test public void decManaTest(){
        player.decMana(20);
        assertEquals(80, player.getMana());
    }

    @Test public void incManaTest(){
        player.decMana(20);
        player.incMana(10);
        assertEquals(90, player.getMana());
    }

    @Test public void incLevelTest(){
        player.incLevel();
        assertEquals(2, player.getLevel());
    }

    @Test public void incXpTest(){
        player.incXp(50);
        assertEquals(50, player.getXp());
    }

    @Test public void attackTargetTest(){
        player.meleeAttack(monster, 20);
        assertEquals(30, monster.getHealth());
    }

    @Test
    public void diceRollTest()
    {
        dice.setSides(100);
        assertNotNull(player.rollDice(dice));
    }
    @Test
    public void getTurnCountTest()
    {
        assertEquals(0, player.getTurnCount());
    }

    @Test
    public void incTurnCountTest()
    {
        player.incTurn(1);
        assertEquals(1, player.getTurnCount());
    }

    @Test
    public void canCheckDeath()
    {
        player.setHealth(0);
        assertEquals(true, player.deadCheck());
    }

    @Test
    public void canTakeLoot()
    {
        player.addToInventory(loot);
        assertEquals(1, player.getInventorySize());
    }

    @Test
    public void canIncStatsViaEquipment()
    {
        loot.setIntellect(2);
        loot.setVitality(3);
        player.equipItem(loot);
        assertEquals(3, player.getIntellect());
        assertEquals(130, player.getHealth());
        assertEquals(120, player.getMana());

    }

}
