package NPC;


import Main.CollisionChecker;
import Main.Game_controls;
import Main.Game_panel;
import Main.Toolbox;
import Objects.Door;
import Objects.Game_Object;
import surroundings.Tile;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Hero extends NPC {

    Game_panel game_panel;
    Game_controls game_controls;


    private  int screen_x;
    private  int screen_y;
    CollisionChecker collisionChecker;




    public Hero(Game_panel game_panel ,Game_controls game_controls){
        super(game_panel);
        this.game_controls = game_controls;
        this.game_panel = game_panel;
        this.setNPC_rectangle_x(8);
        this.setNPC_rectangle_y(16);
        this.setNPC_rectangle_default_x(8);
        this.setNPC_rectangle_default_y(16);
        this.setNPC_rectangle_width(game_panel.getTile_size_x());
        this.setNPC_rectangle_height(game_panel.getTile_size_y());
        this.setNPC_rectangle(new Rectangle(8,16,getNPC_rectangle_width()/2,getNPC_rectangle_height()/2));

        collisionChecker = new CollisionChecker(game_panel);
        setHero();
        setupNPCimages("Hero");

    }


    public void setHero(){
        setPosition_x(16 * game_panel.getTile_size_x());
        setPosition_y(16 * game_panel.getTile_size_y());
        setSpeed(5);
        setScreen_x(game_panel.getWindow_width()/2 - game_panel.getTile_size_x());
        setScreen_y(game_panel.getWindow_height()/2 - game_panel.getTile_size_y());
    }


    public void updatehero(){
        if(game_controls.go_right || game_controls.go_down || game_controls.go_up || game_controls.go_left || game_controls.interaction) {
            int index = 99;
            int npc_collision_index = 99;

            if(game_controls.interaction) {

               setDirection("interaction");
               setCollision(false);
               index = collisionChecker.check_object(this, true);
               npc_collision_index = collisionChecker.checknpc(this,game_panel.getNpcs());

               if (index != 99) {

                   String obj_name = game_panel.getObjFromObjects(index).getName();
                   switch (obj_name) {
                       case "door": {
                           if (game_panel.getObjFromObjects(index).isIsinteractable()) {
                               if (game_panel.getObjFromObjects(index) instanceof Door) {
                                   if (((Door) game_panel.getObjFromObjects(index)).isopen() == true) {
                                       ((Door) game_panel.getObjFromObjects(index)).close();
                                   }
                                   if ((((Door) game_panel.getObjFromObjects(index)).isopen() == false)) {
                                       ((Door) game_panel.getObjFromObjects(index)).open();
                                   }
                               }
                           }

                           break;
                       }
                       case "Lamp":
                           break;

                   }
               }

               if(npc_collision_index != 99){
                   interactNPC(npc_collision_index);

               }

           }


            if (game_controls.go_right) {

                setDirection("right");
                setCollision(false);
                collisionChecker.check_tile(this);
                //  collisionChecker.check_object(this, true);
                collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_x(getPosition_x() + getSpeed());
                }
            }


            if (game_controls.go_left) {

                setDirection("left");
                setCollision(false);
                collisionChecker.check_tile(this);
              //  collisionChecker.check_object(this, true);
                 npc_collision_index = collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_x(getPosition_x() - getSpeed());
                }
            }


            if (game_controls.go_up) {

                setDirection("up");
                setCollision(false);
                collisionChecker.check_tile(this);
              //  collisionChecker.check_object(this, true);
                 npc_collision_index = collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_y(getPosition_y() - getSpeed());
                }
            }


            if (game_controls.go_down) {

                setDirection("down");
                setCollision(false);
                collisionChecker.check_tile(this);
              //  collisionChecker.check_object(this, true);
                 npc_collision_index =  collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_y(getPosition_y() + getSpeed());
                }

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


        if(!game_controls.go_right  && !game_controls.go_down && !game_controls.go_up && !game_controls.go_left){
            setDirection("stands");
            setCollision(false);
            setSpriteCounter(getSpriteCounter() + 1);
            if (getSpriteCounter() > 90) {
                if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }

        }


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

                if(getSpriteNum()==1){
                    image = getStands1();
                }
                if(getSpriteNum()==2){
                    image = getStands2();
                }
                break;

            case "inderaction":
                image = getDialogue_image();

        }

        g2.drawImage(image, getScreen_x(), getScreen_y(), null);
    }





    public void  interactNPC(int index){
        game_panel.setGameState(game_panel.getDialogState());
        game_panel.getNPCfromNPCs(index).speak();
    }


    public int getScreen_x() {
        return screen_x;
    }

    public void setScreen_x(int screen_x) {
        this.screen_x = screen_x;
    }

    public int getScreen_y() {
        return screen_y;
    }

    public void setScreen_y(int screen_y) {
        this.screen_y = screen_y;
    }
}