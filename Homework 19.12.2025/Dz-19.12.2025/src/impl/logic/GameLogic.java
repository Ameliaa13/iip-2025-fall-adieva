package impl.logic;

import impl.model.PixelColor;

public interface GameLogic {

    void init();

    void loop(PixelColor[][] pixels);

    void handleKeyPress(int keyCode);

}
