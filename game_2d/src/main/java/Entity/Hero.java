package Entity;


import Main.CollisionChecker;
import Main.Game_controls;
import Main.Game_panel;

import java.io.*;

import Objects.Door;
import Objects.Pond;


import java.awt.*;
import java.util.ArrayList;

public class Hero extends Entity {

    Game_panel game_panel;
    Game_controls game_controls;


    private  int screen_x;
    private  int screen_y;
    CollisionChecker collisionChecker;

    public ArrayList<Entity> inventory = new ArrayList<>();
    private final int inventory_size = 25;

    private int on_level_number = 1;



    private long lastInteractionTime = 0;
    private static final long INTERACTION_COOLDOWN = 1000;




    //String for adding to addmessage method
    String text = "";

    //CRAFT

    private int index_1;
    private int index_2;
    boolean craft = false;




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
        setDefaultStats();
        setHero();
        setupNPCimages("Hero");


    }


    public void setHero(){
        setPosition_x(28 * game_panel.getTile_size_x());
        setPosition_y(26 * game_panel.getTile_size_y());
        setSpeed(5);
        setScreen_x(game_panel.getWindow_width()/2 - game_panel.getTile_size_x());
        setScreen_y(game_panel.getWindow_height()/2 - game_panel.getTile_size_y());
    }
    public void setDefaultStats(){
        setLevel(1);
        setExp(1);
        setNextlevelexp(10);
        LoadStartItems();
    }


    public void updatehero() {
        if (game_controls.go_right || game_controls.go_down || game_controls.go_up || game_controls.go_left || game_controls.interaction) {
            int index = 99;
            int npc_collision_index = 99;


            if (game_controls.interaction) {
                long currentTime = System.currentTimeMillis();

                if (currentTime - lastInteractionTime > INTERACTION_COOLDOWN) {
                    setDirection("interaction");
                    setCollision(false);
                    index = collisionChecker.check_object(this, true);
                    npc_collision_index = collisionChecker.checknpc(this, game_panel.getNpcs());

                    if (index != 99) {
                        if (game_panel.getObjFromObjects(index).isIsinteractable()) {
                            interactObject(index);
                        }
                        if (game_panel.getObjFromObjects(index).isIspickeble()) {
                            pickup(index);
                        }
                    }

                    if (npc_collision_index != 99) {
                        interactNPC(npc_collision_index);
                    }

                    // Сбрасываем флаг интеракции и запоминаем время последней интеракции
                    game_controls.interaction = false;
                    lastInteractionTime = currentTime;
                }
            }



            if (game_controls.go_right) {

                setDirection("right");
                setCollision(false);
                collisionChecker.check_tile(this);
                collisionChecker.check_object(this, true);
                collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_x(getPosition_x() + getSpeed());
                }
            }


            if (game_controls.go_left) {

                setDirection("left");
                setCollision(false);
                collisionChecker.check_tile(this);
              collisionChecker.check_object(this, true);
                 npc_collision_index = collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_x(getPosition_x() - getSpeed());
                }
            }


            if (game_controls.go_up) {

                setDirection("up");
                setCollision(false);
                collisionChecker.check_tile(this);
                collisionChecker.check_object(this, true);
                 npc_collision_index = collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_y(getPosition_y() - getSpeed());
                }
            }


            if (game_controls.go_down) {

                setDirection("down");
                setCollision(false);
                collisionChecker.check_tile(this);
                collisionChecker.check_object(this, true);
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



    public void pickup( int i){
        if(i != 99 && !checkIfInventoryisFull()){
            game_panel.getObjFromObjects(i).pickup(i);
            }
    }


    public void interactObject(int index){
            if (index != 99) {
                game_panel.getObj().get(index).interactObject();
                }
    }

    public void LoadStartItems(){
        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream("NPC/Hero/Start_items.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String itemName;
            Entity new_item;
            while ((itemName = br.readLine()) != null) {
             switch (itemName){
                 case "Axe":
                     inventory.add(new Axe(game_panel));
                     break;
                 case "Lamp_of":
                     inventory.add(new Lamp_of(game_panel));
                     break;
                 case "Bulp":
                     inventory.add(new Blup(game_panel));
                     break;
                 case "Lamp_on":
                     inventory.add(new Lamp_on(game_panel));
                     break;
             }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }


    public boolean checkIfInventoryisFull(){
        if(inventory.size()<inventory_size){return false;}
        else {
            text = "You inventory if full!!!";
            game_panel.getGui().addMessage(text,game_panel.getGui().getXfortextincenter(text),
                    game_panel.getGui().getYForCenterinGameMessage());
            return true;
        }
    }


    public void  interactNPC(int index){
        game_panel.setGameState(game_panel.getDialogState());
        game_panel.getNPCfromNPCs(index).speak();
    }

    //pick item from inventory
    public void selectitem(){

        int itemindex = game_panel.getGui().getindexofitem();
        if(itemindex < inventory.size()) {

            //take item in hands
            setItemInHeands(inventory.get(itemindex));

            //change lightning
            if( inventory.get(itemindex).isIslightsource()){
              game_panel.changelightning(game_panel.getEnviromentManager().getVision_distance_with_light_source());
            }
            else {
                game_panel.changelightning(game_panel.getEnviromentManager().getBase_vision_distance());
            }
        }

    }

    public void craftItem(int index_1, int index_2) {
        if (index_1 != index_2 && index_1 < inventory.size() && index_2 < inventory.size()) {
            if ((inventory.get(index_1).getName().equals("Lamp_of") && inventory.get(index_2).getName().equals("Bulp")) ||
                    (inventory.get(index_2).getName().equals("Lamp_of") && inventory.get(index_1).getName().equals("Bulp"))) {
                inventory.remove(index_1);
                System.out.println("removed first item");
                inventory.remove(index_2 > index_1 ? index_2 - 1 : index_2);
                System.out.println("removed second item");
                inventory.add(new Lamp_on(game_panel));
                setItemInHeands(inventory.get(inventory.size() - 1));
                text = "you crafted lamp";
                game_panel.getGui().addMessage(text, game_panel.getGui().getXfortextincenter(text),
                        game_panel.getGui().getYForCenterinGameMessage());
                System.out.println("you crafted lamp");
            }
         else {
            text = "Cant combine those items";
            game_panel.getGui().addMessage(text, game_panel.getGui().getXfortextincenter(text),
                    game_panel.getGui().getYForCenterinGameMessage());
        }
        }
    }

    public void checkLevelUp(){
        if(getExp() < getNextlevelexp()){
            //
        }
        if(getExp() > getNextlevelexp()){
            setLevel(getLevel() + 1);
            setNextlevelexp(getNextlevelexp() * 2);

            text = "Level up!";
            game_panel.getGui().addMessage(text,game_panel.getGui().getXfortextincenter(text),
                    game_panel.getGui().getYForCenterinGameMessage());

        }

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

    public ArrayList<Entity> getInventory() {
        return inventory;
    }

    public int get_item_in_hands_inventory_index_slot(){
        int slot = 0;

        for(int i = 0; i< inventory.size();i++) {

                if (game_panel.getHero().inventory.get(i) == game_panel.getHero().getItemInHeands()) {
                    slot = i;
                }

        }
        return slot;
    }



    public void setInventory(ArrayList<Entity> inventory) {
        this.inventory = inventory;
    }

    public int getInventory_size() {
        return inventory_size;
    }

    public int getIndex_1() {
        return index_1;
    }

    public void setIndex_1(int index_1) {
        this.index_1 = index_1;
    }

    public int getIndex_2() {
        return index_2;
    }

    public void setIndex_2(int index_2) {
        this.index_2 = index_2;
    }

    public int getOn_level_number() {
        return on_level_number;
    }

    public void setOn_level_number(int on_level_number) {
        this.on_level_number = on_level_number;
    }
}