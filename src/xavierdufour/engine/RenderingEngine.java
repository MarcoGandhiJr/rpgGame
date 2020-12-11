package xavierdufour.engine;

import xavierdufour.engine.controls.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class RenderingEngine {

    private static RenderingEngine instance;
    private Screen screen;
    private JPanel panel;
    private Mouse mouse;
    private BufferedImage bufferedImage;

    public static RenderingEngine getInstance() {
        if (instance == null) {
            instance = new RenderingEngine();
        }
        return instance;
    }

    public Mouse getMouseInstance() {
        if (this.mouse == null) {
            this.mouse = new Mouse();
            panel.addMouseListener(this.mouse);
            return this.mouse;
        }
        return mouse;
    }

    public Screen getScreen() {
        return screen;
    }

    public void start() {
        screen.start();
    }

    public void stop() {
        screen.end();
    }

    public Buffer getRenderingBuffer() {
        bufferedImage = new BufferedImage(800, 600,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHints(getOptimalRenderingHints());
        return new Buffer(graphics);
    }

    public void renderBufferOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }

    public void addInputListener(KeyListener listener) {
        panel.addKeyListener(listener);
    }

    private RenderingEngine() {
        initializeFrame();
        initializePanel();
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        screen.setPanel(panel);
    }

    private void initializeFrame() {
        screen = new Screen();
        screen.setSize(800, 600);
        screen.setTitle("Xavier's Rpg Game");
    }

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }
}
