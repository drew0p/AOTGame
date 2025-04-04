import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        // Available characters
        Character eren = new Character("Eren", 7, 8, 6);
        Character mikasa = new Character("Mikasa", 9, 7, 8);
        Character armin = new Character("Armin", 8, 7, 9);
        Character levi = new Character("Levi", 10, 9, 9);

        System.out.println("Choose a character: Eren, Mikasa, Armin, Levi");
        String choice = scanner.nextLine();
        Character chosenCharacter = null;

        switch (choice.toLowerCase()) {
            case "eren":
                chosenCharacter = eren;
                break;
            case "mikasa":
                chosenCharacter = mikasa;
                break;
            case "armin":
                chosenCharacter = armin;
                break;
            case "levi":
                chosenCharacter = levi;
                break;
            default:
                System.out.println("Invalid character. Game Over.");
                gameOver = true;
        }

        if (!gameOver) {
            // Start the game with different levels
            Level level1 = new Level(chosenCharacter, new PureTitan());
            level1.start();

            Level level2 = new Level(chosenCharacter, new BeastTitan());
            level2.start();

            Level level3 = new Level(chosenCharacter, new ColossalTitan());
            level3.start();
        }

        scanner.close();
    }
}

