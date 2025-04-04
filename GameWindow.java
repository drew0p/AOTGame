import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameWindow {
    private JFrame frame;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JPanel panel;
    private JButton attackButton, dodgeButton;
    private Character chosenCharacter;
    private Titan currentTitan;

    public GameWindow() {
        // Set up the JFrame
        frame = new JFrame("Attack on Titan Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the window

        // Set up the JTextArea
        textArea = new JTextArea(10, 50);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);

        // Set up the buttons for player actions
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        attackButton = new JButton("Attack");
        dodgeButton = new JButton("Dodge");

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attack();
            }
        });

        dodgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodge();
            }
        });

        panel.add(attackButton);
        panel.add(dodgeButton);

        // Add components to frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        // Show the window
        frame.setVisible(true);
        startGame();
    }

    public void startGame() {
        // Prompt user to choose a character
        textArea.setText("Choose your character:\n");
        textArea.append("1. Eren\n");
        textArea.append("2. Levi\n");
        textArea.append("3. Mikasa\n");
        textArea.append("4. Armin\n");

        // Wait for user input
        JPanel choicePanel = new JPanel();
        JTextField choiceField = new JTextField(2);
        JButton confirmButton = new JButton("Confirm");

        choicePanel.add(choiceField);
        choicePanel.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = Integer.parseInt(choiceField.getText());

                switch (choice) {
                    case 1:
                        chosenCharacter = new Character("Eren", 7, 8, 100);
                        break;
                    case 2:
                        chosenCharacter = new Character("Levi", 10, 9, 100);
                        break;
                    case 3:
                        chosenCharacter = new Character("Mikasa", 9, 8, 100);
                        break;
                    case 4:
                        chosenCharacter = new Character("Armin", 6, 7, 100);
                        break;
                    default:
                        chosenCharacter = new Character("Eren", 7, 8, 100); // Default to Eren
                }

                // Set the character's health to 100 at the beginning of the first level
                chosenCharacter.health = 100;

                // Now choose a Titan
                currentTitan = new PureTitan();
                textArea.setText("You chose " + chosenCharacter.name + ".\nYou are facing a " + currentTitan.name + ".\n");
                textArea.append("Press 'Attack' to fight the Titan or 'Dodge' to try and avoid an attack.\n");
                frame.add(choicePanel, BorderLayout.NORTH);  // Remove choicePanel after selection
                frame.revalidate();
                frame.repaint();
            }
        });

        frame.add(choicePanel, BorderLayout.NORTH);
        frame.revalidate();
        frame.repaint();
    }

    public void attack() {
        Random rand = new Random();
        int attackChance = rand.nextInt(100);

        // Character's attack should have a higher chance of hitting
        if (attackChance < chosenCharacter.attackPower * 12) {  // Increased chance of attack success
            textArea.append(chosenCharacter.name + " attacks the " + currentTitan.name + "!\n");

            // Increased damage range for characters (Between 15 and 30 damage)
            int damage = chosenCharacter.attackPower + rand.nextInt(16) + 15;
            currentTitan.health -= damage;

            // Display Titan's updated health
            textArea.append(currentTitan.name + " health: " + currentTitan.health + "\n");

            // Check if the Titan is defeated
            if (currentTitan.health <= 0) {
                textArea.append("You defeated the " + currentTitan.name + "!\n");
                nextLevel();  // Proceed to the next level
            } else {
                titanAttack(); // Titan attacks back if still alive
            }
        } else {
            textArea.append(chosenCharacter.name + "'s attack missed!\n");
            titanAttack();  // Titan attacks back if player misses
        }
    }

    public void dodge() {
        Random rand = new Random();

        // Calculate dodge chance based on agility (0-100)
        int dodgeChance = rand.nextInt(100);  // Random number between 0 and 99

        // If dodgeChance is less than character's agility * 10, the dodge succeeds
        if (dodgeChance < chosenCharacter.agility * 10) {
            textArea.append(chosenCharacter.name + " successfully dodges the Titan's attack!\n");
        } else {
            textArea.append(chosenCharacter.name + " fails to dodge and is hit!\n");
            titanAttack();  // Titan attacks if dodge fails
        }
    }


    public void titanAttack() {
        Random rand = new Random();
        int damage = rand.nextInt(10) + 5;  // Reduced damage for Titan's attack (Between 5 and 15)

        chosenCharacter.health -= damage;  // Deduct health from the player's character

        // Display the Titan's attack damage
        textArea.append("Titan attacks " + chosenCharacter.name + " for " + damage + " damage!\n");

        // If the player's health reaches 0 or below, the character dies
        if (chosenCharacter.health <= 0) {
            chosenCharacter.health = 0;  // Ensure health doesn't go below 0
            textArea.append(chosenCharacter.name + " has been defeated!\n");
            gameOver();  // Stop the game and prevent further actions
        } else {
            // Display the remaining health of the character
            textArea.append(chosenCharacter.name + "'s remaining health: " + chosenCharacter.health + "\n");
        }
    }



    public void gameOver() {
        // Logic to stop the game or prevent moving to the next level
        textArea.append("Game Over! You have lost the game.\n");
        disableGameControls(); // Disable the buttons or controls
    }

    public void disableGameControls() {
        attackButton.setEnabled(false);  // Disable attack button
        dodgeButton.setEnabled(false);   // Disable dodge button
    }
    public void nextLevel() {
        // Simulate level progression (next Titan or game over)
        textArea.append("Prepare for the next level...\n");

        // Example: move to next level with a new Titan (change as needed)
        if (currentTitan instanceof PureTitan) {
            currentTitan = new BeastTitan();  // New Titan for the next level
            textArea.append("A Beast Titan approaches!\n");
        } else if (currentTitan instanceof BeastTitan) {
            currentTitan = new ColossalTitan();  // Next Titan
            textArea.append("The Colossal Titan approaches!\n");

            // Increase character health for final level to make the fight fairer
            chosenCharacter.health = 200;  // Set health for the final level
            updateHealthDisplay();  // Update UI if necessary
        } else {
            textArea.append("You've defeated the final Titan!\n");
            endGame();  // End game when all Titans are defeated
        }

        // Reset health for the player's character before the next level starts (for earlier levels)
        if (!(currentTitan instanceof ColossalTitan)) {
            chosenCharacter.health = 100;  // Reset character health for earlier levels
            updateHealthDisplay();  // Update UI if necessary
        }
    }

    // Update Health Display
    public void updateHealthDisplay() {
        // Clear existing health display to avoid duplication
        textArea.append(chosenCharacter.name + "'s health: " + chosenCharacter.health + "\n");
    }

    public void enableGameControls() {
        attackButton.setEnabled(true);  // Re-enable attack button
        dodgeButton.setEnabled(true);   // Re-enable dodge button
    }

    public void endGame() {
        // Display game over message
        textArea.append("Game Over!\n");
        attackButton.setEnabled(false);
        dodgeButton.setEnabled(false);
    }

    public static void main(String[] args) {
        // Create a new GameWindow
        new GameWindow();
    }
}



