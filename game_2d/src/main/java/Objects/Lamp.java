package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Lamp extends Game_Object {

    public Lamp (Game_panel game_panel){
        this.setIndex(2);
        this.setGame_panel(game_panel);
        this.setName("Lamp");
        this.setCollision(true);

        try{
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/Lamp.png")));

        }catch (Exception e) {
            System.out.println("cannot load Lamp image");
        }

    }
}
