package xavierdufour.engine.controls;

import java.awt.event.KeyEvent;

public class MovementController extends Controller{

    private int upKey = KeyEvent.VK_UP;
    private int downKey = KeyEvent.VK_DOWN;
    private int leftKey = KeyEvent.VK_LEFT;
    private int rightKey = KeyEvent.VK_RIGHT;

    public MovementController() {
        int[] pressedKeys = {upKey, downKey, leftKey, rightKey};
        bindKeys(pressedKeys);
    }

    public boolean isUpPressed() {
        return super.isKeyPressed(upKey);
    }

    public boolean isDownPressed() {
        return super.isKeyPressed(downKey);
    }

    public boolean isLeftPressed() {
        return super.isKeyPressed(leftKey);
    }

    public boolean isRightPressed() {
        return super.isKeyPressed(rightKey);
    }

    public boolean isMoving() {
        return isUpPressed() || isDownPressed() || isLeftPressed() || isRightPressed() ;
    }

    public void setUpKey(int upKey) {
        super.removeKey(this.upKey);
        super.bindKey(upKey);
        this.upKey = upKey;
    }

    public void setDownKey(int downKey) {
        super.removeKey(this.downKey);
        super.bindKey(downKey);
        this.downKey = downKey;
    }

    public void setLeftKey(int leftKey) {
        super.removeKey(this.leftKey);
        super.bindKey(leftKey);
        this.leftKey = leftKey;
    }

    public void setRightKey(int rightKey) {
        super.removeKey(this.rightKey);
        super.bindKey(rightKey);
        this.rightKey = rightKey;
    }
}
