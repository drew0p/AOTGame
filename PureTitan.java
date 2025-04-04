import java.util.Random;
import java.util.Random;

public class PureTitan extends Titan {

    public PureTitan() {
        super("Pure Titan", 100, 10, 5);  // Set base health, attackPower, and defensePower
    }

    // Special attack for Pure Titan: Charge, temporarily boost attack power
    @Override
    public void specialAttack() {
        System.out.println(name + " charges up for a powerful attack next turn!");
        attackPower += 5;  // Boost attack power for the next attack
    }

    @Override
    public void attack(Character target) {
        Random rand = new Random();
        int damage = attackPower + rand.nextInt(5);
        target.health -= damage;
        System.out.println(name + " attacks for " + damage + " damage!");
    }
}

