package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;
import java.awt.*;

public class Hole_to_previous_level extends Game_Object {

        public Hole_to_previous_level(Game_panel game_panel) {
            super(game_panel);
            this.setGame_panel(game_panel);
            this.text = "";
            this.setName("Hole_to_previous_level");
            this.setCollision(true);
            this.setIsinteractable(true);
            this.setIspickeble(false);
            try {
                this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/hole_to_previous_level.png")));
            } catch (Exception e) {
                System.out.println("cannot load hole image");
            }
        }



        public void interactObject(){
            game_panel.getHero().setOn_level_number(game_panel.getHero().getOn_level_number() - 1);
            game_panel.getAssetManager().loadLevel(game_panel.getHero().getOn_level_number());
            TeleportPayer();
            text = "You entered level " + game_panel.getHero().getOn_level_number();
            getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                    getGame_panel().getGui().getYForCenterinGameMessage());
        }


        public void TeleportPayer(){
            for(int i = 0; i < game_panel.getObj().size();i++){
                if(game_panel.getObj().get(i).getName() == "Hole_to_next_level"){
                    game_panel.getHero().setPosition_x(game_panel.getObj().get(i).getPosition_x() - game_panel.getTile_size_x());
                    game_panel.getHero().setPosition_y(game_panel.getObj().get(i).getPosition_y()- game_panel.getTile_size_y());
                }
            }
        }
    //DRAW BIG OBJECT
    public void paintObject(Graphics2D g2) {
        int screen_x = getPosition_x() - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
        int screen_y = getPosition_y() - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();

        if(        getPosition_x() + game_panel.getTile_size_x() > game_panel.getHero().getPosition_x() - game_panel.getHero().getScreen_x()
                && getPosition_x() - game_panel.getTile_size_x() < game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x()
                && getPosition_y() + game_panel.getTile_size_y() > game_panel.getHero().getPosition_y() - game_panel.getHero().getScreen_y()
                && getPosition_y() - game_panel.getTile_size_y() < game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y()
        ) {


            g2.drawImage(getImage(), screen_x, screen_y - game_panel.getTile_size_y()*2, game_panel.getTile_size_x(), game_panel.getTile_size_y()*3, null);
        }
    }

    }
