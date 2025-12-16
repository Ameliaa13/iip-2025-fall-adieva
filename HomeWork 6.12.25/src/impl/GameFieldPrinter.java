package impl;

public class GameFieldPrinter {

    public void printGameField(Symbol[][] map) {
        System.out.println("====================");
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                System.out.print(map[x][y] == Symbol.NONE ? " " : map[x][y]);
                if(y < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

}
