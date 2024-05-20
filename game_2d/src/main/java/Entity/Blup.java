package Entity;

import Main.Game_panel;

/**
 * The Blup class represents a blup entity in the game.
 * It extends the Entity class and initializes specific properties for the blup.
 */
public class Blup extends Entity {

    /**
     * Constructor to initialize a Blup entity with a game panel.
     * Sets the name of the blup and loads its images.
     *
     * @param game_panel The game panel this blup belongs to.
     */
    public Blup(Game_panel game_panel) {
        super(game_panel);
        setName("Bulp");
        this.setupNPCimages("Bulp");
    }
}





