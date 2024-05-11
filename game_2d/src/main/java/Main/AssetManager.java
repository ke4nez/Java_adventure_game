package Main;

import Objects.Door;
import Objects.Game_Object;
import Objects.Lamp;

public class AssetManager {

    Game_panel game_panel;
    private Game_Object[] obj = new Game_Object[10];

    public AssetManager(Game_panel game_panel){
        this.game_panel = game_panel;
        this.obj = game_panel.getObj();
    }

    public void setObjects(){
        obj[0] = new Lamp(game_panel);
        obj[0].setPosition_x(5 * game_panel.getTile_size_x());
        obj[0].setPosition_y(5 * game_panel.getTile_size_y());

        obj[1] = new Door(game_panel);
        obj[1].setPosition_x(7 * game_panel.getTile_size_x());
        obj[1].setPosition_y(7 * game_panel.getTile_size_y());

        System.out.println("USPECH");
        game_panel.setObj(obj);
    }

}
