package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Toolbox class provides image scaling method
 */
public class Toolbox {
    private static final Logger logger = Logger.getLogger(Toolbox.class.getName());

    /**
     * Scales a given BufferedImage to the specified width and height.
     *
     * @param image The original BufferedImage to be scaled.
     * @param width The desired width of the scaled image.
     * @param height The desired height of the scaled image.
     * @return The scaled BufferedImage, or null if the scaling operation fails.
     */
    public BufferedImage scale_image(BufferedImage image, int width, int height){
        try {
            BufferedImage scale_image = new BufferedImage(width,height, image.getType());
            Graphics2D g2 = scale_image.createGraphics();
            g2.drawImage(image, 0, 0, width, height, null);
            g2.dispose();
            return scale_image;

        }catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to scale image", e);
            return null;
        }
    }
}
