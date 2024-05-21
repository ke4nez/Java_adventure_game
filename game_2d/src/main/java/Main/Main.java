package Main;
import javax.swing.JFrame;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * The Main class serves as the entry point for the game application.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());



    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--enable-logging")) {
            LogManager.getLogManager().getLogger("").setLevel(Level.ALL);
            logger.info("Logging enabled");
        }else {
            LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
        }

        JFrame window = createGameWindow();
        startGame(window);
    }

    /**
     * Creates and configures the game window.
     *
     * @return The configured JFrame representing the game window.
     */
    private static JFrame createGameWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("my adventure");
        window.setResizable(false);

        Game_panel game_panel = new Game_panel();
        game_panel.set_game_panel();
        game_panel.setGame();

        window.add(game_panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        return window;
    }

    /**
     * Starts the game by retrieving the Game_panel from the window and initiating the game thread.
     *
     * @param window The JFrame representing the game window.
     */
    private static void startGame(JFrame window) {
        Game_panel game_panel = (Game_panel) window.getContentPane().getComponent(0);
        game_panel.start_game();
    }
}