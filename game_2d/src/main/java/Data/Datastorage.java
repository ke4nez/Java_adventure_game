package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Datastorage implements Serializable {
    //PLAYER STATS
    int on_level;
int position_x;
int position_y;
    int level;
    int exp;
    int exp_to_next_level;

    //INVENTORY
    ArrayList<String> inventory_item_names = new ArrayList<>();
    int item_in_hands;

    //OBJECTS
    ArrayList<String> obj_names = new ArrayList<>();
    ArrayList<Integer> obj_position_x = new ArrayList<>();
    ArrayList<Integer> obj_position_y = new ArrayList<>();

    //NPC
    ArrayList<String> npc_names = new ArrayList<>();
    ArrayList<Integer> npc_position_x = new ArrayList<>();
    ArrayList<Integer> npc_position_y = new ArrayList<>();

    //Map
    int[][] map;
}
