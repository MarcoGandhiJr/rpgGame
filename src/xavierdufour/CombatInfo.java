package xavierdufour;

import java.util.Random;

public class CombatInfo {

    private final int MAXIMUM_HEALTH;
    private final int MAXIMUM_MANA;
    private int currentMana;
    private int currentHealth;
    private int healingPower;
    private int power;

    public CombatInfo(int maxHealth, int maxMana) {
        MAXIMUM_HEALTH = maxHealth;
        MAXIMUM_MANA = maxMana;
        currentHealth = MAXIMUM_HEALTH;
        currentMana = MAXIMUM_MANA;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public void setHealingPower(int healingPower) {
        this.healingPower = healingPower;
    }

    // In combat actions
    public void receiveDamage(int damage) {
        Random random = new Random();
        // Can negate up to 3 damage (Increase with level?)
        currentHealth -= damage - random.nextInt(3);
    }

    public int dealDamage() {
        Random random = new Random();
        return power + random.nextInt(3);
    }

    public void heal() {
        Random random = new Random();
        if (currentMana < 50) {
            currentMana -= 50;
            currentHealth += healingPower - random.nextInt(1);
            if (currentHealth > MAXIMUM_HEALTH) {
                currentHealth = MAXIMUM_HEALTH;
            }
        }

    }
}
