public class Character {
    String name;
    int agility;
    int attackPower;
    int health;
    int maxHealth;  // Added maxHealth to reset health at the start of each level

    public Character(String name, int agility, int attackPower, int health) {
        this.name = name;
        this.agility = agility;
        this.attackPower = attackPower;
        this.health = health;
        this.maxHealth = health;  // Set maxHealth to the starting health
    }
}


