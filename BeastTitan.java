import java.util.Random;

import java.util.Random;

public class BeastTitan extends Titan {

    public BeastTitan() {
        super("Beast Titan", 150, 20, 8);  // Set base health, attackPower, and defensePower
    }

    // Special attack for Beast Titan: Throws rocks, increasing damage range
    @Override
    public void specialAttack() {
        System.out.println(name + " prepares to throw rocks!");
        attackPower += 10;  // Boost attack power for the next attack
    }

    @Override
    public void attack(Character target) {
        Random rand = new Random();
        int damage = attackPower + rand.nextInt(10);
        target.health -= damage;
        System.out.println(name + " attacks for " + damage + " damage!");
    }
}
