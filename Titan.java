import java.util.Random;
public class Titan {
    String name;
    int health;
    int attackPower;
    int maxHealth;  // Added maxHealth for Titan (optional, but can be used to track max health)

    // Updated constructor to take maxHealth
    public Titan(String name, int health, int attackPower, int maxHealth) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.maxHealth = maxHealth;  // Store the maximum health
    }

    public void specialAttack() {
        // To be implemented in subclasses
    }

    public void attack(Character target) {
        Random rand = new Random();
        int damage = attackPower + rand.nextInt(10);  // Increased damage range
        target.health -= damage;
        System.out.println(name + " attacks for " + damage + " damage!");
    }
}


