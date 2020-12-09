package xavierdufour.engine.entity;

import xavierdufour.engine.controls.MovementController;

public abstract class ControllableEntity extends MovableEntity {

    private MovementController controller;

    public ControllableEntity(MovementController controller) {
        this.controller = controller;
    }

    public void  moveAccordingToHandler() {
        if (!controller.isMoving()) {
            return;
        }
        if (controller.isUpPressed()) {
            moveUp();
        } else if (controller.isDownPressed()) {
            moveDown();
        } else if (controller.isLeftPressed()) {
            moveLeft();
        } else if (controller.isRightPressed()) {
            moveRight();
        }
    }
}
