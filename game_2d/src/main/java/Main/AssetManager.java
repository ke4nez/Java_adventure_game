package Main;

import NPC.NPC_stranger;
import Objects.Door;
import Objects.Game_Object;
import Objects.Lamp;

public class AssetManager {

    Game_panel game_panel;
    private Game_Object[] obj = new Game_Object[10];

    public AssetManager(Game_panel game_panel) {
        this.game_panel = game_panel;
        this.obj = game_panel.getObj();
    }

    public void setObjects() {

    }


    public void setNPCS(){
        game_panel.getNpcs()[0] = new NPC.NPC_stranger(game_panel);
        game_panel.getNpcs()[0].setPosition_x(10 * game_panel.getTile_size_x());
        game_panel.getNpcs()[0].setPosition_y(10 * game_panel.getTile_size_y());
    }
}
