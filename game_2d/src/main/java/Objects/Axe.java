package Objects;

import Entity.Lamp_of;
import Main.Game_panel;

import javax.imageio.ImageIO;

public class Axe extends Game_Object{

    public Axe(Game_panel game_panel){
        super(game_panel);
        this.setGame_panel(game_panel);
        this.setName("Axe");
        this.setIspickeble(true);

        try{
            this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/axe.png")));

        }catch (Exception e) {
            System.out.println("cannot load axe image");
        }

    }


    public void pickup(int i){
        if(! getGame_panel().getHero().checkIfInventoryisFull()) {
                 getGame_panel().getHero().inventory.add(new Entity.Axe(getGame_panel()));
                 getGame_panel().getObj().remove(i);
            text = "You picked up Axe";
            getGame_panel().getGui().addMessage(text,getGame_panel().getGui().getXfortextincenter(text),
                    getGame_panel().getGui().getYForCenterinGameMessage());

    }
    }
}
