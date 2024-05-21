/**
 * The Objects package is responsible for handling in-game objects,
 * including interactive elements, items, and non-player characters (NPCs).
 */
package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

/**
 * The Lamp_of class represents a lamp object in the game.
 */
public class Lamp_of extends Game_Object {
    Game_panel game_panel;

    /**
     * Constructs a Lamp_of object with the specified Game_panel.
     *
     * @param game_panel The Game_panel instance associated with the lamp object.
     */
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

    /**
     * Picks up the lamp object from the game world and adds it to the hero's inventory.
     *
     * @param i The index of the lamp object in the list of game objects.
     */
    public void pickup(int i) {
        game_panel.getObj().remove(i);
        getGame_panel().getHero().inventory.add(new Entity.Lamp_of(game_panel));

        text = "You picked up Lamp";
        getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                getGame_panel().getGui().getYForCenterinGameMessage());
    }
}
