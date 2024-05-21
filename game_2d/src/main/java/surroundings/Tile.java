package Surroundings;

import java.awt.image.BufferedImage;

/**
 * The Tile class represents a tile in the game world.
 */
public class Tile {
private BufferedImage image;
private boolean collision = false;




    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean getIfNotPasseble() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
