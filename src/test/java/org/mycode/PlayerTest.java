package org.mycode;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player testedPlayer = new Player("");
    private String testedName = "Fred";
    private RPSElement testedRPSElement = RPSElement.ROCK;
    @Test
    public void shouldSetupFields(){
        testedPlayer.setName(testedName);
        assertEquals(testedName, testedPlayer.getName());
        testedPlayer.setChosenElement(testedRPSElement);
        assertEquals(testedRPSElement, testedPlayer.getChosenElement());
        testedPlayer.setWinner(true);
        assertTrue(testedPlayer.isWinner());
    }

}