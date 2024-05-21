import Entity.Hero;
import Entity.NPC_stranger;
import Main.Game_controls;
import Main.Game_panel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.awt.*;



public class InteractNPCTest {

    private Game_panel game_panel;
    private Game_controls game_controls;
    private Hero hero;
    private NPC_stranger stranger;



    @BeforeEach
   public void setup(){
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
        game_panel.setHero(hero);
    }
    @Test
   public void SpeakTest(){

        // Adding the NPC to the game panel
        stranger = new NPC_stranger(game_panel);
        stranger.setPosition_x(1);
        stranger.setPosition_y(1);
        stranger.setDialogue(1);
        game_panel.getNpcs().add(stranger);

        // Speak test
        game_controls.interaction = true;
        game_panel.setGameState(game_panel.getPlayState());
        assertEquals(game_panel.getGameState(), game_panel.getPlayState());
        game_panel.getHero().updatehero();
        assertEquals(game_panel.getGameState(), game_panel.getDialogState(), "Game should change game state to dialogue");
    }


    @Test
    public void FalseSpeakTest(){

        // Adding the NPC to the game panel
        stranger = new NPC_stranger(game_panel);
        stranger.setPosition_x(500);
        stranger.setPosition_y(500);
        stranger.setDialogue(1);
        game_panel.getNpcs().add(stranger);

        // Speak test
        game_controls.interaction = true;
        game_panel.setGameState(game_panel.getPlayState());
        assertEquals(game_panel.getGameState(), game_panel.getPlayState());
        game_panel.getHero().updatehero();
        assertEquals(game_panel.getGameState(), game_panel.getPlayState(), "Game should running without changes");
    }
}
