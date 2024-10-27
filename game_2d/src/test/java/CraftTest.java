import Entity.Blup;
import Entity.Hero;
import Entity.Lamp_of;
import Entity.Lamp_on;
import Main.Game_panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//TESTING WORKING LAMP CRAFT
public class CraftTest {

    private Game_panel game_panel;
    private Hero hero;



    @BeforeEach
    public void setup(){
        game_panel = new Game_panel();

        // Mock GUI and Graphics2D
        Main.GUI mockGui = mock(Main.GUI.class);
        Graphics2D mockG2 = mock(Graphics2D.class);
        when(mockGui.getG2()).thenReturn(mockG2);
        game_panel.setGui(mockGui);

        // Initialize the hero with inventory
        hero = new Hero(game_panel, null);
        assertTrue(hero.inventory.isEmpty());
        hero.inventory.add(new Lamp_of(game_panel));
        hero.inventory.add(new Blup(game_panel));

        // Adding the hero to the game panel
        game_panel.setHero(hero);

    }
    @Test
   public void CraftTest(){

        //Crafting
        game_panel.getHero().craftItem(0,1);
        assertSame("Lamp_on", hero.inventory.get(0).getName(), "Lamp_on should be added to inventory");
        assertEquals(1, hero.inventory.size(), "Lamp_of and Blup should be removed from inventory and Lamp on is added, so inventory size should be 1");
    }
    @Test

    public void FalseCraftTest(){
        Lamp_on lamp_on = new Lamp_on(game_panel);

        //Crafting
        game_panel.getHero().craftItem(1,1);

        assertFalse(hero.inventory.contains(lamp_on), "Lamp_on should be added to inventory");
        assertEquals(2, hero.inventory.size(), "Lamp_of and Blup should be in inventory");
    }

}
