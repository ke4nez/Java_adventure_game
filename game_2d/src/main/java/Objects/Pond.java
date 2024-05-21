/**
 * The Objects package is responsible for handling in-game objects,
 * including interactive elements, items, and non-player characters (NPCs).
 */
package Objects;

import Entity.Blup;
import Main.Game_panel;

import javax.imageio.ImageIO;

/**
 * The Pond class represents a pond object in the game.
 */
public class Pond extends Game_Object{

    private boolean hasBulp = true;


    /**
     * Constructs a Pond object with the specified Game_panel.
     *
     * @param game_panel The Game_panel instance associated with the pond object.
     */
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

    /**
     * Handles the interaction with the pond object.
     * If the pond has a bulb and the hero's inventory is not full, adds a bulb to the hero's inventory,
     * sets the pond's bulb status to false, increases the hero's experience, and checks for level up.
     * If the pond is empty, displays a message indicating that the pond is empty.
     */
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
}
