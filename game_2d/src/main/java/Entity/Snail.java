package Entity;

import Main.Game_panel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Snail extends Entity{
int counter =1;
    public Snail(Game_panel game_panel){
        super(game_panel);
        this.setSpeed(1);
        this.setNPC_rectangle_x(16);
        this.setNPC_rectangle_y(32);
        this.setNPC_rectangle_default_x(16);
        this.setNPC_rectangle_default_y(32);
        this.setNPC_rectangle_width(game_panel.getTile_size_x()/3);
        this.setNPC_rectangle_height(game_panel.getTile_size_y()/3);
        this.setName("Snail");
        this.setupNPCimages("Snail");
        this.setIspeakble(false);
    }

    public void setAction() {
        if (counter == 1) {
            Random i = new Random();
            int x = i.nextInt(1, 10);
            if (x % 2 == 1) {
                this.setDirection("stands");
            }
            if (x == 4) {
                this.setDirection("left");
            }
            if (x == 5) {
                this.setDirection("right");
            }
        }
        counter++;
        if (counter > 90) {
            counter = 1;
        }
    }

    public void draw(Graphics2D g2, Game_panel game_panel) {
        BufferedImage image = null;
        // SET SCREEN POSITION
        int screen_x = getPosition_x() - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
        int screen_y = getPosition_y() - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();

        //NOT DRAWING IF OUT OF SCREEN
        if (getPosition_x() + game_panel.getTile_size_x() > game_panel.getHero().getPosition_x() - game_panel.getHero().getScreen_x()
                && getPosition_x() - game_panel.getTile_size_x() < game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x()
                && getPosition_y() + game_panel.getTile_size_y() > game_panel.getHero().getPosition_y() - game_panel.getHero().getScreen_y()
                && getPosition_y() - game_panel.getTile_size_y() < game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y()
        ) {
            //CHANGE SPRITE
            switch (this.getDirection()) {
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
            g2.drawImage(image,screen_x+game_panel.getTile_size_x()/2,screen_y+game_panel.getTile_size_y()/2,game_panel.getTile_size_x()/2,game_panel.getTile_size_y()/2,null);
        }
    }

}

