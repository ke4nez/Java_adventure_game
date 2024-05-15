package Objects;

import Main.Game_panel;
import NPC.NPC;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game_Object {

    private Game_panel game_panel;
    private BufferedImage image;
    private String name;
    private boolean collision = true;
    private int position_x;
    private int position_y;

    private int index;

    private int Obj_rectangle_x = 0;
    private int Obj_rectangle_y = 0;
    private int  Obj_rectangle_width = 48;
    private int  Obj_rectangle_height = 48;

    private Rectangle object_rectangle = new Rectangle(0,0,48,48);

    private int object_rectangle_default_x = 0;
    private int object_rectangle_default_y = 0;




    private boolean ispickeble = false;
    private boolean isinteractable = false;

    //loading

    int object_type_number;


    public void paintObject(Graphics2D g2) {
            int screen_x = position_x - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
            int screen_y = position_y - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();

        if(        position_x + game_panel.getTile_size_x() > game_panel.getHero().getPosition_x() - game_panel.getHero().getScreen_x()
                && position_x - game_panel.getTile_size_x() < game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x()
                && position_y + game_panel.getTile_size_y() > game_panel.getHero().getPosition_y() - game_panel.getHero().getScreen_y()
                && position_y - game_panel.getTile_size_y() < game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y()
        ) {


            g2.drawImage(image, screen_x, screen_y, game_panel.getTile_size_x(), game_panel.getTile_size_y(), null);
        }
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

    public void setObject_rectangle_default_x(int object_rectangle_default_x) {
        this.object_rectangle_default_x = object_rectangle_default_x;
    }

    public int getObject_rectangle_default_y() {
        return object_rectangle_default_y;
    }

    public void setObject_rectangle_default_y(int object_rectangle_default_y) {
        this.object_rectangle_default_y = object_rectangle_default_y;
    }

    public Rectangle getObject_rectangle() {
        return object_rectangle;
    }

    public void setObject_rectangle(Rectangle object_rectangle) {
        this.object_rectangle = object_rectangle;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getObj_rectangle_x() {
        return Obj_rectangle_x;
    }

    public void setObj_rectangle_x(int obj_rectangle_x) {
        Obj_rectangle_x = obj_rectangle_x;
    }

    public int getObj_rectangle_y() {
        return Obj_rectangle_y;
    }

    public void setObj_rectangle_y(int obj_rectangle_y) {
        Obj_rectangle_y = obj_rectangle_y;
    }

    public int getObj_rectangle_width() {
        return Obj_rectangle_width;
    }

    public void setObj_rectangle_width(int obj_rectangle_width) {
        Obj_rectangle_width = obj_rectangle_width;
    }

    public int getObj_rectangle_height() {
        return Obj_rectangle_height;
    }

    public void setObj_rectangle_height(int obj_rectangle_height) {
        Obj_rectangle_height = obj_rectangle_height;
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
}

