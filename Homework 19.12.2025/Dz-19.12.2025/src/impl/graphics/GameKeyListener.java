package impl.graphics;

import impl.logic.GameLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

    private final GameLogic gameLogic;

    public GameKeyListener(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameLogic.handleKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
