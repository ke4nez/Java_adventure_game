package Main;
import javax.swing.JFrame;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {
        boolean loggingEnabled = isLoggingEnabled(args);

        // Konfigurace logování
        configureLogging(loggingEnabled);

        // Vytvoření okna pro hru
        JFrame window = createGameWindow();

        // Spuštění hry
        startGame(window);
    }

    private static boolean isLoggingEnabled(String[] args) {
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--enable-logging")) {
                return true;
            }
        }
        return false;
    }

    private static void configureLogging(boolean loggingEnabled) {
        Logger rootLogger = Logger.getLogger("");
        if (loggingEnabled) {
            rootLogger.setLevel(Level.ALL);
        } else {
            rootLogger.setLevel(Level.OFF);
        }
    }

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
    private static void startGame(JFrame window) {
        Game_panel game_panel = (Game_panel) window.getContentPane().getComponent(0);
        game_panel.start_game();
    }
}
