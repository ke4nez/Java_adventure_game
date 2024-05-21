/**
 * The Environment package is responsible for handling lighting in the game.
 */
package Enviroment;

import Main.Game_panel;

import java.awt.*;


/**
 * Manages game lighting.
 */
public class EnviromentManager {

    private Game_panel game_panel;
    private Lightning lightning;
    private final int base_vision_distance;
    private final int vision_distance_with_light_source;

    /**
     * Constructs an environment manager with the specified game panel.
     *
     * @param game_panel The game panel associated with this environment manager.
     */
    public EnviromentManager(Game_panel game_panel) {
        this.game_panel = game_panel;
        this.base_vision_distance = game_panel.getWindow_width() / 2;
        this.vision_distance_with_light_source = game_panel.getWindow_width();
        setup();
    }

    /**
     * Sets up the environment by initializing the lightning.
     */
    private void setup() {
        this.lightning = new Lightning(game_panel, base_vision_distance);
    }

    /**
     * Draws the environment, including the lightning.
     *
     * @param g2 The graphics context used for drawing.
     */
    public void draw(Graphics2D g2) {
        lightning.draw(g2);
    }

    /**
     * Sets the lightning for the environment.
     *
     * @param lightning The lightning object to be set.
     */
    public void setLightning(Lightning lightning) {
        this.lightning = lightning;
    }
    public int getBase_vision_distance() {
        return base_vision_distance;
    }
    public int getVision_distance_with_light_source() {
        return vision_distance_with_light_source;
    }

}
