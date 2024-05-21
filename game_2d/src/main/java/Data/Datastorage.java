/**
 * The Data package is responsible for saving and loading player progress information.
 */
package Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Datastorage class is used to store game state data for serialization.
 * It implements Serializable interface to allow saving and loading the game state.
 */
public class Datastorage implements Serializable {

    /**
     * The current level number the player is on.
     */
    protected int on_level;

    /**
     * The x position of the player.
     */
    protected int position_x;

    /**
     * The y position of the player.
     */
    protected int position_y;

    /**
     * The player's level.
     */
    protected int level;

    /**
     * The player's experience points.
     */
    protected int exp;

    /**
     * The experience points required for the next level.
     */
    protected  int exp_to_next_level;

    /**
     * List of item names in the player's inventory.
     */
    protected ArrayList<String> inventory_item_names = new ArrayList<>();

    /**
     * Index of the item currently held by the player.
     */
    protected int item_in_hands;

    /**
     * List of names of objects in the current level.
     */
    protected ArrayList<String> obj_names = new ArrayList<>();

    /**
     * List of x positions of objects in the current level.
     */
    protected ArrayList<Integer> obj_position_x = new ArrayList<>();

    /**
     * List of y positions of objects in the current level.
     */
    protected ArrayList<Integer> obj_position_y = new ArrayList<>();

    /**
     * List of names of NPCs in the current level.
     */
    protected ArrayList<String> npc_names = new ArrayList<>();

    /**
     * List of x positions of NPCs in the current level.
     */
    protected  ArrayList<Integer> npc_position_x = new ArrayList<>();

    /**
     * List of y positions of NPCs in the current level.
     */
    protected  ArrayList<Integer> npc_position_y = new ArrayList<>();

    /**
     * 2D array representing the current level's map.
     */
    int[][] map;
}