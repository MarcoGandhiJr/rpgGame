package xavierdufour;

import xavierdufour.engine.Buffer;
import xavierdufour.engine.CollidableRepository;
import xavierdufour.engine.controls.Direction;
import xavierdufour.engine.entity.MovableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Zombie extends MovableEntity {

    private static final String SPRITE_PATH = "sprites/zombieSheet.png";
    private static final int ANIMATION_SPEED = 8;
    private static final int IDLE_FRAME = 1;
    private BufferedImage spriteSheet;
    private Image[] downFrames;
    private Image[] upFrames;
    private Image[] leftFrames;
    private Image[] rightFrames;
    private int currentAnimationFrame = 0;
    private int nextFrame = ANIMATION_SPEED;
    private CombatInfo stats;

    public Zombie() {
        CollidableRepository.getInstance().registerEntity(this);
        stats = new CombatInfo(100, 150);
        super.setDimension(32, 32);
        stats.setPower(4);
        setSpeed(1);
        loadSpriteSheet();
        loadFrames();
        stats.setHealingPower(10);
    }

    public void update(int xObjective, int yObjective) {
        super.update();
        moveTowardPlayer(xObjective, yObjective);
        changeFrame();
    }

    @Override
    public void draw(Buffer buffer) {
        if (getDirection() == Direction.DOWN) {
            buffer.drawImage(downFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.UP) {
            buffer.drawImage(upFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.LEFT) {
            buffer.drawImage(leftFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.RIGHT) {
            buffer.drawImage(rightFrames[currentAnimationFrame], x, y);
        }
    }

    public CombatInfo fight() {
        return stats;
    }

    private void changeFrame() {
        if (super.hasMoved()) {
            nextFrame--;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= leftFrames.length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = IDLE_FRAME;
        }
    }

    private void moveTowardPlayer(int xObjective, int yObjective) {
        if (isObjectiveLeft(xObjective)) {
            moveLeft();
        }
        if (isObjectiveRight(xObjective)) {
            moveRight();
        }
        if (isObjectiveDown(yObjective)) {
            moveDown();
        }
        if (isObjectiveUp(yObjective)) {
            moveUp();
        }
    }

    public boolean isDead() {
        return stats.isDead();
    }

    public void removeHitBox() {
        CollidableRepository.getInstance().unregisterEntity(this);
    }

    private boolean isObjectiveDown(int yObjective) {
        return yObjective > y;
    }

    private boolean isObjectiveUp(int yObjective) {
        return yObjective < y;
    }

    private boolean isObjectiveLeft(int xObjective) {
        return xObjective < x;
    }

    private boolean isObjectiveRight(int xObjective) {
        return xObjective > x;
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFrames() {
        downFrames = new Image[3];
        downFrames[0] = spriteSheet.getSubimage(0, 0, width, height);
        downFrames[1] = spriteSheet.getSubimage(32, 0, width, height);
        downFrames[2] = spriteSheet.getSubimage(64, 0, width, height);

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(0, 32, width, height);
        leftFrames[1] = spriteSheet.getSubimage(32, 32, width, height);
        leftFrames[2] = spriteSheet.getSubimage(64, 32, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(0, 64, width, height);
        rightFrames[1] = spriteSheet.getSubimage(32, 64, width, height);
        rightFrames[2] = spriteSheet.getSubimage(64, 64, width, height);

        upFrames = new Image[3];
        upFrames[0] = spriteSheet.getSubimage(0, 96, width, height);
        upFrames[1] = spriteSheet.getSubimage(32, 96, width, height);
        upFrames[2] = spriteSheet.getSubimage(64, 96, width, height);
    }
}
