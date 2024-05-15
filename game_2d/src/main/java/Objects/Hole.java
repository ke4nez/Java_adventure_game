package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Hole extends Game_Object{

    public Hole (Game_panel game_panel){
        super();
        this.setGame_panel(game_panel);
        this.setName("Hole");
        this.setIsinteractable(true);

        try{
            this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/hole.png")));

        }catch (Exception e) {
            System.out.println("cannot load axe image");
        }

    }
}
