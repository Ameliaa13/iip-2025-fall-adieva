package impl;

import impl.graphics.GameFrame;
import impl.logic.GameLogic;
import impl.model.PixelColor;

import javax.swing.*;

public class GameService {

    private final GameFrame gameFrame;

    private final GameLogic gameLogic;

    public static final int FRAME_WIDTH = 400;
    public static final int FRAME_HEIGHT = 500;
    public static final int PIXEL_SIZE = 16;
    public static final int PIXELS_COUNT_W;
    public static final int PIXELS_COUNT_H;
    private final int GAME_SPEED = 40;

    static {
        PIXELS_COUNT_W = FRAME_WIDTH / PIXEL_SIZE - 1;
        PIXELS_COUNT_H = FRAME_HEIGHT / PIXEL_SIZE - 1;
    }

    public GameService(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        gameFrame = new GameFrame(this);
        for(int x = 0; x < PIXELS_COUNT_W; x++) {
            for (int y = 0; y < PIXELS_COUNT_H; y++) {
                gameFrame.getPixels()[y][x] = new PixelColor(0, 0, 0);
            }
        }
        gameLogic.init();

        new Timer(GAME_SPEED, e -> gameLogic.loop(gameFrame.getPixels())).start();
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void showFrame() {
        gameFrame.setVisible(true);
    }

    public static void finishGame(String title, String message, boolean win) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                win ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }
}
