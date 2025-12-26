package impl.model;

import impl.exception.GameException;

public class PixelColor {

    public static final PixelColor WHITE = new PixelColor(255, 255, 255);
    public static final PixelColor BLACK = new PixelColor(0, 0, 0);
    public static final PixelColor RED = new PixelColor(255, 0, 0);
    public static final PixelColor GREEN = new PixelColor(0, 255, 0);
    public static final PixelColor BLUE = new PixelColor(0, 0, 255);
    public static final PixelColor YELLOW = new PixelColor(255, 255, 0);

    private final int redComponent;
    private final int greenComponent;
    private final int blueComponent;

    public PixelColor(int redComponent, int greenComponent, int blueComponent) {
        if(0 > redComponent || 255 < redComponent) {
            throw new GameException("Попытка создать пиксель с запредельным значением красной компоненты: " + redComponent);
        }
        if(0 > greenComponent || 255 < greenComponent) {
            throw new GameException("Попытка создать пиксель с запредельным значением зелёной компоненты: " + redComponent);
        }
        if(0 > blueComponent || 255 < blueComponent) {
            throw new GameException("Попытка создать пиксель с запредельным значением синей компоненты: " + redComponent);
        }
        this.redComponent = redComponent;
        this.greenComponent = greenComponent;
        this.blueComponent = blueComponent;
    }

    public int getRedComponent() {
        return redComponent;
    }

    public int getGreenComponent() {
        return greenComponent;
    }

    public int getBlueComponent() {
        return blueComponent;
    }
}
