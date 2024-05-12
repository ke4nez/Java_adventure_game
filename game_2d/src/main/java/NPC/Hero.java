package NPC;


import Main.CollisionChecker;
import Main.Game_controls;
import Main.Game_panel;
import Objects.Door;
import Objects.Game_Object;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Hero extends NPC {

    Game_panel game_panel;
    Game_controls game_controls;
    BufferedImage image = null;

    private  int screen_x;
    private  int screen_y;
    CollisionChecker collisionChecker;




    public Hero(Game_panel game_panel ,Game_controls game_controls){
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
        getHeroImages();
    }

    public void setHero(){
        setPosition_x(64 * game_panel.getTile_size_x());
        setPosition_y(115 * game_panel.getTile_size_y());
        setSpeed(5);
        setDirection("stands");
        setScreen_x(game_panel.getWindow_width()/2 - game_panel.getTile_size_x());
        setScreen_y(game_panel.getWindow_height()/2 - game_panel.getTile_size_y());
    }

    private void getHeroImages(){
       try{

          setUp1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_up_1.png")));
          setUp2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_up_2.png")));
          setLeft1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_left_1.png")) );
          setLeft2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_left_2.png")) );
          setRight1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_right_1.png")) );
          setRight2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_right_2.png")) );
          setDown1(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/kvadratni.png")) );
          setDown2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/boy_down_2.png")) );
          setStands(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/stands.png")));
          setStands2(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/stands2.png")));

        }catch (IOException e){
            System.out.println("get hero images not working");
        }
    }

    public void updatehero(){
        if(game_controls.go_right || game_controls.go_down || game_controls.go_up || game_controls.go_left || game_controls.interaction) {
            int index = 99;

           if(game_controls.interaction) {

               setDirection("interaction");
               setCollision(false);

               index = collisionChecker.check_object(this, true);
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
           }
            if (game_controls.go_right) {

                setDirection("right");
                setCollision(false);
                collisionChecker.check_tile(this);
                System.out.print(collisionChecker.check_object(this, true));
                if(getCollision() != true){
                    setPosition_x(getPosition_x() + getSpeed());
                }
            }

            if (game_controls.go_left) {

                setDirection("left");
                setCollision(false);
                collisionChecker.check_tile(this);
                System.out.print(collisionChecker.check_object(this, true));
                if(getCollision() != true){
                    setPosition_x(getPosition_x() - getSpeed());
                }
            }
            if (game_controls.go_up) {

                setDirection("up");
                setCollision(false);
                collisionChecker.check_tile(this);
                System.out.print(collisionChecker.check_object(this, true));
                if(getCollision() != true){
                    setPosition_y(getPosition_y() - getSpeed());
                }
            }
            if (game_controls.go_down) {

                setDirection("down");
                setCollision(false);
                collisionChecker.check_tile(this);
               System.out.print(collisionChecker.check_object(this, true));
                if(getCollision() != true){
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


          /// WITH THIS CODE CHARACTER CANT WALK IN 2 WAYS AT THE SAME TIME


            //setCollision(false);
           // collisionChecker.check_tile(this);


           // if(getDirection() == "up"){
            //    if(getCollision() != true){
              //      setPosition_y(getPosition_y() - getSpeed());
             //   }
           // }

           // if(getDirection() == "right"){
           //     if(getCollision() != true){
           //         setPosition_x(getPosition_x() + getSpeed());
           //     }
           // }

           // if(getDirection() == "left"){
           //     if(getCollision() != true){
           //         setPosition_x(getPosition_x() - getSpeed());
           //     }
           // }


           // if(getDirection() == "down"){
            //    if(getCollision() != true){
            //        setPosition_y(getPosition_y() + getSpeed());
            //    }
           // }



        }


        if(!game_controls.go_right  && !game_controls.go_down && !game_controls.go_up && !game_controls.go_left){
            setDirection("stands");

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

                if(getSpriteNum()==1){
                    image = getStands();
                }
                if(getSpriteNum()==2){
                    image = getStands2();
                }
                break;

        }

        g2.drawImage(image, getScreen_x(), getScreen_y(), game_panel.getTile_size_x(), game_panel.getTile_size_y(), null);
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