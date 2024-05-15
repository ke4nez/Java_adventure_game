package enviroment;

import Main.Game_panel;

import java.awt.*;

public class EnviromentManager {

    Game_panel game_panel;
            private Lightning lightning;

            public EnviromentManager(Game_panel game_panel){
                this.game_panel = game_panel;
            }
            public void setup(){
                setLightning(new Lightning(game_panel,game_panel.getWindow_width()));
            }




    public void draw(Graphics2D g2){
        getLightning().draw(g2);
    }

    public Lightning getLightning() {
        return lightning;
    }

    public void setLightning(Lightning lightning) {
        this.lightning = lightning;
    }
}
