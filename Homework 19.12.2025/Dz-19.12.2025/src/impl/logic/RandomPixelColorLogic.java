package impl.logic;

import impl.GameService;
import impl.model.PixelColor;

import java.util.Random;

public class RandomPixelColorLogic implements GameLogic {

    private final Random r = new Random();

    private boolean reset;

    @Override
    public void init() {
        reset = true;
    }

    @Override
    public void loop(PixelColor[][] pixels) {
        pixels[r.nextInt(GameService.PIXELS_COUNT_H)][r.nextInt(GameService.PIXELS_COUNT_W)] =
                new PixelColor(r.nextInt(255), r.nextInt(255), r.nextInt(255));

        if(reset) {
            reset = false;
            PixelColor pixel = new PixelColor(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            for(int x = 0; x < GameService.PIXELS_COUNT_W; x++) {
                for (int y = 0; y < GameService.PIXELS_COUNT_H; y++) {
                    pixels[y][x] = pixel;
                }
            }
        }
    }

    @Override
    public void handleKeyPress(int keyCode) {
        if (keyCode == 32) {
            reset = true;
        }
    }
}
