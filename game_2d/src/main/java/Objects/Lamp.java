package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Lamp extends Object{

    public Lamp (Game_panel game_panel){
        this.setGame_panel(game_panel);
        this.setName("Lamp");
        this.setPosition_x(500);
        this.setPosition_y(500);


        try{
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Hero/kvadratni.png")));

        }catch (Exception e) {
            System.out.println("cannot load Lamp image");
        }

    }








}
