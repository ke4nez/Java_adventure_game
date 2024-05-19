package Entity;


import Main.Game_panel;

public class Lamp_of extends Entity {

    Game_panel game_panel;
    public Lamp_of(Game_panel game_panel){
        super(game_panel);
        setName("Lamp_of");
        setupNPCimages("Lamp_of");
    }
}
