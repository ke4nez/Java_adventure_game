package Main;

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
}
