package NPC;


import Main.Game_controls;
import Main.Game_panel;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Hero extends NPC {

    Game_panel game_panel;
    Game_controls game_controls;
    BufferedImage image = null;




    public Hero(Game_panel game_panel ,Game_controls game_controls){
        this.game_controls = game_controls;
        this.game_panel = game_panel;
        setHero();
        getHeroImages();
    }

    public void setHero(){
        setPosition_x(100);
        setPosition_y(100);
        setSpeed(5);
        setDirection("down");
    }

    private void getHeroImages(){
       try{

          setUp1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_up_1.png")));
          setUp2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_up_2.png")));
          setLeft1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_left_1.png")) );
          setLeft2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_left_2.png")) );
          setRight1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_right_1.png")) );
          setRight2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_right_2.png")) );
          setDown1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_down_1.png")) );
          setDown2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_down_2.png")) );
          //setStands(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/hero_defoult.png")));

        }catch (IOException e){
            System.out.println("get hero images not working");
        }
    }

    public void updatehero(){
        if(game_controls.go_right || game_controls.go_down || game_controls.go_up || game_controls.go_left) {
            if (game_controls.go_right) {
                setPosition_x(getPosition_x() + getSpeed());
                setDirection("right");
            }
            if (game_controls.go_left) {
                setPosition_x(getPosition_x() - getSpeed());
                setDirection("left");
            }
            if (game_controls.go_up) {
                setPosition_y(getPosition_y() - getSpeed());
                setDirection("up");
            }
            if (game_controls.go_down) {
                setPosition_y(getPosition_y() + getSpeed());
                setDirection("down");
            }

            setSpriteCounter(getSpriteCounter() + 1);
            if (getSpriteCounter() > 30) {
                if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }

        //setDirection("stands");




    }
    public void painthero(Graphics2D g2) {

        switch(getDirection()){
            case "up":
                if(getSpriteNum() == 1){
                    image = getUp1();
                }
                if(getSpriteNum() == 2){
                    image = getUp2();
                }
                break;
            case "down":
                if(getSpriteNum()==1){
                    image = getDown1();
                }
                if(getSpriteNum()==2){
                    image = getDown2();
                }
                break;
            case "left":
                if(getSpriteNum()==1){
                    image = getLeft1();
                }
                if(getSpriteNum()==2){
                    image = getLeft2();
                }
                break;
            case "right":
                if(getSpriteNum()==1){
                    image = getRight1();
                }
                if(getSpriteNum()==2){
                    image = getRight2();
                }
                break;
            case"stands":
                image = getStands();

        }

        g2.drawImage(image,getPosition_x(),getPosition_y(), game_panel.getTile_size_x(), game_panel.getTile_size_y(), null);
    }





}