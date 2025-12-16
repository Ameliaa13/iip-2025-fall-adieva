package impl;

import java.util.Scanner;

public class KeyboardPlayer extends AbstractPlayer {

    private Scanner scanner;

    public KeyboardPlayer(Scanner scanner, Symbol symbol) {
        this.scanner = scanner;
        if(symbol == Symbol.NONE) {
            throw new IllegalArgumentException("Cannot create player with NONE symbol!");
        }
        this.symbol = symbol;
    }

    @Override
    public void doTurn() {
        System.out.println("Ход игрока " + symbol + ".");
        System.out.println("Введите координату для хода (1-9): ");
        int pos = scanner.nextInt();
        gameService.doTurn(symbol, pos);
    }
}
