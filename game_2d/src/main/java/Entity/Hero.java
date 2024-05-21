package Entity;


import Main.CollisionChecker;
import Main.Game_controls;
import Main.Game_panel;

import java.io.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the Hero entity in the game.
 * The Hero is controlled by the player and can move around the game world,
 * interact with objects and NPCs, pick up items, and perform various actions.
 */
public class Hero extends Entity {

    /**
     * The Game_panel instance associated with the Hero.
     */
    Game_panel game_panel;

    /**
     * The Game_controls instance for controlling the Hero.
     */
    Game_controls game_controls;

    /**
     * The x-coordinate of the Hero on the screen.
     */
    private int screen_x;

    /**
     * The y-coordinate of the Hero on the screen.
     */
    private int screen_y;

    /**
     * The CollisionChecker instance for handling collisions.
     */
    CollisionChecker collisionChecker;

    /**
     * The inventory of the Hero to store items.
     */
    public ArrayList<Entity> inventory = new ArrayList<>();

    /**
     * The maximum size of the Hero's inventory.
     */
    private final int inventory_size = 25;

    /**
     * The current level number of the Hero.
     */
    private int on_level_number = 1;

    /**
     * The last time the Hero interacted with an object.
     */
    private long lastInteractionTime = 0;

    /**
     * The cooldown duration for interactions.
     */
    private static final long INTERACTION_COOLDOWN = 1000;

    /**
     * The text message to be displayed.
     */
    String text = "";

    /**
     * The first index used in crafting.
     */
    private int index_1;

    /**
     * The second index used in crafting.
     */
    private int index_2;

    /**
     * Flag indicating whether the Hero is crafting.
     */
    boolean craft = false;

    /**
     * Constructs a new Hero object.
     *
     * @param game_panel     The Game_panel instance associated with the Hero.
     * @param game_controls  The Game_controls instance for controlling the Hero.
     */
    public Hero(Game_panel game_panel, Game_controls game_controls) {
        super(game_panel);
        this.game_controls = game_controls;
        this.game_panel = game_panel;
        this.setNPC_rectangle_x(8);
        this.setNPC_rectangle_y(16);
        this.setNPC_rectangle_default_x(8);
        this.setNPC_rectangle_default_y(16);
        this.setNPC_rectangle_width(game_panel.getTile_size_x());
        this.setNPC_rectangle_height(game_panel.getTile_size_y());
        this.setNPC_rectangle(new Rectangle(8, 16, getNPC_rectangle_width() / 2, getNPC_rectangle_height() / 2));
        collisionChecker = new CollisionChecker(game_panel);
        setDefaultStats();
        setHero();
        setupNPCimages("Hero");
    }

    /**
     * Sets the initial position and speed of the Hero.
     */
    private void setHero(){
        setPosition_x(36 * game_panel.getTile_size_x());
        setPosition_y(53 * game_panel.getTile_size_y());
        setSpeed(5);
        setScreen_x(game_panel.getWindow_width()/2 - game_panel.getTile_size_x());
        setScreen_y(game_panel.getWindow_height()/2 - game_panel.getTile_size_y());
    }

    /**
     * Sets the default statistics of the Hero.
     */
    private void setDefaultStats(){
        setLevel(1);
        setExp(1);
        setNextlevelexp(10);
        LoadStartItems();
    }

    /**
     * Updates the state of the Hero in the game loop.
     */
    public void updatehero() {
        if (game_controls.go_right || game_controls.go_down || game_controls.go_up || game_controls.go_left || game_controls.interaction) {
            //INDEXES FOR INTERACTIONS AND DIALOGUES
            int index = 99;
            int npc_collision_index = 99;
            if (game_controls.interaction) {
                //INTERACTION COOLDOWN;
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastInteractionTime > INTERACTION_COOLDOWN) {

                    setDirection("interaction");
                    setCollision(false);
                    index = collisionChecker.check_object(this);
                    npc_collision_index = collisionChecker.checknpc(this, game_panel.getNpcs());
                    //INTERACT OR PICKUP
                    if (index != 99){
                        if (game_panel.getObjFromObjects(index).isIspickeble()) {
                            pickup(index);
                        }
                        else if (game_panel.getObjFromObjects(index).isIsinteractable()) {
                            interactObject(index);
                        }
                    }
                    //SPEAK
                    if (npc_collision_index != 99) {
                        interactNPC(npc_collision_index);
                    }

                    //INTERACTION COOLDOWN;
                    game_controls.interaction = false;
                    lastInteractionTime = currentTime;
                }
            }


            //MOVEMENT
            if (game_controls.go_right) {
                setDirection("right");
                setCollision(false);
                collisionChecker.check_tile(this);
                collisionChecker.check_object(this);
                collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_x(getPosition_x() + getSpeed());
                }
            }

            //MOVEMENT
            if (game_controls.go_left) {

                setDirection("left");
                setCollision(false);
                collisionChecker.check_tile(this);
              collisionChecker.check_object(this);
                 npc_collision_index = collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_x(getPosition_x() - getSpeed());
                }
            }

            //MOVEMENT
            if (game_controls.go_up) {

                setDirection("up");
                setCollision(false);
                collisionChecker.check_tile(this);
                collisionChecker.check_object(this);
                 npc_collision_index = collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_y(getPosition_y() - getSpeed());
                }
            }

            //MOVEMENT
            if (game_controls.go_down) {

                setDirection("down");
                setCollision(false);
                collisionChecker.check_tile(this);
                collisionChecker.check_object(this);
                 npc_collision_index =  collisionChecker.checknpc(this,game_panel.getNpcs());
                if(isCollision() != true){
                    setPosition_y(getPosition_y() + getSpeed());
                }

            }
            //WALK ANIMATION
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

        //STANDING ANIMATION
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

    /**
     * Paints the Hero on the game screen and handles its animation.
     *
     * @param g2 The graphics context.
     */
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

    /**
     * Picks up an object at the specified index from the game world.
     *
     * @param i The index of the object to pick up.
     */
   private void pickup( int i){
        if(i != 99 && !checkIfInventoryisFull()){
            game_panel.getObj().get(i).pickup(i);
            }
    }

    /**
     * Interacts with an object at the specified index from the game world.
     *
     * @param index The index of the object to interact with.
     */
    private void interactObject(int index){
            if (index != 99) {
                game_panel.getObj().get(index).interactObject();
                }
    }

    /**
     * Loads starting items for the Hero from a file.
     */
    private void LoadStartItems(){
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

    /**
     * Checks if the Hero's inventory is full.
     *
     * @return True if the inventory is full, otherwise false.
     */
    public boolean checkIfInventoryisFull(){
        if(inventory.size()<inventory_size){return false;}
        else {
            text = "You inventory if full!!!";
            game_panel.getGui().addMessage(text,game_panel.getGui().getXfortextincenter(text),
                    game_panel.getGui().getYForCenterinGameMessage());
            return true;
        }
    }

    /**
     * Interacts with an NPC at the specified index from the game world.
     *
     * @param index The index of the NPC to interact with.
     */
    private void  interactNPC(int index){
        if(game_panel.getNpcs().get(index).isIspeakble()) {
            game_panel.setGameState(game_panel.getDialogState());
            game_panel.getNPCfromNPCs(index).speak();
        }
    }

    /**
     * Selects an item from the Hero's inventory.
     */
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

    /**
     * Crafts an item using two specified inventory indexes.
     *
     * @param index_1 The index of the first item in the inventory.
     * @param index_2 The index of the second item in the inventory.
     */
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

    /**
     * Checks if the Hero has enough experience to level up.
     */
    public void checkLevelUp(){
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