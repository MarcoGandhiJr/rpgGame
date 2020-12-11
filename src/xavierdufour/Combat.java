package xavierdufour;

import xavierdufour.engine.Buffer;

public class Combat {

    private CombatInfo player;
    private CombatInfo enemy;
    private boolean ended;
    private String currentTurn = "Player";
    private GamePad gamePad;

    public Combat(CombatInfo player, CombatInfo enemy, GamePad gamePad) {
        this.player = player;
        this.enemy = enemy;
        this.gamePad = gamePad;
    }

    public void play() {
        if (currentTurn.equals("Player")) {
            if (gamePad.isAttackPressed()) {
                int damage = player.dealDamage();
                enemy.receiveDamage(damage);
                changeTurn();
            } else if (gamePad.isHealPressed()) {
                player.heal();
                changeTurn();
            }
        } else if (currentTurn.equals("Enemy")) {
            aiTurn();
            changeTurn();
        }
        ended = player.isDead() || enemy.isDead();
    }

    public boolean isCombatEnded() {
        return ended;
    }

    public void printHealth(Buffer buffer) {
        player.printHealth(buffer, 34, 450, "Player");
        enemy.printHealth(buffer, 24, 550,  "Zombie");
    }

    private void aiTurn() {
        if (enemy.wantToHeal()) {
            enemy.heal();
        } else {
            int damage = enemy.dealDamage();
            player.receiveDamage(damage);
        }
    }

    private void changeTurn() {
        if (currentTurn.equals("Player")) {
            currentTurn = "Enemy";
        } else if (currentTurn.equals("Enemy")) {
            currentTurn = "Player";
        }
    }




}
