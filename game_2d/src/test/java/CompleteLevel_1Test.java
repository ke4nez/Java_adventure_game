
import Entity.Hero;
import Main.Game_controls;
import Main.Game_panel;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;



public class CompleteLevel_1Test {
    private Game_panel game_panel;
    private Hero hero;
    private Game_controls game_controls;

    @Test
    public void Level_1_test(){
        game_panel = new Game_panel();
        game_controls = new Game_controls(game_panel);
        hero = new Hero(game_panel, game_controls);
        game_panel.setHero(hero);


        // Mock GUI and Graphics2D
        Main.GUI mockGui = mock(Main.GUI.class);
        Graphics2D mockG2 = mock(Graphics2D.class);
        when(mockGui.getG2()).thenReturn(mockG2);
        game_panel.setGui(mockGui);

        // Adding the hero to the game panel
        game_panel.setHero(hero);

        // Check that Axe was loaded correctly
        assertSame("Axe", game_panel.getObj().get(0).getName(), "axe should be first item on the map");

        // Take hero next to axe
        game_panel.getHero().setPosition_x(24 * game_panel.getTile_size_x());
        game_panel.getHero().setPosition_y(51 * game_panel.getTile_size_y());

        // Pck up
        game_controls.interaction = true;
        hero.updatehero();
        assertNotSame("Axe", game_panel.getObj().get(0).getName());
        assertSame("Axe", hero.getInventory().get(0).getName(), "Player should have picked up the axe");
        game_controls.interaction = false;

        // Hero next to the door
        assertSame("Door", game_panel.getObj().get(6).getName());
        game_panel.getHero().setPosition_x(27 * game_panel.getTile_size_x());
        game_panel.getHero().setPosition_y(30 * game_panel.getTile_size_y());
        // Take axe in hands
        assertSame(game_panel.getHero().getItemInHeands(), null);;
        game_panel.getHero().setItemInHeands(game_panel.getHero().inventory.get(0));
        assertSame("Axe",game_panel.getHero().getItemInHeands().getName(),"Player take axe in hands");
        game_controls.interaction = true;
        hero.updatehero();
        assertEquals(false, game_panel.getObj().get(6).isCollision(),"Door now open");


        game_panel.getHero().setPosition_x(27 * game_panel.getTile_size_x());
        game_panel.getHero().setPosition_y(52 * game_panel.getTile_size_y());
        game_controls.interaction = true;
        hero.updatehero();
        assertEquals("Bulp", hero.getInventory().get(1).getName());

        game_panel.getHero().setPosition_x(24 * game_panel.getTile_size_x());
        game_panel.getHero().setPosition_y(24 * game_panel.getTile_size_y());
        game_controls.interaction = true;
        hero.updatehero();
        assertEquals("Lamp_of", hero.getInventory().get(2).getName());

        game_panel.getHero().craftItem(1,2);
        hero.setItemInHeands(hero.inventory.get(1));
        assertSame("Axe", hero.getInventory().get(0).getName());
        assertSame("Lamp_on", hero.getInventory().get(1).getName());
        assertSame("Lamp_on",hero.getItemInHeands().getName());
        assertEquals(2, hero.inventory.size(), "Lamp_off and Bulp should be removed from inventory and Lamp on is added, so inventory size should be 1");

        game_panel.getHero().setPosition_x(21 * game_panel.getTile_size_x());
        game_panel.getHero().setPosition_y(16 * game_panel.getTile_size_y());
        assertSame(1,game_panel.getHero().getOn_level_number());
        game_controls.interaction = true;
        hero.updatehero();
        assertSame(2,game_panel.getHero().getOn_level_number());
    }
}
