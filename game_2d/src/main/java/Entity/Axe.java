/**
 * The Entity package is responsible for classes representing NPCs or items in the inventory.
 */
package Entity;

import Main.Game_panel;

/**
 * The Axe class represents an axe entity in the game.
 * It extends the Entity class and initializes specific properties for the axe.
 */
public class Axe extends Entity {

    /**
     * Constructor to initialize an Axe entity with a game panel.
     * Sets the name of the axe and loads its images.
     *
     * @param game_panel The game panel this axe belongs to.
     */
    public Axe(Game_panel game_panel) {
        super(game_panel);
        setName("Axe");
        this.setupNPCimages("Axe");
    }
}