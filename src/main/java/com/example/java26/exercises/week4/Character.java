package com.example.java26.exercises.week4;

public class Character {
    private int health;
    private final int maxHealth;

    public Character(int health, int maxHealth) {
        this.health = health;
        this.maxHealth = maxHealth;
        if (health < 0) {
            this.health = 0;
        }
        if (maxHealth < health) {
            this.health = maxHealth;
        }
    }

    public void takeDamage(int amount) {
        this.health = this.health - amount;
        if (amount >= health) {                        //dmg results in death
            this.health = 0;
        } else {
            this.health = this.health - amount;        // dmg done
        }
    }

    public void heal(int amount) {
        this.health = this.health + amount;
        if (this.health >= this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public static void main() {
        IO.println("A mage emerges from the shadows, running from someone...or something, with a visible limp.");
        Character mage = new Character(70, 100);
        IO.println(" A throng of imps encircle the mage, casting dark magic for 85 damage");
        mage.takeDamage(85);

        IO.println("Remaining health is: " + mage.getHealth() + ". Restart campaign, or use daily lifesaver?");

        //Use lifesaver
        mage.heal(500);
        IO.println("You used your lifesaver, your health is now  " + mage.getHealth() + ". You awakened at the nearest camp.");
    }

}
