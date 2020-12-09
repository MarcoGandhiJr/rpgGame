package xavierdufour.engine.entity;

import xavierdufour.engine.Buffer;
import xavierdufour.engine.controls.Direction;

import java.awt.*;

public abstract class MovableEntity extends UpdatableEntity{

    private Collision collision;
    private Direction direction = Direction.UP;
    private int speed = 1;
    private boolean moved;
    private int lastX;
    private int lastY;

    public MovableEntity() {
        collision = new Collision(this);
    }

    @Override
    public void update() {
        moved = false;
    }

    @Override
    public void draw(Buffer buffer) {

    }

    public boolean hasMoved() {
        return moved;
    }

    public boolean hitBoxIntersectWith(StaticEntity other) {
        if (other == null) {
            return false;
        }
        return getHitBox().intersects(other.getBounds());
    }

    public void moveLeft() {
        move(Direction.LEFT);
    }

    public void moveRight() {
        move(Direction.RIGHT);
    }

    public void moveUp() { move(Direction.UP); }

    public void moveDown() {
        move(Direction.DOWN);
    }

    public void move(Direction direction) {
        this.direction = direction;
        int allowedDistance = collision.getAllowedSpeed(direction);
        x += direction.getVelocityX(allowedDistance);
        y += direction.getVelocityY(allowedDistance);
        moved = x != lastX || y != lastY;
        lastX = x;
        lastY = y;
    }

    public void drawHitBox(Buffer buffer) {
        Rectangle rectangle = getHitBox();
        buffer.drawRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, Color.RED);
    }

    protected Rectangle getHitBox() {
        switch (direction) {
            case UP: return getUpperHitBox();
            case DOWN: return getLowerHitBox();
            case LEFT: return getLeftHitBox();
            case RIGHT: return getRightHitBox();
            default: return getBounds();
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private Rectangle getUpperHitBox() {
        return new Rectangle(x, y - speed, width, speed);
    }

    private Rectangle getLowerHitBox() {
        return new Rectangle(x, y + height, width, speed);
    }

    private Rectangle getLeftHitBox() {
        return new Rectangle(x - speed, y, speed, height);
    }

    private Rectangle getRightHitBox() {
        return new Rectangle(x + width, y, speed, height);
    }
}
