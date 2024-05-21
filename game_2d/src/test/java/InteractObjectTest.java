import Entity.Hero;
import Entity.Lamp_on;
import Main.Game_controls;
import Main.Game_panel;
import Objects.Hole_to_next_level;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InteractObjectTest {

    private Game_panel game_panel;
    private Hero hero;
    private Game_controls game_controls;
    private Hole_to_next_level holeToNextLevel;

    @Test
    public void InteractnextLevel(){

        game_panel = new Game_panel();
        game_controls = new Game_controls(game_panel);

        // Mock GUI and Graphics2D
        Main.GUI mockGui = mock(Main.GUI.class);
        Graphics2D mockG2 = mock(Graphics2D.class);
        when(mockGui.getG2()).thenReturn(mockG2);
        game_panel.setGui(mockGui);

        // Initialize the hero with the mocked game controls
        hero = new Hero(game_panel, game_controls);
        hero.setPosition_x(1);
        hero.setPosition_y(1);
        hero.setOn_level_number(1);
        hero.setItemInHeands(new Lamp_on(game_panel));

        // Adding the hero to the game panel
        game_panel.setHero(hero);

        // Set up next level hole
        holeToNextLevel= new Hole_to_next_level(game_panel);
        holeToNextLevel.setPosition_x(1);
        holeToNextLevel.setPosition_y(1);
        game_panel.getObj().add(holeToNextLevel);


        // Test
        game_controls.interaction = true;
        hero.updatehero();
        assertEquals(2, hero.getOn_level_number(), "Game should load next level");
    }



    @Test
    public void FalseInteractnextLevel(){

        game_panel = new Game_panel();
        game_controls = new Game_controls(game_panel);

        // Mock GUI and Graphics2D
        Main.GUI mockGui = mock(Main.GUI.class);
        Graphics2D mockG2 = mock(Graphics2D.class);
        when(mockGui.getG2()).thenReturn(mockG2);
        game_panel.setGui(mockGui);

        // Initialize the hero with the mocked game controls
        hero = new Hero(game_panel, game_controls);
        hero.setPosition_x(1);
        hero.setPosition_y(1);
        hero.setOn_level_number(1);

        // Adding the hero to the game panel
        game_panel.setHero(hero);

        // Set up next level hole
        holeToNextLevel= new Hole_to_next_level(game_panel);
        holeToNextLevel.setPosition_x(1);
        holeToNextLevel.setPosition_y(1);
        game_panel.getObj().add(holeToNextLevel);


        // Test
        game_controls.interaction = true;
        hero.updatehero();
        assertEquals(1, hero.getOn_level_number(), "Player should stay in the same level because he dont have lamp in hands");
    }
}
