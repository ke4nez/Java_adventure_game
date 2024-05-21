/**
 * The Objects package is responsible for handling in-game objects,
 * including interactive elements, items, and non-player characters (NPCs).
 */
package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

/**
 * The Axe class represents an axe game object that can be picked up by the hero in the game.
 * It extends the Game_Object class and initializes the axe object with its properties.
 */
public class Axe extends Game_Object {

    /**
     * Constructs an Axe object and initializes its properties.
     *
     * @param game_panel The Game_panel instance associated with the axe object.
     */
    public Axe(Game_panel game_panel) {
        super(game_panel);
        this.setGame_panel(game_panel);
        this.setName("Axe");
        this.setIspickeble(true);
        this.setIsinteractable(false);

        try {
            // Load the image for the axe
            this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/axe.png")));
        } catch (Exception e) {
            System.out.println("Cannot load axe image");
        }
    }

    /**
     * Picks up the axe object and adds it to the hero's inventory if there is space.
     * Removes the axe object from the game panel if picked up successfully.
     *
     * @param i The index of the axe object in the game panel's object list.
     */
    public void pickup(int i) {
        if (!getGame_panel().getHero().checkIfInventoryisFull()) {
            getGame_panel().getHero().inventory.add(new Entity.Axe(getGame_panel()));
            getGame_panel().getObj().remove(i);
            String text = "You picked up Axe";
            getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                    getGame_panel().getGui().getYForCenterinGameMessage());
        }
    }
}
