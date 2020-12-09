package xavierdufour;

import xavierdufour.engine.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class World {

    private final String MAP_PATH;
    private Image background;
    private ArrayList<Blockade> worldBorders;
    private int x;
    private int y;

    public World(String mapPath, ArrayList<Blockade> worldBorders) {
        MAP_PATH = mapPath;
        loadBackground();
        this.worldBorders = worldBorders;
    }

    public void updateCamera() {

    }

    public void draw(Buffer buffer) {
        buffer.drawImage(background, 0, 0);
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
