package org.mycode;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

public class GameRunner {
    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.playGame(System.in, System.out);
        //game.playLogicOnce(new ByteArrayInputStream("Fhjh\np".getBytes()), System.out);
        //game.playGame(new ByteArrayInputStream("Fred\np\nn".getBytes()), System.out);
    }
}
