package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class End_chest extends Game_Object{
    public End_chest (Game_panel game_panel){
        super(game_panel);
        this.setGame_panel(game_panel);
        this.text = "";
        this.setName("End_chest");
        this.setCollision(true);
        this.setIsinteractable(true);

        try {
            this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/end_chest.png")));
        } catch (Exception e) {
            System.out.println("cannot load end_chest image");
        }
    }



    public void interactObject(){
       game_panel.setGameState(game_panel.getGameEndState());
    }
}
