public class Battle {
    Character character;
    Titan titan;

    public Battle(Character character, Titan titan) {
        this.character = character;
        this.titan = titan;
    }

    public void start() {
        System.out.println("Battle has started against " + titan.name);
        // Battle logic: Player chooses to attack or dodge
    }
}

