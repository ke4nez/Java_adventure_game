import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Main.Game_panel;
import Objects.Axe;
import Entity.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Main.Game_controls;
import java.awt.Graphics2D;

public class PickupTest {

    private Game_panel game_panel;
    private Hero hero;
    private Game_controls game_controls;
    private Axe axe;

    @BeforeEach
    public void setUp() {
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

        // Adding the hero to the game panel
        game_panel.setHero(hero);
    }

    @Test
    public void testPickupAxe() {

        // Set up the axe
        axe = new Axe(game_panel);
        axe.setPosition_x(1);
        axe.setPosition_y(1);
        game_panel.getObj().add(axe);

        // Simulate interaction to pick up the axe
        game_controls.interaction = true;
        hero.updatehero();

        // Verify the axe is in the hero's inventory]
        assertSame("Axe", hero.getInventory().get(0).getName(), "Player should have picked up the axe");
    }

    @Test
    public void testFalsePickupAxe(){
        // Set up the axe
        axe = new Axe(game_panel);
        axe.setPosition_x(500);
        axe.setPosition_y(500);
        game_panel.getObj().add(axe);

        // Simulate interaction to pick up the axe
        game_controls.interaction = true;
        hero.updatehero();

        // Verify the axe is not in the hero's inventory
        assertEquals(0, hero.inventory.size(), "Player should have empty inventory");
    }

}
