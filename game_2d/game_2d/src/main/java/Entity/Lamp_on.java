package Entity;

import Main.Game_panel;

public class Lamp_on extends Entity {

    public Lamp_on(Game_panel game_panel){
        super(game_panel);
        setName("Lamp_on");
        this.setupNPCimages("Lamp_on");
        this.setIslightsource(true);

    }
}