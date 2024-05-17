package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Datastorage implements Serializable {
    //PLAYER STATS

    int level;
    int exp;
    int exp_to_next_level;

    ArrayList<String> inventory_item_names = new ArrayList<>();

    int item_in_hands;
}
