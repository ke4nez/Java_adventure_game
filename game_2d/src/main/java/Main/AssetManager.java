package Main;

import Entity.Entity;
import Entity.NPC_stranger;
import Entity.Snail;
import Objects.*;
import Surroundings.TileManager;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages assets and resources for the game.
 */
public class AssetManager {

    private Game_panel game_panel;
    public static final Logger logger = Logger.getLogger(AssetManager.class.getName());
    private TileManager tileManager;

    /**
     * Constructs an AssetManager with the specified game panel.
     *
     * @param game_panel The game panel associated with the asset manager.
     */
    public AssetManager(Game_panel game_panel) {
        this.game_panel = game_panel;
        this.tileManager = game_panel.getTileManager();
        loadLevel(1);
    }

    /**
     * Loads the specified level.
     *
     * @param level_number The number of the level to load.
     */
    public void loadLevel(int level_number) {
        logger.log(Level.INFO, "Loading new level");
        clearLevel();
        loadObjects(level_number);
        loadNPCs(level_number);
        tileManager.loadmap(level_number);
        logger.log(Level.INFO, "Level loaded");
    }

    /**
     * Loads objects for the specified level from a file.
     *
     * @param level_number The number of the level from which to load objects.
     */
    public void loadObjects(int level_number) {
        game_panel.getObj().clear();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Levels/Level_" + level_number + "/Objects.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                Game_Object new_object;
                if (parts.length == 3) {
                    String objectName = parts[0];
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);

                    switch (objectName) {
                        // Create and add specific game objects based on their names
                        case "Pond":
                            new_object = new Pond(game_panel);
                            new_object.setPosition_x(x * game_panel.getTile_size_x());
                            new_object.setPosition_y(y * game_panel.getTile_size_y());
                            game_panel.getObj().add(new_object);
                            break;
                        case "Axe":
                            new_object = new Axe(game_panel);
                            new_object.setPosition_x(x * game_panel.getTile_size_x());
                            new_object.setPosition_y(y * game_panel.getTile_size_y());
                            game_panel.getObj().add(new_object);
                            break;
                        case "Lamp_of":
                            new_object = new Lamp_of(game_panel);
                            new_object.setPosition_x(x * game_panel.getTile_size_x());
                            new_object.setPosition_y(y * game_panel.getTile_size_y());
                            game_panel.getObj().add(new_object);
                            break;
                        case"Door":
                            new_object = new Door(game_panel);
                            new_object.setPosition_x(x * game_panel.getTile_size_x());
                            new_object.setPosition_y(y * game_panel.getTile_size_y());
                            game_panel.getObj().add(new_object);
                            break;
                        case "Hole_to_next_level":
                            new_object = new Hole_to_next_level(game_panel);
                            new_object.setPosition_x(x * game_panel.getTile_size_x());
                            new_object.setPosition_y(y * game_panel.getTile_size_y());
                            game_panel.getObj().add(new_object);
                            break;
                        case "Hole_to_previous_level":
                            new_object = new Hole_to_previous_level(game_panel);
                            new_object.setPosition_x(x * game_panel.getTile_size_x());
                            new_object.setPosition_y(y * game_panel.getTile_size_y());
                            game_panel.getObj().add(new_object);
                            break;
                        case "End_chest":
                            new_object = new End_chest(game_panel);
                            new_object.setPosition_x(x * game_panel.getTile_size_x());
                            new_object.setPosition_y(y * game_panel.getTile_size_y());
                            game_panel.getObj().add(new_object);
                            break;
                        default:
                            throw new IOException("Invalid Objects name: "+ line);
                    }
                } else {
                    logger.log(Level.SEVERE, "Invalid line format in Objects.txt file: " + line);
                    throw new IOException("Invalid line format in Objects.txt file: "+ line);
                }
            }
            logger.log(Level.INFO, "Objects were loaded");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error reading Objects: " + e.getMessage(), e);
            throw new RuntimeException("Error reading Objects: " + e.getMessage(), e);
        }
    }

    /**
     * Loads NPCs for the specified level from a file.
     *
     * @param level_number The number of the level from which to load NPCs.
     */
    public void loadNPCs(int level_number) {
        game_panel.getNpcs().clear();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Levels/Level_" + level_number + "/Npcs.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            Entity npc;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String npcName = parts[0].trim();
                    int x = Integer.parseInt(parts[1].trim());
                    int y = Integer.parseInt(parts[2].trim());

                    switch (npcName) {
                        // Create and add specific NPCs based on their names
                        case "Stranger":
                            npc = new NPC_stranger(game_panel);
                            npc.setPosition_x(x * game_panel.getTile_size_x());
                            npc.setPosition_y(y * game_panel.getTile_size_y());
                            npc.setDialogue(level_number);
                            game_panel.getNpcs().add(npc);
                            break;
                        case "Snail":
                            npc = new Snail(game_panel);
                            npc.setPosition_x(x * game_panel.getTile_size_x());
                            npc.setPosition_y(y * game_panel.getTile_size_y());
                            npc.setDialogue(level_number);
                            game_panel.getNpcs().add(npc);
                            break;
                        default:
                            throw new IOException("Invalid NPC name: " + line);
                    }
                } else {
                    logger.log(Level.SEVERE, "Invalid line format in Npcs.txt file: " + line);
                }
            }
            logger.log(Level.INFO, "NPCs were loaded");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error reading file with NPCs: " + e.getMessage(), e);
            throw new RuntimeException("Error reading file with NPCs: " + e.getMessage(), e);
        }
    }

    /**
     * Clears the current level by removing objects and NPCs.
     */
    public void clearLevel() {
        game_panel.getObj().clear();
        game_panel.getNpcs().clear();
    }
}



