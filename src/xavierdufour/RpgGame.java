package xavierdufour;

import xavierdufour.engine.Buffer;
import xavierdufour.engine.Game;
import xavierdufour.engine.RenderingEngine;

import java.awt.*;
import java.util.ArrayList;

public class RpgGame extends Game {

    private World village;
    private World worldMap;
    private World currentMap;
    private GamePad gamePad;
    private Player player;


    public RpgGame() {
        currentMap = worldMap;
    }

    @Override
    public void initialize() {
        RenderingEngine.getInstance().getScreen().fullScreen();
        gamePad = new GamePad();
        player = new Player(gamePad);
        setupMaps();
        currentMap = worldMap;
        //load musics
    }

    @Override
    public void update() {
        player.update();
        if (gamePad.isQuitPressed()) {
            this.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        currentMap.draw(buffer);
        player.draw(buffer);
    }

    @Override
    public void conclude() { }



    private void setupMaps() {
        //ArrayList<Blockade> villageBlockade = new ArrayList<>();
        //village = new World("maps/village", villageBlockade);
        /*
        * TODO Add World Blockades and instantiate them all
        * */

        ArrayList<Blockade> worldMapBlockade = new ArrayList<>();
        worldMap = new World("maps/worldMap.png", worldMapBlockade);
        /*
         * TODO Add Village Blockades and instantiate them all
         * */
    }






}
