package org.mycode;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class GameLogicTest {
    private GameLogic testedGame = new GameLogic();
    private String testedNameOfPlayer = "Fred";
    private ByteArrayInputStream inputForRegistration = new ByteArrayInputStream(testedNameOfPlayer.getBytes());
    private ByteArrayInputStream inputForInputRPSRock = new ByteArrayInputStream("r".getBytes());
    private ByteArrayInputStream inputForInputRPSPaper = new ByteArrayInputStream("p".getBytes());
    private ByteArrayInputStream inputForInputRPSScissors = new ByteArrayInputStream("s".getBytes());
    private ByteArrayInputStream inputForValidation = new ByteArrayInputStream("S".getBytes());
    private ByteArrayInputStream inputForValidationWrong = new ByteArrayInputStream("K".getBytes());
    private final String messageSelectRPS = "Enter your choice: ";
    private final String messageWrongEntering = "Follow input rule, please";
    private final String patternForRPS = "R|r|P|p|S|s";
    @Before
    public void setupGameLogic(){
        testedGame.init();
    }
    @Test
    public void shouldCompeteRegistration(){
        assertEquals("Human", testedGame.getPlayerHuman().getName());
        assertEquals("Computer", testedGame.getPlayerComputer().getName());
        testedGame.registration(inputForRegistration, System.out);
        assertEquals(testedNameOfPlayer, testedGame.getPlayerHuman().getName());
    }
    @Test
    public void shouldInputRPS(){
        assertEquals(RPSElement.UNDEFINED, testedGame.getPlayerHuman().getChosenElement());
        testedGame.inputRPS(inputForInputRPSRock, System.out);
        assertEquals(RPSElement.ROCK, testedGame.getPlayerHuman().getChosenElement());
        testedGame.inputRPS(inputForInputRPSPaper, System.out);
        assertEquals(RPSElement.PAPER, testedGame.getPlayerHuman().getChosenElement());
        testedGame.inputRPS(inputForInputRPSScissors, System.out);
        assertEquals(RPSElement.SCISSORS, testedGame.getPlayerHuman().getChosenElement());
    }
    @Test
    public void shouldCorrectCheckForWinner(){
        testedGame.getPlayerHuman().setChosenElement(RPSElement.ROCK);
        testedGame.getPlayerComputer().setChosenElement(RPSElement.ROCK);
        assertFalse(testedGame.checkForWinner(System.out));
        testedGame.getPlayerHuman().setChosenElement(RPSElement.SCISSORS);
        testedGame.getPlayerComputer().setChosenElement(RPSElement.ROCK);
        testedGame.checkForWinner(System.out);
        assertFalse(testedGame.getPlayerHuman().isWinner());
        assertTrue(testedGame.getPlayerComputer().isWinner());
        testedGame.getPlayerHuman().setChosenElement(RPSElement.PAPER);
        testedGame.getPlayerComputer().setChosenElement(RPSElement.ROCK);
        testedGame.checkForWinner(System.out);
        assertTrue(testedGame.getPlayerHuman().isWinner());
        assertFalse(testedGame.getPlayerComputer().isWinner());
        testedGame.getPlayerHuman().setChosenElement(RPSElement.ROCK);
        testedGame.getPlayerComputer().setChosenElement(RPSElement.SCISSORS);
        testedGame.checkForWinner(System.out);
        assertTrue(testedGame.getPlayerHuman().isWinner());
        assertFalse(testedGame.getPlayerComputer().isWinner());
        testedGame.getPlayerHuman().setChosenElement(RPSElement.ROCK);
        testedGame.getPlayerComputer().setChosenElement(RPSElement.PAPER);
        testedGame.checkForWinner(System.out);
        assertFalse(testedGame.getPlayerHuman().isWinner());
        assertTrue(testedGame.getPlayerComputer().isWinner());
    }
    @Test
    public void shouldValidateInputWithPattern(){
        assertEquals("S", testedGame.validation(inputForValidation, System.out, patternForRPS, messageSelectRPS, messageWrongEntering));
        assertNotEquals("K", testedGame.validation(inputForValidationWrong, System.out, patternForRPS, messageSelectRPS, messageWrongEntering));
    }
    @Test
    public void shouldReactToNullArgumentsInRegistration(){
        testedGame.registration(null, null);
        assertEquals("Human", testedGame.getPlayerHuman().getName());
    }
    @Test
    public void shouldReactToNullArgumentsInInputRPS(){
        testedGame.inputRPS(null, null);
        assertEquals(RPSElement.UNDEFINED, testedGame.getPlayerHuman().getChosenElement());
    }
    @Test
    public void shouldReactToNullArgumentsInGenerateRPS(){
        testedGame.generateRPS(null);
        assertEquals(RPSElement.UNDEFINED, testedGame.getPlayerComputer().getChosenElement());
    }
    @Test
    public void shouldReactToNullArgumentsInCheckForWinner(){
        assertFalse(testedGame.checkForWinner(null));
    }
    @Test
    public void shouldReactToNullArgumentsInValidation(){
        assertEquals(null, testedGame.validation(null, null, "","",""));
    }
}