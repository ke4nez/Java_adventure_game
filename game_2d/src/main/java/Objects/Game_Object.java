/**
 * The Objects package is responsible for handling in-game objects,
 * including interactive elements, items, and non-player characters (NPCs).
 */
package Objects;

import Main.Game_panel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Game_Object class represents a generic object within the game.
 * This class provides common properties and methods for various game objects.
 */
public class Game_Object {

    protected Game_panel game_panel;
    private BufferedImage image;
    private String name;
    protected String text;
    private boolean collision = true;
    private int position_x = 0;
    private int position_y = 0;
    private int index;
    private int Obj_rectangle_width = 48;
    private int Obj_rectangle_height = 48;
    private Rectangle object_rectangle = new Rectangle(0, 0, 48, 48);
    private int object_rectangle_default_x = 0;
    private int object_rectangle_default_y = 0;
    private boolean ispickeble = false;
    private boolean isinteractable = false;

    /**
     * Constructs a Game_Object with the specified Game_panel.
     *
     * @param gamePanel The Game_panel instance associated with the game object.
     */
    public Game_Object(Game_panel gamePanel) {
        this.game_panel = gamePanel;
    }

    /**
     * Paints the game object on the screen.
     *
     * @param g2 The Graphics2D context used for drawing the object.
     */
    public void paintObject(Graphics2D g2) {
        int screen_x = position_x - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
        int screen_y = position_y - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();

        if (position_x + game_panel.getTile_size_x() > game_panel.getHero().getPosition_x() - game_panel.getHero().getScreen_x()
                && position_x - game_panel.getTile_size_x() < game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x()
                && position_y + game_panel.getTile_size_y() > game_panel.getHero().getPosition_y() - game_panel.getHero().getScreen_y()
                && position_y - game_panel.getTile_size_y() < game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y()) {
            g2.drawImage(image, screen_x, screen_y, game_panel.getTile_size_x(), game_panel.getTile_size_y(), null);
        }
    }

    /**
     * Interacts with the game object.
     * This method is intended to be overridden by subclasses to provide specific interaction behavior.
     */
    public void interactObject() {
        // Default implementation does nothing
    }

    /**
     * Picks up the game object.
     * This method is intended to be overridden by subclasses to provide specific pickup behavior.
     *
     * @param i The index of the object to be picked up.
     */
    public void pickup(int i) {
        // Default implementation does nothing
    }

        public BufferedImage getImage () {
            return image;
        }

        public void setImage (BufferedImage image){
            this.image = image;
        }

        public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public boolean isCollision () {
            return collision;
        }

        public void setCollision ( boolean collision){
            this.collision = collision;
        }

        public int getPosition_x () {
            return position_x;
        }

        public void setPosition_x ( int position_x){
            this.position_x = position_x;
        }

        public int getPosition_y () {
            return position_y;
        }

        public void setPosition_y ( int position_y){
            this.position_y = position_y;
        }

    public Game_panel getGame_panel() {
        return game_panel;
    }

    public void setGame_panel(Game_panel game_panel) {
        this.game_panel = game_panel;
    }

    public int getObject_rectangle_default_x() {
        return object_rectangle_default_x;
    }
    public int getObject_rectangle_default_y() {
        return object_rectangle_default_y;
    }

    public Rectangle getObject_rectangle() {
        return object_rectangle;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getObj_rectangle_width() {
        return Obj_rectangle_width;
    }
    public int getObj_rectangle_height() {
        return Obj_rectangle_height;
    }

    public boolean isIspickeble() {
        return ispickeble;
    }

    public void setIspickeble(boolean ispickeble) {
        this.ispickeble = ispickeble;
    }

    public boolean isIsinteractable() {
        return isinteractable;
    }

    public void setIsinteractable(boolean isinteractable) {
        this.isinteractable = isinteractable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

