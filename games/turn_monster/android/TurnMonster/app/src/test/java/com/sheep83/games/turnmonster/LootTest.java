package com.sheep83.games.turnmonster;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 03/09/2016.
 */
public class LootTest {
    Player player;
    Loot loot;
    Dice dice;

    @Before
    public void before(){
        dice = new Dice(100);
        player = new Player("testplayer");
        loot = player.rollForLoot(dice);
    }

    @Test
    public void getTypeTest()
    {
        loot.setType("testtype");
        assertEquals("testtype", loot.getType());
    }

    @Test
    public void getEnchantTest()
    {
        loot.setEnchant("testenchant");
        assertEquals("testenchant", loot.getEnchant());
    }

    @Test
    public void getLevelTest()
    {
        assertEquals(1, loot.getLevel());
    }

    @Test
    public void getIntellectTest()
    {
        loot.setIntellect(5);
        assertEquals(5, loot.getIntellect());
    }

    @Test
    public void getStrengthTest()
    {
        loot.setStrength(4);
        assertEquals(4, loot.getStrength());
    }

    @Test
    public void getVitalityTest()
    {
        loot.setVitality(3);
        assertEquals(3, loot.getVitality());
    }

    @Test
    public void getEnumTypeTest()
    {
        loot.setEnumType(Loot.Slot.ARMOUR);
        assertEquals(Loot.Slot.ARMOUR, loot.getEnumType());
    }

    @Test
    public void getEnumRarityTest()
    {
        loot.setEnumRarity(Loot.Rarity.EPIC);
        assertEquals(Loot.Rarity.EPIC, loot.getEnumRarity());
    }






}
