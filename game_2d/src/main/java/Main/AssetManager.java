package Main;

import Entity.Entity;
import Entity.NPC_stranger;
import Objects.*;
import Surroundings.TileManager;

import java.io.*;
import java.util.ArrayList;

public class AssetManager {

    Game_panel game_panel;
    TileManager tileManager;

    public AssetManager(Game_panel game_panel) {
        this.game_panel = game_panel;
        this.tileManager = game_panel.getTileManager();
        loadLevel(1);
    }
public void loadLevel(int level_number){
        clearLevel();
        loadObjects(level_number);
        loadNPCs(level_number);
        tileManager.loadmap(level_number);
}
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
                            case "Door":
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
                        }
                    } else {
                        System.err.println("Ошибка: Неверный формат строки в файле объектов: " + line);
                    }
                }
            } catch(IOException e){
                System.err.println("Ошибка при чтении файла: " + e.getMessage());
            }

    }
    public void loadNPCs(int level_number){
        game_panel.getNpcs().clear();
        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream("Levels/Level_" + level_number+"/Npcs.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            Entity npc;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String npcName = parts[0].trim();
                    int x = Integer.parseInt(parts[1].trim());
                    int y = Integer.parseInt(parts[2].trim());

                    switch (npcName){
                        case "Stranger":
                            npc = new NPC_stranger(game_panel);
                            npc.setPosition_x(x * game_panel.getTile_size_x());
                            npc.setPosition_y(y * game_panel.getTile_size_y());
                            npc.setDialogue(level_number);
                            game_panel.getNpcs().add(npc);
                            break;
                        case "npc_name":
                            //
                            break;
                    }
                } else {
                    System.err.println("Неверный формат строки: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка преобразования числа: " + e.getMessage());
        }
    }
    public void clearLevel(){
       game_panel.getObj().clear();
       game_panel.getNpcs().clear();
    }


}



