package Entity;

import Main.Game_panel;

import java.io.*;
import java.util.Random;

/**
 * Represents a stranger NPC entity in the game.
 */
public class NPC_stranger extends Entity {

    private int counter = 1; // Counter to set random direction to NPCs

    /**
     * Constructs a stranger NPC entity with the specified game panel.
     *
     * @param game_panel The game panel associated with this stranger NPC entity.
     */
    public NPC_stranger(Game_panel game_panel) {
        super(game_panel);
        setSpeed(1);
        setName("Stranger");
        setupNPCimages("Stranger");
        setDialogue(game_panel.getHero().getOn_level_number());
    }

    /**
     * Loads dialogues from the NPC folder in the level folder.
     *
     * @param level_number The level number from which to load the dialogues.
     */
    public void setDialogue(int level_number) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Levels/Level_" + level_number + "/Stranger/Dialogue.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                this.dialogues.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Initiates speaking by the stranger NPC.
     */
    public void speak() {
        super.speak();
    }

    /**
     * Sets the action of the stranger NPC, including random walking.
     */
    protected void setAction() {
        if (counter == 1) {
            Random i = new Random();
            int x = i.nextInt(1, 10);

            switch (x) {
                case 1:
                    setDirection("stands");
                    break;
                case 2:
                    setDirection("up");
                    break;
                case 3:
                    setDirection("down");
                    break;
                case 4:
                    setDirection("left");
                    break;
                case 5:
                    setDirection("right");
                    break;
            }
        }
        counter++;
        if (counter > 90) {
            counter = 1;
        }
    }
}