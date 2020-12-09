package xavierdufour;

import xavierdufour.engine.RenderingEngine;
import xavierdufour.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;
    private int interactKey = KeyEvent.VK_SPACE;

    public GamePad() {
        super.bindKey(quitKey);
        super.bindKey(interactKey);
        RenderingEngine.getInstance().addInputListener(this);
    }

    public boolean isQuitPressed() {
        return super.isKeyPressed(quitKey);
    }
    public boolean isInteractPressed() {
        return super.isKeyPressed(interactKey);
    }
}
