package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Lamp_of extends Game_Object{
Game_panel game_panel;
    public Lamp_of(Game_panel game_panel){
        this.setGame_panel(game_panel);
        this.setName("Lamp_of");
        setIspickeble(true);

        try{
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/lamp_of.png")));

        }catch (Exception e) {
            System.out.println("cannot load Lamp_of image");
        }

    }
}
