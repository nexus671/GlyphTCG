package Cards;

import Game.Player;

/**
 * Created by acurr on 5/25/2016.
 */
public abstract class Troop extends Card {
    private int damage;
    private int currentHealth;
    private int maxHealth;

    public Troop() {
    }

    public void attack(Player player) {
        player.recieveDamage(damage);
    }

    public void attack(Troop troop) {
        troop.setCurrentHealth(troop.getCurrentHealth() - damage);
        this.setCurrentHealth(this.getCurrentHealth() - troop.getDamage());
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    @Override
    public abstract String getDescription();

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public String toString() {
        return "(" + name + " Glyphs " + getThresholds() + " Damage: " + damage + " Health: " + currentHealth + ")";
    }
}
