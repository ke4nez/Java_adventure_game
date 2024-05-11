package NPC;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC {
    private int position_x;
    private int position_y;


    private int speed;
    private Rectangle NPC_rectangle;

    private int NPC_rectangle_x;
    private int NPC_rectangle_y;
    private int NPC_rectangle_height = 48;
    private int NPC_rectangle_width = 48;

    private int NPC_rectangle_default_x;
    private int NPC_rectangle_default_y;
    private boolean collision = false;





    private BufferedImage up1;
    private BufferedImage up2;
    private BufferedImage down1;
    private BufferedImage down2;
    private BufferedImage left1;
    private BufferedImage left2;
    private BufferedImage right1;
    private BufferedImage right2;
    private BufferedImage stands;
    private BufferedImage stands2;
    private String direction;

    private  int spriteCounter = 0;
    private int spriteNum = 1;




    public void DefaultNPC() {
        this.position_x = 0;
        this.position_y = 0;
        this.speed = 3;
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

    public BufferedImage getStands() {
        return stands;
    }

    public void setStands(BufferedImage stands) {
        this.stands = stands;
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
        return collision;
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
}





