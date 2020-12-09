package xavierdufour;

import xavierdufour.engine.Buffer;
import xavierdufour.engine.CollidableRepository;
import xavierdufour.engine.entity.StaticEntity;

import java.awt.*;

public class Blockade extends StaticEntity {
    public Blockade () {
        CollidableRepository.getInstance().registerEntity(this);
    }

    public void draw (Buffer buffer) {
        if (GameSettings.DEBUG_MODE_ACTIVE) {
            buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
        }
    }
}
