package Objects;

import Entity.Blup;
import Main.Game_panel;

import javax.imageio.ImageIO;

public class Lamp_of extends Game_Object {
    Game_panel game_panel;
    public Lamp_of(Game_panel game_panel) {
        super(game_panel);
        this.game_panel = game_panel;
        this.setName("Lamp_of");
        setIspickeble(true);

        try {
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/lamp_of.png")));

        } catch (Exception e) {
            System.out.println("cannot load Lamp_of image");
        }

    }


    public void pickup(int i) {
        game_panel.getObj().remove(i);
        getGame_panel().getHero().inventory.add(new Entity.Lamp_of(game_panel));

        text = "You picked up Lamp";
        getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                getGame_panel().getGui().getYForCenterinGameMessage());
    }
}
