package org.mycode;

public class Player {
    private String name;
    private RPSElement chosenElement;
    private boolean isWinner;
    public Player(String name){
        this.name = name;
        this.chosenElement = RPSElement.UNDEFINED;
        this.isWinner = false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public RPSElement getChosenElement() {
        return chosenElement;
    }
    public void setChosenElement(RPSElement chosenElement) {
        this.chosenElement = chosenElement;
    }
    public boolean isWinner() {
        return isWinner;
    }
    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
