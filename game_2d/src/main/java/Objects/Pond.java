package Objects;

import Entity.Blup;
import Main.Game_panel;

import javax.imageio.ImageIO;

public class Pond extends Game_Object{

    private boolean hasBulp = true;



    public Pond(Game_panel game_panel){
        super(game_panel);
        this.setGame_panel(game_panel);
            this.text = "";
            this.setName("Pond");
            this.setCollision(true);
            this.setIsinteractable(hasBulp);

            try{
                this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/pond.png")));

            }catch (Exception e) {
                System.out.println("cannot load door image");
            }

        }



        public void interactObject(){
           if(hasBulp && !getGame_panel().getHero().checkIfInventoryisFull()){
               getGame_panel().getHero().inventory.add(new Blup(getGame_panel()));
               hasBulp = false;
               game_panel.getHero().setExp(game_panel.getHero().getExp() + 5);
               text = "Here is bulp in this pond";
               getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                       getGame_panel().getGui().getYForCenterinGameMessage());
               game_panel.getHero().checkLevelUp();
           }
           else if(!hasBulp){
               text = "Pond is empty now";
               getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                       getGame_panel().getGui().getYForCenterinGameMessage());
           }
        }

    public boolean isHasBulp() {
        return hasBulp;
    }

    public void setHasBulp(boolean hasBulp) {
        this.hasBulp = hasBulp;
    }
}
