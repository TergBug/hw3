package org.mycode;

public class GameRunner {
    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.playGame(System.in, System.out);
    }
}
