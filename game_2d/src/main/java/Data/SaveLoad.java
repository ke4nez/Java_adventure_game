package Data;

import Entity.*;
import Entity.Axe;
import Entity.Lamp_of;
import Main.Game_panel;
import Entity.Entity;
import Objects.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveLoad {
    private Game_panel game_panel;
    private static final Logger logger = Logger.getLogger(SaveLoad.class.getName());

    public SaveLoad(Game_panel game_panel) {
        this.game_panel = game_panel;
    }
    //SAVE CURRENT LEVEL
    public void save() {
        logger.info("Starting save process...");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("Save.dat")))) {
            Datastorage ds = new Datastorage();

            //STATS
            ds.position_x = game_panel.getHero().getPosition_x();
            ds.position_y= game_panel.getHero().getPosition_y();
            ds.level = game_panel.getHero().getLevel();
            ds.exp = game_panel.getHero().getExp();
            ds.exp_to_next_level = game_panel.getHero().getNextlevelexp();
            ds.on_level = game_panel.getHero().getOn_level_number();
            logger.info("Player stats were saved");

            //INVENTORY NAMES
            for(int i = 0; i < game_panel.getHero().inventory.size(); i++){
                    ds.inventory_item_names.add(game_panel.getHero().inventory.get(i).getName());
            }

            //ITEM IN HANDS INDEX
            ds.item_in_hands = game_panel.getHero().get_item_in_hands_inventory_index_slot();
            logger.info("Player inventory was saved");

            //OBJECTS
            for(int i =0;i <game_panel.getObj().size(); i++){
                if(game_panel.getObj().get(i) != null) {
                    ds.obj_names.add(game_panel.getObj().get(i).getName());
                    ds.obj_position_x.add(game_panel.getObj().get(i).getPosition_x());
                    ds.obj_position_y.add(game_panel.getObj().get(i).getPosition_y());
                }
            }
            logger.info("Level objects were saved");

            //NPCs
            for (int i = 0; i < game_panel.getNpcs().size(); i++) {
                if (game_panel.getNpcs().get(i) != null) {
                    ds.npc_names.add(game_panel.getNpcs().get(i).getName());
                    ds.npc_position_x.add(game_panel.getNpcs().get(i).getPosition_x());
                    ds.npc_position_y.add(game_panel.getNpcs().get(i).getPosition_y());
                }
            }
            logger.info("Level NPCs were saved");

            //MAP
            ds.map = game_panel.getTileManager().getMap();
            logger.info("Level map was saved");

            oos.writeObject(ds);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Save failed", e);
        }
    }
    //LOAD CURRENT LEVEL
    public void load() {
        logger.info("Starting load process...");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Save.dat")))) {
            Datastorage ds = (Datastorage) ois.readObject();

            //STATS
            game_panel.getHero().setPosition_x(ds.position_x);
            game_panel.getHero().setPosition_y(ds.position_y);
            game_panel.getHero().setExp(ds.exp);
            game_panel.getHero().setNextlevelexp(ds.exp_to_next_level);
            game_panel.getHero().setLevel(ds.level);
            game_panel.getHero().setOn_level_number(ds.on_level);
            logger.info("Player stats were loaded");

            //INVENTORY
            if(!ds.inventory_item_names.isEmpty()) {
                game_panel.getHero().inventory.clear();
                for (int i = 0; i < ds.inventory_item_names.size(); i++) {
                    if (i <= game_panel.getHero().getInventory_size()) {
                        game_panel.getHero().inventory.add(make_item(ds.inventory_item_names.get(i)));
                    }
                }
                //ITEM IN HANDS
                game_panel.getHero().setItemInHeands(game_panel.getHero().inventory.get(ds.item_in_hands));
                logger.info("Payer inventory was loaded");
            }

            //OBJECTS
            game_panel.getObj().clear();
            for (int i = 0; i < ds.obj_names.size(); i++) {
                    game_panel.getObj().add(make_object(ds.obj_names.get(i), ds.obj_position_x.get(i), ds.obj_position_y.get(i)));
            }
            logger.info("Objects were loaded");

            //NPC
            game_panel.getNpcs().clear();
            for (int i = 0; i < ds.npc_names.size(); i++) {
                    game_panel.getNpcs().add(make_NPC(ds.npc_names.get(i), ds.npc_position_x.get(i),ds.npc_position_y.get(i)));
            }
            logger.info("NPCs were loaded");


            //MAP
            game_panel.getTileManager().setMap(ds.map);
            logger.info("Map was loaded");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "load failed");
        }
    }
    public Entity make_item(String item_name){
        Entity item = null;

        switch (item_name){
            case "Axe":
                item = new Axe(game_panel);
                break;
            case "Lamp_on":
                item = new Lamp_on(game_panel);
                break;
            case "Lamp_of":
                item = new Lamp_of(game_panel);
                break;
            case "Bulp":
                item = new Blup(game_panel);
        }
        return item;
    }

    public Game_Object make_object(String object_name, int x, int y){
         Game_Object object = null;
        switch (object_name){
            case "Axe":
                object = new Objects.Axe(game_panel);
                object.setPosition_x(x);
                object.setPosition_y(y);
                break;
            case "Lamp_of":
                object = new Objects.Lamp_of(game_panel);
                object.setPosition_x(x);
                object.setPosition_y(y);
                break;
            case "Pond":
                object = new Objects.Pond(game_panel);
                object.setPosition_x(x);
                object.setPosition_y(y);
                break;
            case "Door":
                object = new Door(game_panel);
                object.setPosition_x(x);
                object.setPosition_y(y);
                break;
            case "Hole_to_next_level":
                object = new Hole_to_next_level(game_panel);
                object.setPosition_x(x);
                object.setPosition_y(y);
                break;
            case "Hole_to_previous_level":
                object = new Hole_to_previous_level(game_panel);
                object.setPosition_x(x);
                object.setPosition_y(y);
                break;
        }
        return object;
    }

    public Entity make_NPC(String name, int x, int y){
        Entity npc = null;
        switch (name){
            case "Stranger":
                npc = new NPC_stranger(game_panel);
                npc.setPosition_x(x);
                npc.setPosition_y(y);
                break;
            case "npc":
                //
                break;
        }
        return npc;
    }
}
