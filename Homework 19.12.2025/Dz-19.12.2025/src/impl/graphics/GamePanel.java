package impl.graphics;

import impl.GameService;
import impl.model.PixelColor;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final GameFrame frame;

    public GamePanel(GameFrame frame) {
        this.frame = frame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameService gs = frame.getGameService();

        for(int x = 0; x < GameService.PIXELS_COUNT_W; x++) {
            for(int y = 0; y < GameService.PIXELS_COUNT_H; y++) {
                PixelColor pixelColor = frame.getPixels()[y][x];
                Color color = new Color(pixelColor.getRedComponent(),
                        pixelColor.getGreenComponent(),
                        pixelColor.getBlueComponent());
                g.setColor(color);
                g.fillRect(x * GameService.PIXEL_SIZE, y * GameService.PIXEL_SIZE,
                        GameService.PIXEL_SIZE, GameService.PIXEL_SIZE);
            }
        }

        repaint();
    }
}
