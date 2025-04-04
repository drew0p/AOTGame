public class Level {
    Character character;
    Titan titan;

    public Level(Character character, Titan titan) {
        this.character = character;
        this.titan = titan;
    }

    public void start() {
        System.out.println("Level Started: Fighting " + titan.name);
        Battle battle = new Battle(character, titan);
        battle.start();
        // Implement the rest of the level logic, including winning or losing
    }
}
