package xavierdufour.engine;

import xavierdufour.engine.controls.Mouse;

import java.awt.event.KeyListener;

public abstract class Game {

    private RenderingEngine renderingEngine;
    private GameTime gameTime;
    private Mouse mouse;
    private boolean playing = true;

    public Game() {
        renderingEngine = RenderingEngine.getInstance();
        mouse = renderingEngine.getMouseInstance();
    }

    public abstract void initialize();
    public abstract void update();
    public abstract void draw(Buffer buffer);
    public abstract void conclude();

    public void start() {
        initialize();
        run();
        conclude();
    }

    public void stop() {
        playing = false;
    }

    public void addKeyListener(KeyListener listener) {
        renderingEngine.addInputListener(listener);
    }

    public void run() {
        renderingEngine.start();
        gameTime = new GameTime();
        while (playing) {
            update();
            draw(renderingEngine.getRenderingBuffer());
            renderingEngine.renderBufferOnScreen();
            gameTime.synchronize();
        }
        renderingEngine.stop();
    }
}
