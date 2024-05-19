package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Toolbox {
    private static final Logger logger = Logger.getLogger(Toolbox.class.getName());

    //SCALE IMAGE TO WHATEVER SIZE YOU WANT
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
