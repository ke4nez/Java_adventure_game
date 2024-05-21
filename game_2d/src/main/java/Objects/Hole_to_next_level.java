/**
 * The Objects package is responsible for handling in-game objects,
 * including interactive elements, items, and non-player characters (NPCs).
 */
package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * The Hole_to_next_level class represents a hole object that allows the hero to progress to the next level in the game.
 * The hero can only interact with this hole if they have a lamp turned on.
 */
public class Hole_to_next_level extends Game_Object {

    /**
     * Constructs a Hole_to_next_level object with the specified Game_panel.
     *
     * @param game_panel The Game_panel instance associated with the game object.
     */
    public Hole_to_next_level(Game_panel game_panel) {
        super(game_panel);
        this.setGame_panel(game_panel);
        this.text = "";
        this.setName("Hole_to_next_level");
        this.setCollision(true);
        this.setIsinteractable(true);
        this.setIspickeble(false);

        try {
            this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/hole_to_next_level.png")));
        } catch (IOException e) {
            System.out.println("Cannot load hole image");
        }
    }

    /**
     * Interacts with the hole object. If the hero is holding a lamp that is turned on, the hero will progress to the next level.
     * Otherwise, a message is displayed indicating that a lamp is needed.
     */
    @Override
    public void interactObject() {
        if (game_panel.getHero().getItemInHeands() != null) {
            if (game_panel.getHero().getItemInHeands().getName().equals("Lamp_on")) {
                game_panel.getHero().setOn_level_number(game_panel.getHero().getOn_level_number() + 1);
                game_panel.getAssetManager().loadLevel(game_panel.getHero().getOn_level_number());
                TeleportPlayer();
                text = "You entered level " + game_panel.getHero().getOn_level_number();
                getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                        getGame_panel().getGui().getYForCenterinGameMessage());
            } else {
                displayLampNeededMessage();
            }
        } else {
            displayLampNeededMessage();
        }
    }

    /**
     * Displays a message indicating that a lamp is needed to enter the hole.
     */
    private void displayLampNeededMessage() {
        text = "It's too dark, I need a lamp";
        getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                getGame_panel().getGui().getYForCenterinGameMessage());
    }

    /**
     * Teleports the hero to the position of the "Hole_to_previous_level" object when progressing to the next level.
     */
    private void TeleportPlayer() {
        for (Game_Object obj : game_panel.getObj()) {
            if (obj.getName().equals("Hole_to_previous_level")) {
                game_panel.getHero().setPosition_x(obj.getPosition_x() - game_panel.getTile_size_x());
                game_panel.getHero().setPosition_y(obj.getPosition_y() - game_panel.getTile_size_y());
            }
        }
    }
}