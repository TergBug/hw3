package org.mycode;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {
    private final String messageWrongEntering = "Follow input rule, please";
    private final String messageWrongName = "Wrong name";
    private final String messageGreeting = "--- Rock-paper-scissors game ---";
    private final String messageName = "Enter your name: ";
    private final String messageEnteringRule = "--- Input rule ---\n<(-R or r - rock;  P or p - paper;  S or s - scissors-)>";
    private final String messageSelectRPS = "Enter your choice: ";
    private final String messageHumanChoice = "You have selected #";
    private final String messageComputerChoice = "Computer has selected #";
    private final String messageHumanWin = "Player # has won! Congratulate!";
    private final String messageComputerWin = "Computer has won";
    private final String messageNobodyWin = "Nobody has won, try again...";
    private final String messageNewGame = "Would you like to start game again? (input rule: y|n)\n";
    private final String patternForName = ".+";
    private final String patternForRPS = "R|r|P|p|S|s";
    private final String patternForNewGame = "Y|y|N|n";
    private Player playerHuman;
    private Player playerComputer;
    public GameLogic(){
        playerHuman = new Player("Human");
        playerComputer = new Player("Computer");
    }
    public void playGame(InputStream in, PrintStream out){
        do {
            playLogicOnce(in, out);
            if(validation(in, out, patternForNewGame, messageNewGame, messageWrongEntering).toLowerCase().equals("n")) break;
        }while (true);
    }
    public void playLogicOnce(InputStream in, PrintStream out){
        registration(in, out);
        do {
            inputRPS(in, out);
            generateRPS(out);
            if(checkForWinner(out)) break;
            out.println(messageNobodyWin);
        }while (true);
    }
    public void registration(InputStream in, PrintStream out){
        out.println(messageGreeting);
        playerHuman.setName(validation(in, out, patternForName, messageName, messageWrongName));
        out.println(messageEnteringRule);
    }
    public void inputRPS(InputStream in, PrintStream out){
        switch (validation(in, out, patternForRPS, messageSelectRPS, messageWrongEntering).toLowerCase()){
            case "r":
                playerHuman.setChosenElement(RPSElement.ROCK);
                break;
            case "p":
                playerHuman.setChosenElement(RPSElement.PAPER);
                break;
            case "s":
                playerHuman.setChosenElement(RPSElement.SCISSORS);
                break;
        }
        out.println(messageHumanChoice.replace("#", playerHuman.getChosenElement().name()));
    }
    public void generateRPS(PrintStream out){
        switch ((new Random()).nextInt(3)){
            case 0:
                playerComputer.setChosenElement(RPSElement.ROCK);
                break;
            case 1:
                playerComputer.setChosenElement(RPSElement.PAPER);
                break;
            case 2:
                playerComputer.setChosenElement(RPSElement.SCISSORS);
                break;
        }
        out.println(messageComputerChoice.replace("#", playerComputer.getChosenElement().name()));
    }
    public boolean checkForWinner(PrintStream out){
        playerHuman.setWinner(playerHuman.getChosenElement().checkForWin(playerComputer.getChosenElement()));
        playerComputer.setWinner(playerComputer.getChosenElement().checkForWin(playerHuman.getChosenElement()));
        if(playerHuman.isWinner() && !playerComputer.isWinner()){
            out.println(messageHumanWin.replace("#", playerHuman.getName()));
            return true;
        }
        else if(!playerHuman.isWinner() && playerComputer.isWinner()){
            out.println(messageComputerWin);
            return true;
        }
        return false;
    }
    public String validation(InputStream in, PrintStream out, String pattern, String shownText, String shownTextIfWrong){
        Scanner scanner = new Scanner(in);
        do {
            out.print(shownText);
            if(!scanner.hasNext(pattern)){
                if(scanner.hasNext()) scanner.next();
                else return "";
                out.println(shownTextIfWrong);
                continue;
            }
            return scanner.next();
        }while (true);
    }
    public Player getPlayerHuman() {
        return playerHuman;
    }
    public Player getPlayerComputer() {
        return playerComputer;
    }
}
