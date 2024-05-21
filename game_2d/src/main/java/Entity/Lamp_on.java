package Entity;

import Main.Game_panel;

/**
 * Represents a Lamp_on entity in the game.
 */
public class Lamp_on extends Entity {

    /**
     * Constructs a Lamp_on entity with the specified game panel.
     *
     * @param game_panel The game panel associated with this Lamp_on entity.
     */
    public Lamp_on(Game_panel game_panel) {
        super(game_panel);
        setName("Lamp_on");
        setupNPCimages("Lamp_on");
        setIslightsource(true);
    }
}