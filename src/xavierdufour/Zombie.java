package xavierdufour;

import xavierdufour.engine.Buffer;
import xavierdufour.engine.entity.MovableEntity;

public class Zombie extends MovableEntity {

    private CombatInfo stats;

    public Zombie() {
        stats = new CombatInfo(45, 75);
    }

    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void update() {

    }
}
