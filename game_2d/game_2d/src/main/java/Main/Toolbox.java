package Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Toolbox {

    public BufferedImage scale_image(BufferedImage image, int width, int height){
        BufferedImage scale_image = new BufferedImage(width,height, image.getType());
        Graphics2D g2 = scale_image.createGraphics();
        g2.drawImage(image, 0, 0, width, height, null);
        g2.dispose();
        return scale_image;
    }

}
