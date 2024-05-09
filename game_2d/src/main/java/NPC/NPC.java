package NPC;

import java.awt.image.BufferedImage;

public class NPC {
    private int position_x;
    private int position_y;


    private int speed;


    private BufferedImage up1;
    private BufferedImage up2;
    private BufferedImage down1;
    private BufferedImage down2;
    private BufferedImage left1;
    private BufferedImage left2;
    private BufferedImage right1;
    private BufferedImage right2;
    private BufferedImage stands;
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


}





