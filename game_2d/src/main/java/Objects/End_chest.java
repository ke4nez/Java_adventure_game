/**
 * The Objects package is responsible for handling in-game objects,
 * including interactive elements, items, and non-player characters (NPCs).
 */
package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

/**
 * The End_chest class represents the final chest object in the game that can be interacted with.
 * Interacting with this chest sets the game state to the end state, signaling the end of the game.
 */
public class End_chest extends Game_Object {

    /**
     * Constructs an End_chest object and initializes its properties.
     *
     * @param game_panel The Game_panel instance associated with the end chest object.
     */
    public End_chest(Game_panel game_panel) {
        super(game_panel);
        this.setGame_panel(game_panel);
        this.text = "";
        this.setName("End_chest");
        this.setCollision(true);
        this.setIsinteractable(true);

        try {
            // Load the image for the end chest
            this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/end_chest.png")));
        } catch (Exception e) {
            System.out.println("Cannot load end_chest image");
        }
    }

    /**
     * Interacts with the end chest object.
     * When interacted with, it sets the game state to the end state.
     */
    public void interactObject() {
        game_panel.setGameState(game_panel.getGameEndState());
    }
}