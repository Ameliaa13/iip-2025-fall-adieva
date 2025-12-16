package impl;

import java.util.Random;

public class ArtificialIntelligencePlayerV1 extends AbstractPlayer {
    @Override
    public void doTurn() {
        System.out.println("ArtificialIntelligenceV1 doing turn...");
        Symbol symbol1;
        Random random = new Random();
        int pos;
        if (gameService.getSymbolByLocation(5) == Symbol.NONE) {
            gameService.doTurn(symbol, 5);
            return;
        }

        if (gameService.checkLine(symbol) != 0) {
            gameService.doTurn(symbol, gameService.checkLine(symbol));
            return;
        }

        if (gameService.checkLine(Symbol.X) != 0) {
            gameService.doTurn(symbol, gameService.checkLine(Symbol.X));
            return;
        }
        
        do {
            pos = random.nextInt(9) + 1;
            symbol1 = gameService.getSymbolByLocation(pos);
        } while (symbol1 != Symbol.NONE);
        gameService.doTurn(symbol, pos);
    }
}
