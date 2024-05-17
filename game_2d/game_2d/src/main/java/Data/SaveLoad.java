package Data;

import Entity.*;
import Main.Game_panel;
import Entity.Entity;

import java.io.*;

public class SaveLoad {
    private Game_panel game_panel;

    public SaveLoad(Game_panel game_panel) {
        this.game_panel = game_panel;
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("Save.dat")))) {
            Datastorage ds = new Datastorage();

            //STATS
            ds.level = game_panel.getHero().getLevel();
            ds.exp = game_panel.getHero().getExp();
            ds.exp_to_next_level = game_panel.getHero().getNextlevelexp();


            //INVENTORY NAMES
            for(int i = 0; i < game_panel.getHero().inventory.size(); i++){

                    ds.inventory_item_names.add(game_panel.getHero().inventory.get(i).getName());
            }

            //ITEM IN HANDS INDEX

            ds.item_in_hands = game_panel.getHero().get_item_in_hands_inventory_index_slot();



            oos.writeObject(ds);
        } catch (Exception e) {
            System.out.println("Save failed");
            e.printStackTrace();
        }
    }

    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Save.dat")))) {
            Datastorage ds = (Datastorage) ois.readObject();

            //STATS
            game_panel.getHero().setExp(ds.exp);
            game_panel.getHero().setNextlevelexp(ds.exp_to_next_level);
            game_panel.getHero().setLevel(ds.level);


            //INVENTORY

            for(int i = 0; i <ds.inventory_item_names.size(); i++){
                if(i <= game_panel.getHero().getInventory_size()) {
                    game_panel.getHero().inventory.add(get_item(ds.inventory_item_names.get(i)));
                }
            }


            //ITEM IN HANDS
            game_panel.getHero().setItemInHeands(game_panel.getHero().inventory.get(ds.item_in_hands));




        } catch (Exception e) {
            System.out.println("Can't load player progress");
            e.printStackTrace();
        }
    }




    public Entity get_item(String item_name){
        Entity object = null;

        switch (item_name){
            case "Axe":
                object = new Axe(game_panel);
                break;
            case "Lamp_on":
                object = new Lamp_on(game_panel);
                break;
            case "Lamp_of":
                object = new Lamp_of(game_panel);
                break;
            case "Bulp":
                object = new Blup(game_panel);
        }
        return object;
    }
}
