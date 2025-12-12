package impl;

public class GameService {

    private Symbol[][] map = new Symbol[3][3];

    private GameFieldPrinter gameFieldPrinter;

    public GameService(GameFieldPrinter gameFieldPrinter) {
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                map[x][y] = Symbol.NONE;
            }
        }
        this.gameFieldPrinter = gameFieldPrinter;
        gameFieldPrinter.printGameField(map);
    }

    public void doTurn(Symbol symbol, int position) {
        if(symbol == Symbol.NONE) {
            throw new IllegalArgumentException("Cannot set NONE symbol");
        }
        if(position < 1 || position > 9) {
            throw new IllegalArgumentException("Not existing coordinate " + position);
        }
        int x = (position-1) / 3;
        int y = (position-1) % 3;
        if(map[x][y] != Symbol.NONE) {
            throw new IllegalArgumentException("Cannot set new symbol above existing");
        }
        map[x][y] = symbol;
        gameFieldPrinter.printGameField(map);

        Symbol status = checkEndOfTheGame();
        if(status != Symbol.NONE) {
            System.out.println("Player " + status + " wins!");
            System.exit(0);
        }

        if(!hasEmptyPositions()) {
            System.out.println("Game ends, draw!");
            System.exit(0);
        }
    }

    public Symbol getSymbolByLocation(int position) {
        if(position < 1 || position > 9) {
            throw new IllegalArgumentException("Not existing coordinate " + position);
        }
        int x = (position-1) / 3;
        int y = (position-1) % 3;
        return map[x][y];
    }

    private boolean hasEmptyPositions() {
        for(int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if(map[x][y] == Symbol.NONE) return true;
            }
        }
        return false;
    }

    public int checkLine(Symbol symbol) {
        for (int x = 0; x < 3; x++) {
            int emptyCount = 0;
            int notEmptyCount = 0;
            int emptyCoordinate = 0;

            for (int y = 0; y < 3; y++) {
                if (map[x][y] == symbol) {
                    notEmptyCount++;
                } else if(map[x][y] == Symbol.NONE) {
                    emptyCount++;
                    emptyCoordinate = y;
                }
            }

            if (emptyCount == 1 && notEmptyCount == 2) {
                return 3 * x + emptyCoordinate + 1;
            }

        }

        for (int y = 0; y < 3; y++) {
            int emptyCount = 0;
            int notEmptyCount = 0;
            int emptyCoordinate = 0;

            for (int x = 0; x < 3; x++) {
                if (map[x][y] == symbol) {
                    notEmptyCount++;
                } else if(map[x][y] == Symbol.NONE) {
                    emptyCount++;
                    emptyCoordinate = x;
                }
            }

            if (emptyCount == 1 && notEmptyCount == 2) {
                return 3 * emptyCoordinate + y + 1;
            }

        }

        for (int x = 0; x  < 3; x++) {
            int emptyCount = 0;
            int notEmptyCount = 0;
            int emptyCoordinate = 0;

            if (map[x][x] == symbol) {
                notEmptyCount++;
            } else if(map[x][x] == Symbol.NONE) {
                emptyCount++;
                emptyCoordinate = x;
            }

            if (emptyCount == 1 && notEmptyCount == 2) {
                return 3 * emptyCoordinate + emptyCoordinate + 1;
            }

        }

        for (int x = 0; x < 3; x++) {
            int emptyCount = 0;
            int notEmptyCount = 0;
            int emptyCoordinate = 0;

            if (map[x][2-x] == symbol) {
                notEmptyCount++;
            } else if(map[x][2-x] == Symbol.NONE) {
                emptyCount++;
                emptyCoordinate = x;
            }

            if (emptyCount == 1 && notEmptyCount == 2) {
                return 3 * emptyCoordinate + (2- emptyCoordinate) + 1;
            }

        }

        return 0;

    }


    private Symbol checkEndOfTheGame() {
        // Check lines
        for(int x = 0; x < 3; x++) {
            if(map[x][0] == map[x][1] &&
                    map[x][1] == map[x][2] &&
                    map[x][0] != Symbol.NONE) {
                return map[x][0];
            }
        }
        // Check columns
        for(int y = 0; y < 3; y++) {
            if(map[0][y] == map[1][y] &&
                    map[1][y] == map[2][y] &&
                    map[0][y] != Symbol.NONE) {
                return map[0][y];
            }
        }
        // Check diagonals
        if(map[0][0] == map[1][1] &&
                map[1][1] == map[2][2] &&
                map[0][0] != Symbol.NONE) {
            return map[0][0];
        }
        if(map[0][2] == map[1][1] &&
                map[1][1] == map[2][0] &&
                map[0][2] != Symbol.NONE) {
            return map[0][2];
        }
        return Symbol.NONE;
    }

    
}
