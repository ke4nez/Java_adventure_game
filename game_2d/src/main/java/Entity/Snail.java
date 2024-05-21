package Entity;

import Main.Game_panel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Represents a snail entity in the game.
 */
public class Snail extends Entity {

    private int counter = 1;

    /**
     * Constructs a snail entity with the specified game panel.
     *
     * @param game_panel The game panel associated with this snail entity.
     */
    public Snail(Game_panel game_panel) {
        super(game_panel);
        setSpeed(1);
        setNPC_rectangle_x(16);
        setNPC_rectangle_y(32);
        setNPC_rectangle_default_x(16);
        setNPC_rectangle_default_y(32);
        setNPC_rectangle_width(game_panel.getTile_size_x() / 3);
        setNPC_rectangle_height(game_panel.getTile_size_y() / 3);
        setName("Snail");
        setupNPCimages("Snail");
        setIspeakble(false);
    }

    /**
     * Sets the action of the snail entity, including random movement.
     */
    public void setAction() {
        if (counter == 1) {
            Random i = new Random();
            int x = i.nextInt(1, 10);
            if (x % 2 == 1) {
                setDirection("stands");
            }
            if (x == 4) {
                setDirection("left");
            }
            if (x == 5) {
                setDirection("right");
            }
        }
        counter++;
        if (counter > 90) {
            counter = 1;
        }
    }

    /**
     * Draws the snail entity on the game panel.
     *
     * @param g2          The graphics context used for drawing.
     * @param game_panel  The game panel on which to draw the snail entity.
     */
    public void draw(Graphics2D g2, Game_panel game_panel) {
        BufferedImage image = null;
        // Set screen position
        int screen_x = getPosition_x() - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
        int screen_y = getPosition_y() - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();

        // Not drawing if out of screen
        if (getPosition_x() + game_panel.getTile_size_x() > game_panel.getHero().getPosition_x() - game_panel.getHero().getScreen_x()
                && getPosition_x() - game_panel.getTile_size_x() < game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x()
                && getPosition_y() + game_panel.getTile_size_y() > game_panel.getHero().getPosition_y() - game_panel.getHero().getScreen_y()
                && getPosition_y() - game_panel.getTile_size_y() < game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y()
        ) {
            // Change sprite
            switch (getDirection()) {
                case "left":
                    if (getSpriteNum() == 1) {
                        image = getLeft1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getLeft2();
                    }
                    break;
                case "right":
                    if (getSpriteNum() == 1) {
                        image = getRight1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getRight2();
                    }
                    break;
                case "stands":
                    if (getSpriteNum() == 1) {
                        image = getStands1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getStands2();
                    }
                    break;
            }
            g2.drawImage(image, screen_x + game_panel.getTile_size_x() / 2, screen_y + game_panel.getTile_size_y() / 2, game_panel.getTile_size_x() / 2, game_panel.getTile_size_y() / 2, null);
        }
    }

}

