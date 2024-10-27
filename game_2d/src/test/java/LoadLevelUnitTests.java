
import Main.AssetManager;
import Main.Game_panel;
import Objects.Game_Object;
import Objects.Hole_to_previous_level;
import Surroundings.TileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.awt.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.*;

import static Main.AssetManager.logger;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class LoadLevelUnitTests {
    private Game_panel gamePanel;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
        gamePanel = new Game_panel();
        //TileManager mockTileManager = mock(TileManager.class);
        //AssetManager mockAsetManager = mock(AssetManager.class);
        //gamePanel.setTileManager(mockTileManager);
        //gamePanel.setAssetManager(mockAsetManager);

        // Mock GUI and Graphics2D
        Main.GUI mockGui = mock(Main.GUI.class);
        Graphics2D mockG2 = mock(Graphics2D.class);
        when(mockGui.getG2()).thenReturn(mockG2);

        Hole_to_previous_level holeToPreviousLevel = new Hole_to_previous_level(gamePanel);
        holeToPreviousLevel.setPosition_x(48);
        holeToPreviousLevel.setPosition_y(48);
        gamePanel.getHero().setPosition_x(48);
        gamePanel.getHero().setPosition_y(48);
    }

    @Test
    void testLoadLevel() {
        // gamePanel.getAssetManager().loadLevel(1);
       assertDoesNotThrow(() -> gamePanel.getAssetManager().loadLevel(1));
    }

    @Test
    void testLoadLevelwithBadNPCs(){
        assertDoesNotThrow(() -> gamePanel.getAssetManager().loadObjects(9));
        assertDoesNotThrow(() -> gamePanel.getTileManager().loadmap(9));
        assertThrows(RuntimeException.class, () -> gamePanel.getAssetManager().loadNPCs(9));
    }

    @Test
    void testLoadLevelwithBadObjects(){
        assertDoesNotThrow(() -> gamePanel.getAssetManager().loadNPCs(10));
        assertDoesNotThrow(() -> gamePanel.getTileManager().loadmap(10));
        assertThrows(RuntimeException.class, () -> gamePanel.getAssetManager().loadObjects(10));
    }

    @Test
    void testLoadLevelwithBadMap(){
        assertDoesNotThrow(() -> gamePanel.getAssetManager().loadObjects(11));
        assertDoesNotThrow(() -> gamePanel.getAssetManager().loadNPCs(11));
        assertThrows(RuntimeException.class, () -> gamePanel.getTileManager().loadmap(11));
    }
}

