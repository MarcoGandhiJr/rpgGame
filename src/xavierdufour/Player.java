package xavierdufour;

import xavierdufour.engine.Buffer;
import xavierdufour.engine.controls.Direction;
import xavierdufour.engine.controls.MovementController;
import xavierdufour.engine.entity.ControllableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Player extends ControllableEntity {

    private static final String SPRITE_PATH = "sprites/playerSheet.png";
    private static final int ANIMATION_SPEED = 8;
    private static final int IDLE_FRAME = 0;
    private BufferedImage spriteSheet;
    private Image[] downFrames;
    private Image[] upFrames;
    private Image[] leftFrames;
    private Image[] rightFrames;
    private int currentAnimationFrame = 0;
    private int nextFrame = ANIMATION_SPEED;
    private CombatInfo stats;

    public Player(MovementController controller) {
        super(controller);
        setSpeed(3);
        setDimension(32, 32);
        setDirection(Direction.DOWN);
        loadSpriteSheet();
        loadFrames();
        setupPlayer();
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToHandler();
        changeFrame();
    }

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

    private void setupPlayer() {
        Random random = new Random();
        stats = new CombatInfo(100, 500);
        stats.setHealingPower(random.nextInt(25) + 1);
        stats.setPower(7);
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

        upFrames = new Image[3];
        upFrames[0] = spriteSheet.getSubimage(0, 32, width, height);
        upFrames[1] = spriteSheet.getSubimage(32, 32, width, height);
        upFrames[2] = spriteSheet.getSubimage(64, 32, width, height);

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(0, 64, width, height);
        leftFrames[1] = spriteSheet.getSubimage(32, 64, width, height);
        leftFrames[2] = spriteSheet.getSubimage(64, 64, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(64, 96, width, height);
        rightFrames[1] = spriteSheet.getSubimage(0, 96, width, height);
        rightFrames[2] = spriteSheet.getSubimage(32, 96, width, height);
    }
}
