import java.util.Random;

public class ColossalTitan extends Titan {

    public ColossalTitan() {
        super("Colossal Titan", 300, 25, 10);  // Set base health, attackPower, and defensePower
    }

    // Special attack for Colossal Titan: Steam Burst, huge damage over time
    @Override
    public void specialAttack() {
        System.out.println(name + " releases a steam burst! It burns anything in its range!");
        attackPower += 15;  // Boost attack power for the next attack
    }

    @Override
    public void attack(Character target) {
        Random rand = new Random();
        int damage = attackPower + rand.nextInt(15);
        target.health -= damage;
        System.out.println(name + " attacks for " + damage + " damage!");
    }
}


