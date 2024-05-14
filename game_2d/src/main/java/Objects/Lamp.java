package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Lamp extends Game_Object{
Game_panel game_panel;
    public Lamp (Game_panel game_panel){
        this.setGame_panel(game_panel);
        this.setName("Lamp");
        this.setCollision(true);
        setIsinteractable(false);
        setIspickeble(true);

        try{
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/lamp.png")));

        }catch (Exception e) {
            System.out.println("cannot load Lamp image");
        }

    }
}
