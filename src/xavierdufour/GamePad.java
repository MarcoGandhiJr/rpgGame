package xavierdufour;

import xavierdufour.engine.RenderingEngine;
import xavierdufour.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_ESCAPE;
    private int attackKey = KeyEvent.VK_1;
    private int healKey = KeyEvent.VK_2;


    public GamePad() {
        super.bindKey(quitKey);
        super.bindKey(attackKey);
        super.bindKey(healKey);
        RenderingEngine.getInstance().addInputListener(this);
    }

    public boolean isQuitPressed() {
        return super.isKeyPressed(quitKey);
    }
    public boolean isAttackPressed() {
        return super.isKeyPressed(attackKey);
    }
    public boolean isHealPressed() {
        return super.isKeyPressed(healKey);
    }
}
