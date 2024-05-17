package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Axe extends Game_Object{

    public Axe(Game_panel game_panel){
        super();
        this.setGame_panel(game_panel);
        this.setName("Axe");
        this.setIspickeble(true);

        try{
            this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/axe.png")));

        }catch (Exception e) {
            System.out.println("cannot load axe image");
        }

    }
}
