import impl.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameFieldPrinter gameFieldPrinter = new GameFieldPrinter();
        GameService gameService = new GameService(gameFieldPrinter);

        boolean useAI = true;

        AbstractPlayer player2 = new KeyboardPlayer(scanner, Symbol.X);
        AbstractPlayer player1;
        if(useAI) {
            player1 = new ArtificialIntelligencePlayerV1();
            player1.setSymbol(Symbol.O);
        } else {
            player1 = new KeyboardPlayer(scanner, Symbol.O);
        }

        player1.setGameService(gameService);
        player2.setGameService(gameService);

        int i = 0;
        while (i < 10) {
            i++;
            boolean p1 = false;
            while (!p1) {
                try {
                    player1.doTurn();
                    p1 = true;
                } catch (IllegalArgumentException e) {
                    System.err.println("Unavailable turn: " + e.getMessage());
                }
            }
            boolean p2 = false;
            while (!p2) {
                try {
                    player2.doTurn();
                    p2 = true;
                } catch (IllegalArgumentException e) {
                    System.err.println("Unavailable turn: " + e.getMessage());
                }
            }
        }
    }
}