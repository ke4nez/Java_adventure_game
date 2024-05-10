package Objects;

import Main.Game_panel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {

    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int position_x;
    private int position_y;

    private Game_panel game_panel;


    public void paintObject(Graphics2D g2) {
            int screen_x = position_x - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
            int screen_y = position_y - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();
            g2.drawImage(image,screen_x,screen_y,game_panel.getTile_size_x(), game_panel.getTile_size_y(),null );
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
}

