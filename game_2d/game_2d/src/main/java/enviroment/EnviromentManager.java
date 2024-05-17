package enviroment;

import Main.Game_panel;

import java.awt.*;

public class EnviromentManager {

    Game_panel game_panel;
    private Lightning lightning;

     private int base_vision_distance;
     private int vision_distance_with_light_source;

     public EnviromentManager(Game_panel game_panel){
                this.game_panel = game_panel;
                this.setBase_vision_distance(game_panel.getWindow_width()/2);
                this.setVision_distance_with_light_source(game_panel.getWindow_width());
                setup();
            }

            public void setup(){

         setLightning(new Lightning(game_panel, getBase_vision_distance()));
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

    public int getBase_vision_distance() {
        return base_vision_distance;
    }

    public void setBase_vision_distance(int base_vision_distance) {
        this.base_vision_distance = base_vision_distance;
    }

    public int getVision_distance_with_light_source() {
        return vision_distance_with_light_source;
    }

    public void setVision_distance_with_light_source(int vision_distance_with_light_source) {
        this.vision_distance_with_light_source = vision_distance_with_light_source;
    }
}
