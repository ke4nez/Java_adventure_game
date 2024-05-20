package Entity;

import Main.Game_panel;
import Main.Toolbox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Entity implements Serializable {

    private Game_panel game_panel;
    private int position_x;
    private int position_y;
    private int speed = 3;


    //Lightning
    private boolean islightsource = false;


    //COLLISION
    private int NPC_rectangle_x = 0;
    private int NPC_rectangle_y = 0;
    private int NPC_rectangle_height = 48;
    private int NPC_rectangle_width = 48;
    private Rectangle NPC_rectangle = new Rectangle(NPC_rectangle_x, NPC_rectangle_y, NPC_rectangle_width, NPC_rectangle_height);
    private int NPC_rectangle_default_x = 0;
    private int NPC_rectangle_default_y = 0;
    private boolean collision = true;

    //DIALOGUE
    private boolean ispeakble = true;
    ArrayList<String> dialogues = new ArrayList<>();
    int dialogueIndex = 0;

    //STATS
    private int maxHealth;
    private int health;
    private int level;
    private int exp;

    private String name;
    private int nextlevelexp;
    private Entity itemInHeands;




    //IMAGES
    BufferedImage image = null;
    private BufferedImage up1;
    private BufferedImage up2;
    private BufferedImage down1;
    private BufferedImage down2;
    private BufferedImage left1;
    private BufferedImage left2;
    private BufferedImage right1;
   private BufferedImage right2;
    private BufferedImage stands1;
    private BufferedImage stands2;
   private BufferedImage dialogue_image;
   private BufferedImage interaction;
   private String direction = "stands";
   //SPRITE ANIMATION
    private int spriteCounter = 0;
    private int spriteNum = 1;


    public Entity(Game_panel game_panel) {
        this.game_panel = game_panel;
    }


    public void setAction() {
    }
    //UPDATE NPC STATUS IN GAME LOOP
    public void update() {
        setCollision(false);
        setAction();
        game_panel.getCollisionChecker().check_tile(this);
        game_panel.getCollisionChecker().check_object(this);
        game_panel.getCollisionChecker().checkhero(this, game_panel.getHero());

        //WALKING AROUND
        if (!isCollision()){

            switch (getDirection()) {
                case "up":
                    if (!isCollision()) {
                        this.position_y -= speed;
                    }
                    break;
                case "down":
                    if (!isCollision()) {
                        this.position_y += speed;
                    }
                    break;
                case "left":
                    if (!isCollision()) {
                        this.position_x -= speed;
                    }
                    break;
                case "right":
                    if (!isCollision()) {
                        this.position_x += speed;
                    }
                    break;
            }

        }
        //ANIMATION
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
    //LOAD IMAGES FROM NPS FOLDER
    public void setupNPCimages(String npc_name) {
        int scale = 0;
        Toolbox toolbox = new Toolbox();
        try {
                this.setUp1(toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/up_1.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale));

                this.setUp2(toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/up_2.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale));

                this.setDown1(toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/down_1.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale));

                this.setDown2(toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/down_2.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale));

                this.setLeft1((toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/left_1.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale)));

                this.setLeft2(toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/left_2.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale));

                this.setRight1(toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/right_1.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale));

                this.setRight2(toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/right_2.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale));

                this.setStands1(toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/stands_1.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale));

                this.setStands2(toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/stands_2.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale));

                this.setDialogue_image((toolbox.scale_image(ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPC/" + npc_name + "/dialogue_1.png")),
                        game_panel.getTile_size_x() + scale, game_panel.getTile_size_y() + scale)));

        } catch (IOException e) {
            System.out.println("Can not load tile image");
        }

    }
    public void draw(Graphics2D g2, Game_panel game_panel) {
        BufferedImage image = null;
        // SET SCREEN POSITION
        int screen_x = position_x - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
        int screen_y = position_y - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();

        //NOT DRAWING IF OUT OF SCREEN
        if (position_x + game_panel.getTile_size_x() > game_panel.getHero().getPosition_x() - game_panel.getHero().getScreen_x()
                && position_x - game_panel.getTile_size_x() < game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x()
                && position_y + game_panel.getTile_size_y() > game_panel.getHero().getPosition_y() - game_panel.getHero().getScreen_y()
                && position_y - game_panel.getTile_size_y() < game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y()
        ) {
            //CHANGE SPRITE
            switch (this.getDirection()) {
                case "up":
                    if (getSpriteNum() == 1) {
                        image = getUp1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getUp2();
                    }
                    break;
                case "down":
                    if (getSpriteNum() == 1) {
                        image = getDown1();
                    }
                    if (getSpriteNum() == 2) {
                        image = getDown2();
                    }
                    break;
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
                case "interaction":
                    image = getDialogue_image();
                    break;
            }
            //DRAW HERO
            g2.drawImage(image, screen_x, screen_y, null);
        }
    }

    //DIALOGUE
    public void speak() {
        //START FROM BEGINNING
        if (dialogueIndex >= dialogues.size()) {
            dialogueIndex = 0;
        }
        //NEXT LINE
        getGame_panel().getGui().setCurrentDialogue(dialogues.get(dialogueIndex));
        dialogueIndex++;
        //SET NPC SPRITE TO DIALOGUE
        switch (getGame_panel().getHero().getDirection()) {
            case "interaction":
                setDirection("interaction");
        }
    }


    public void setDialogue(int level_number){
        //
    }
public Game_panel getGame_panel(){
        return game_panel;
}
    public void setPosition_x(int x) {
        this.position_x = x;
    }

    public void setPosition_y(int y) {
        this.position_y = y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPosition_x() {
        return position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public int getSpeed() {
        return speed;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public BufferedImage getStands1() {
        return stands1;
    }

    public void setStands1(BufferedImage stands) {
        this.stands1 = stands;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }


    public Rectangle getNPC_rectangle() {
        return NPC_rectangle;
    }

    public void setNPC_rectangle(Rectangle NPC_collision) {
        this.NPC_rectangle = NPC_collision;
    }

    public boolean getCollision() {
        return isCollision();
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public BufferedImage getStands2() {
        return stands2;
    }

    public void setStands2(BufferedImage stands2) {
        this.stands2 = stands2;
    }

    public int getNPC_rectangle_default_x() {
        return NPC_rectangle_default_x;
    }

    public void setNPC_rectangle_default_x(int NPC_rectangle_default_x) {
        this.NPC_rectangle_default_x = NPC_rectangle_default_x;
    }

    public int getNPC_rectangle_default_y() {
        return NPC_rectangle_default_y;
    }

    public void setNPC_rectangle_default_y(int NPC_rectangle_default_y) {
        this.NPC_rectangle_default_y = NPC_rectangle_default_y;
    }

    public int getNPC_rectangle_x() {
        return NPC_rectangle_x;
    }

    public void setNPC_rectangle_x(int NPC_rectangle_x) {
        this.NPC_rectangle_x = NPC_rectangle_x;
    }

    public int getNPC_rectangle_y() {
        return NPC_rectangle_y;
    }

    public void setNPC_rectangle_y(int NPC_rectangle_y) {
        this.NPC_rectangle_y = NPC_rectangle_y;
    }

    public int getNPC_rectangle_height() {
        return NPC_rectangle_height;
    }

    public void setNPC_rectangle_height(int NPC_rectangle_height) {
        this.NPC_rectangle_height = NPC_rectangle_height;
    }

    public int getNPC_rectangle_width() {
        return NPC_rectangle_width;
    }

    public void setNPC_rectangle_width(int NPC_rectangle_width) {
        this.NPC_rectangle_width = NPC_rectangle_width;
    }

    public boolean isCollision() {
        return collision;
    }

    public BufferedImage getDialogue_image() {
        return dialogue_image;
    }

    public void setDialogue_image(BufferedImage dialogue_image) {
        this.dialogue_image = dialogue_image;
    }

    public BufferedImage getInteraction() {
        return interaction;
    }

    public void setInteraction(BufferedImage interaction) {
        this.interaction = interaction;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNextlevelexp() {
        return nextlevelexp;
    }

    public void setNextlevelexp(int nextlevelexp) {
        this.nextlevelexp = nextlevelexp;
    }

    public Entity getItemInHeands() {
        return itemInHeands;
    }

    public void setItemInHeands(Entity itemInHeands) {
        this.itemInHeands = itemInHeands;
        if(itemInHeands.islightsource){
            game_panel.changelightning(game_panel.getEnviromentManager().getVision_distance_with_light_source());
        }
        else {
            game_panel.changelightning(game_panel.getEnviromentManager().getBase_vision_distance());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIslightsource() {
        return islightsource;
    }

    public void setIslightsource(boolean islightsource) {
        this.islightsource = islightsource;
    }

    public boolean isIspeakble() {
        return ispeakble;
    }

    public void setIspeakble(boolean ispeakble) {
        this.ispeakble = ispeakble;
    }
}





