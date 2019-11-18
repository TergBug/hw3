package org.mycode;

public enum RPSElement{
    UNDEFINED,
    ROCK,
    PAPER,
    SCISSORS;
    public boolean checkForWin(RPSElement enemy){
        switch (this){
            case ROCK: return enemy.equals(RPSElement.SCISSORS);
            case PAPER: return enemy.equals(RPSElement.ROCK);
            case SCISSORS: return enemy.equals(RPSElement.PAPER);
        }
        return false;
    }
}
