package impl.graphics;

import impl.GameService;
import impl.exception.GameException;
import impl.model.PixelColor;

import javax.swing.*;

public class GameFrame extends JFrame {

    private final GameService gameService;

    private PixelColor[][] pixels;

    public GameFrame(GameService gameService) {
        this.gameService = gameService;
        pixels = new PixelColor[GameService.PIXELS_COUNT_H][GameService.PIXELS_COUNT_W];
        setBounds(100, 100, GameService.FRAME_WIDTH, GameService.FRAME_HEIGHT);
        setContentPane(new GamePanel(this));
        setFocusable(true);
        addKeyListener(new GameKeyListener(gameService.getGameLogic()));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void updateFrame(PixelColor[][] pixels) {
        if(pixels == null) {
            throw new GameException("Невозможно обработать пустой массив pixels");
        }
        if(pixels.length != GameService.PIXELS_COUNT_H) {
            throw new GameException("Некорректный размер pixels по высоте: " +
                    "Ожидалось " + GameService.PIXELS_COUNT_H + ", получено " + pixels.length);
        }
        if(pixels[0].length != GameService.PIXELS_COUNT_W) {
            throw new GameException("Некорректный размер pixels по длине: " +
                    "Ожидалось " + GameService.PIXELS_COUNT_W + ", получено " + pixels[0].length);
        }
        this.pixels = pixels;
    }

    public GameService getGameService() {
        return gameService;
    }

    public PixelColor[][] getPixels() {
        return pixels;
    }
}
