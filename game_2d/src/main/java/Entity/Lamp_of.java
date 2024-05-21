package Entity;

import Main.Game_panel;

/**
 * Represents a Lamp_of entity in the game.
 */
public class Lamp_of extends Entity {

    private Game_panel game_panel;

    /**
     * Constructs a Lamp_of entity with the specified game panel.
     *
     * @param game_panel The game panel associated with this Lamp_of entity.
     */
    public Lamp_of(Game_panel game_panel) {
        super(game_panel);
        setName("Lamp_of");
        setupNPCimages("Lamp_of");
    }
}
