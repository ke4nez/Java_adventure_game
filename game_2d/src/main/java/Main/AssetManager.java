package Main;

import NPC.NPC_stranger;
import Objects.Door;
import Objects.Game_Object;
import NPC.Lamp_2;
import Objects.Lamp;

public class AssetManager {

    Game_panel game_panel;
    private Game_Object[] obj = new Game_Object[10];

    public AssetManager(Game_panel game_panel) {
        this.game_panel = game_panel;
        this.obj = game_panel.getObj();
    }

    public void setObjects() {
        game_panel.getObj()[0] = new Lamp(game_panel);
       game_panel.getObj()[0].setPosition_x(5 * game_panel.getTile_size_x());
        game_panel.getObj()[0].setPosition_y(5 * game_panel.getTile_size_y());
        game_panel.getObj()[1] = new Lamp(game_panel);
        game_panel.getObj()[1].setPosition_x(6 * game_panel.getTile_size_x());
        game_panel.getObj()[1].setPosition_y(6 * game_panel.getTile_size_y());
        game_panel.getObj()[2] = new Lamp(game_panel);
       game_panel.getObj()[2].setPosition_x(7 * game_panel.getTile_size_x());
       game_panel.getObj()[2].setPosition_y(7 * game_panel.getTile_size_y());

        game_panel.getObj()[3] = new Door(game_panel);
        game_panel.getObj()[3].setPosition_x(13 * game_panel.getTile_size_x());
        game_panel.getObj()[3].setPosition_y(13 * game_panel.getTile_size_y());



    }


    public void setNPCS(){
        game_panel.getNpcs()[0] = new NPC_stranger(game_panel);
        game_panel.getNpcs()[0].setPosition_x(10* game_panel.getTile_size_x());
        game_panel.getNpcs()[0].setPosition_y(10 * game_panel.getTile_size_y());
    }
}
