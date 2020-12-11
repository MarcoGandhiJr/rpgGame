package xavierdufour;

import xavierdufour.engine.Buffer;
import xavierdufour.engine.Game;
import xavierdufour.engine.RenderingEngine;
import xavierdufour.engine.Sound;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RpgGame extends Game {

    private World village;
    private World combatMap;
    private World currentMap;
    private GamePad gamePad;
    private Player player;
    private ArrayList<Zombie> enemies;
    private Combat currentCombat;
    private BufferedImage deathScreen;
    private boolean inFight = false;


    @Override
    public void initialize() {
        //RenderingEngine.getInstance().getScreen().fullScreen();
        gamePad = new GamePad();
        player = new Player(gamePad);
        enemies = new ArrayList<>();
        setupEnemies();
        setupMaps();
        currentMap = village;
        player.teleport(400, 300);
        loadSprite();
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            this.stop();
        }
        if (showDeathScreen()) {
            return;
        }
        if (inFight) {
            currentCombat.play();
            if (currentCombat.isCombatEnded()) {
                inFight = false;
                currentMap = village;
            }
        } else {
            int deadEnemyIndex = -1;
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).isDead()) {
                    enemies.get(i).removeHitBox();
                    deadEnemyIndex = i;
                }
                enemies.get(i).update(player.getX(), player.getY());
                if (enemies.get(i).hitBoxIntersectWith(player)) {
                    inFight = true;
                    currentMap = combatMap;
                    Sound.play("sounds/zombie.wav");
                    currentCombat = new Combat(player.fight(), enemies.get(i).fight(), gamePad);
                }
            }
            if (deadEnemyIndex != -1) {
                enemies.remove(deadEnemyIndex);
            }
            if (enemies.size() == 0) {
                setupEnemies();
            }
            player.update();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        currentMap.draw(buffer);
        if (showDeathScreen()) {
            buffer.drawImage(deathScreen, 200, 150);

        } else if (inFight) {
            currentCombat.printHealth(buffer);
        } else  {
            player.draw(buffer);
            for (Zombie enemy : enemies) {
                enemy.draw(buffer);
            }
        }
    }

    @Override
    public void conclude() { }

    private void loadSprite() {
        try {
            deathScreen = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/gameOver.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean showDeathScreen() {
        return player.fight().isDead();
    }

    private void setupMaps() {
        ArrayList<Blockade> villageBlockades = new ArrayList<>();
        ArrayList<Blockade> combatMapBlockades = new ArrayList<>();
        /*
         * TODO Create more maps that are more detailed.
        */
        Blockade topBorder = new Blockade();
        topBorder.setDimension(800, 8);
        topBorder.teleport(0, -8);
        Blockade leftBorder = new Blockade();
        leftBorder.setDimension(5, 1000);
        leftBorder.teleport(0, 0);
        Blockade rightBorder = new Blockade();
        rightBorder.setDimension(8, 800);
        rightBorder.teleport(800, 0);
        Blockade bottomBorder = new Blockade();
        bottomBorder.setDimension(800, 9);
        bottomBorder.teleport(0, 600);
        Blockade h1 = new Blockade();
        h1.teleport(80, 0);
        h1.setDimension(112, 132);
        Blockade h2  = new Blockade();
        h2.teleport(370, 530);
        h2.setDimension(112, 132);

        villageBlockades.add(h1);
        villageBlockades.add(h2);
        villageBlockades.add(topBorder);
        villageBlockades.add(bottomBorder);
        villageBlockades.add(leftBorder);
        villageBlockades.add(rightBorder);

        village = new World("maps/village.png", villageBlockades);
        combatMap = new World("maps/combatMap.png", combatMapBlockades);
    }

    private void setupEnemies() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            enemies.add(new Zombie());
            enemies.get(i).teleport(random.nextInt(700), random.nextInt(500));
        }
    }
}
