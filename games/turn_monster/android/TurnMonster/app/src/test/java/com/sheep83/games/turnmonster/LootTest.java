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

    @Before
    public void before(){
        player = new Player("testplayer");
        loot = new Loot("testtype", "testenchant", 1);
    }

    @Test
    public void getNameTest()
    {
        assertEquals("testname", loot.getName());
    }

    @Test
    public void getTyoeTest()
    {
        assertEquals("testtype", loot.getType());
    }

    @Test
    public void getEnchantTest()
    {
        assertEquals("testenchant", loot.getEnchant());
    }

    @Test
    public void getLevelTest()
    {
        assertEquals(1, loot.getLevel());
    }




}
