package Enviroment;

import Main.Game_panel;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents the lighting effect in the game.
 */
public class Lightning {

    private Game_panel game_panel;
    private BufferedImage darknessFilter;
    private int circleSize;

    /**
     * Constructs a lightning effect with the specified game panel and circle size.
     *
     * @param game_panel The game panel associated with the lightning effect.
     * @param circleSize The size of the circle representing the lighting effect.
     */
    public Lightning(Game_panel game_panel, int circleSize) {
        this.game_panel = game_panel;
        this.setCircleSize(circleSize);

        // Create a buffered image for the darkness filter
        darknessFilter = new BufferedImage(game_panel.getWindow_width(), game_panel.getWindow_height(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        // Get the center x and y of the light circle
        int centerX = game_panel.getHero().getScreen_x() + (game_panel.getTile_size_x()) / 2;
        int centerY = game_panel.getHero().getScreen_y() + (game_panel.getTile_size_y()) / 2;

        // Create color and fraction arrays for the gradient effect
        Color[] color = new Color[]{
                new Color(0, 0, 0, 25),
                new Color(0, 0, 0, 100),
                new Color(0, 0, 0, 130),
                new Color(0, 0, 0, 150),
                new Color(0, 0, 0, 165),
                new Color(0, 0, 0, 180),
                new Color(0, 0, 0, 195),
                new Color(0, 0, 0, 210),
                new Color(0, 0, 0, 220),
                new Color(0, 0, 0, 230),
                new Color(0, 0, 0, 240),
                new Color(0, 0, 0, 250)
        };
        float[] fraction = new float[]{
                0f, 0.4f, 0.5f, 0.6f, 0.65f, 0.7f, 0.75f, 0.8f, 0.85f, 0.9f, 0.95f, 1f
        };

        // Create a radial gradient paint for the lighting effect
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, circleSize / 2, fraction, color);

        // Set the gradient paint settings on the graphics context
        g2.setPaint(gPaint);
        g2.fillRect(0, 0, game_panel.getWindow_width(), game_panel.getWindow_height());
        g2.dispose();
    }

    /**
     * Draws the lightning effect on the specified graphics context.
     *
     * @param g2 The graphics context on which to draw the lightning effect.
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }

    /**
     * Sets the size of the circle representing the lighting effect.
     *
     * @param circleSize The size of the circle.
     */
    public void setCircleSize(int circleSize) {
        this.circleSize = circleSize;
    }
}

