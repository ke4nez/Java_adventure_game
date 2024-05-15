package Main;

import NPC.NPC_stranger;
import Objects.*;

public class AssetManager {

    Game_panel game_panel;
    private Game_Object[] obj = new Game_Object[10];

    public AssetManager(Game_panel game_panel) {
        this.game_panel = game_panel;
        this.obj = game_panel.getObj();
    }

    public void setObjects() {
        game_panel.getObj()[0] = new Lamp_of(game_panel);
       game_panel.getObj()[0].setPosition_x(24 * game_panel.getTile_size_x());
        game_panel.getObj()[0].setPosition_y(32 * game_panel.getTile_size_y());
        game_panel.getObj()[1] = new Lamp_of(game_panel);
        game_panel.getObj()[1].setPosition_x(25 * game_panel.getTile_size_x());
        game_panel.getObj()[1].setPosition_y(32 * game_panel.getTile_size_y());
        game_panel.getObj()[2] = new Lamp_of(game_panel);
       game_panel.getObj()[2].setPosition_x(26 * game_panel.getTile_size_x());
       game_panel.getObj()[2].setPosition_y(32 * game_panel.getTile_size_y());

        game_panel.getObj()[3] = new Door(game_panel);
        game_panel.getObj()[3].setPosition_x(27 * game_panel.getTile_size_x());
        game_panel.getObj()[3].setPosition_y(32 * game_panel.getTile_size_y());

        game_panel.getObj()[4] = new Pond(game_panel);
        game_panel.getObj()[4].setPosition_x(28 * game_panel.getTile_size_x());
        game_panel.getObj()[4].setPosition_y(32 * game_panel.getTile_size_y());


        game_panel.getObj()[5] = new Lamp_of(game_panel);
        game_panel.getObj()[5].setPosition_x(29 * game_panel.getTile_size_x());
        game_panel.getObj()[5].setPosition_y(32 * game_panel.getTile_size_y());

        game_panel.getObj()[6] = new Axe (game_panel);
        game_panel.getObj()[6].setPosition_x(30 * game_panel.getTile_size_x());
        game_panel.getObj()[6].setPosition_y(32 * game_panel.getTile_size_y());


        game_panel.getObj()[7] = new Pond(game_panel);
        game_panel.getObj()[7].setPosition_x(31 * game_panel.getTile_size_x());
        game_panel.getObj()[7].setPosition_y(32 * game_panel.getTile_size_y());

        game_panel.getObj()[7] = new Hole (game_panel);
        game_panel.getObj()[7].setPosition_x(32 * game_panel.getTile_size_x());
        game_panel.getObj()[7].setPosition_y(32 * game_panel.getTile_size_y());




    }


    public void setNPCS(){
        game_panel.getNpcs()[0] = new NPC_stranger(game_panel);
        game_panel.getNpcs()[0].setPosition_x(10* game_panel.getTile_size_x());
        game_panel.getNpcs()[0].setPosition_y(10 * game_panel.getTile_size_y());
    }
}
