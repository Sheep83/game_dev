package com.sheep83.games.turnmonster;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 03/09/2016.
 */
public class DiceTest {

    Dice dice, nullDice;

    @Before
    public void before(){
        dice = new Dice(100);
        nullDice = new Dice();
    }

    @Test
    public void getSidesTest()
    {
        assertEquals(100, (int)dice.getSides());
    }

    @Test
    public void setSidesTest()
    {
        dice.setSides(6);
        assertEquals(6, (int)dice.getSides());
    }

    @Test
    public void overloadTest()
    {
        assertEquals(null, nullDice.getSides());
    }

    @Test
    public void rollTest()
    {
        nullDice.setSides(20);
        assertNotNull(nullDice.roll());
    }

}
