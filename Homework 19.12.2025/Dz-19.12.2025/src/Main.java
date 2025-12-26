import impl.GameService;
import impl.logic.GameLogic;
import impl.logic.RaceLogic;

public class Main {
    public static void main(String[] args) {
        GameLogic gameLogic = new RaceLogic();
        // GameLogic gameLogic = new TetrisGameLogic();
        GameService gameService = new GameService(gameLogic);
        gameService.showFrame();
    }
}