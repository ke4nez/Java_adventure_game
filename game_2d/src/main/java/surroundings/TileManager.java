package Surroundings;
import Main.Game_panel;
import Main.Toolbox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TileManager {

Game_panel game_panel;
private static final Logger logger = Logger.getLogger(TileManager.class.getName());
 private Tile[] tiles;
 private int[][] map;

public TileManager(Game_panel game_panel){
    this.game_panel = game_panel;
    setMap(new int[game_panel.getMax_world_col()][game_panel.getMax_world_row()]);
    setTiles(new Tile[64]);
    getTileImages();
    loadmap(1);

}

private void getTileImages(){
    try{
        setup(30,"water",true);
        setup(10,"trees",true);
        setup(11,"grass",false);
        setup(12,"forest_road_left",false);
        setup(13,"forest_road_right",false);
        setup(14,"forest_field",false);
        setup(15,"forest_rocks",false);
        setup(16,"forest_stick",false);
        setup(17,"forest_stick",false);
        setup(18,"tree",true);
        setup(20,"forest_swamp",false);
        setup(21,"forest_swamp_2",false);
        setup(22,"forest_4_swamps",false);
        setup(39,"darkness",false);
        setup(40,"dungeon_wall_top",true);
        setup(41,"dungeon_wall_bottom",true);
        setup(42,"dungeon_wall_left",true);
        setup(43,"dungeon_wall_right",true);
        setup(44,"dungeon_floor",false);
        setup(45,"dungeon_big_rock",false);
        logger.info("Tile images were loaded");
    }catch (Exception e){
        logger.log(Level.SEVERE, "Failed to load tile images", e);
    }
}
public void  setup (int index, String imagePath, boolean collision){
    Toolbox toolbox = new Toolbox();
    try {
        getTiles()[index] = new Tile();
        getTiles()[index].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/"+ imagePath +".png")));
        getTiles()[index].setImage(toolbox.scale_image(getTiles()[index].getImage(), game_panel.getTile_size_x(), game_panel.getTile_size_y()));
        getTiles()[index].setCollision(collision);
        logger.info("Tile setup completed for index " + index);
    }catch (IOException e){
        logger.log(Level.SEVERE, "Failed to setup tile at index " + index, e);
    }
}
public void loadmap(int level_number){
    try {
        InputStream is = getClass().getClassLoader().getResourceAsStream("Levels/Level_" + level_number + "/map_1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int col = 0;
        int row = 0;

        while (col < game_panel.getMax_world_col() && row < game_panel.getMax_world_row()) {

               String line = br.readLine();

            while (col < game_panel.getMax_world_col()) {
                String numbers[] = line.split(" ");
                int number = Integer.parseInt(numbers[col]);
                getMap()[col][row] = number;
                col++;
            }
            if (col == game_panel.getMax_world_col()) {
                col = 0;
                row++;
            }
        }
        br.close();
        logger.info("Map loaded successfully");
    }catch (Exception e){
        logger.log(Level.SEVERE, "Failed to load map", e);
    }
}

public void draw(Graphics2D g2){
    try {
        int world_col = 0;
        int world_row = 0;

        //CAMERA FUNCTION
        while (world_col < game_panel.getMax_world_col() && world_row < game_panel.getMax_world_row()) {
            int world_x = world_col * game_panel.getTile_size_x();
            int world_y = world_row * game_panel.getTile_size_y();
            int screen_x = world_x - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
            int screen_y = world_y - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();

        //IF TILES OF SCREEN DONT DRAW
            if (world_x + game_panel.getTile_size_x() * 2 > game_panel.getHero().getPosition_x() - game_panel.getHero().getScreen_x()
                    && world_x - game_panel.getTile_size_x() * 2 < game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x()
                    && world_y + game_panel.getTile_size_x() * 2 > game_panel.getHero().getPosition_y() - game_panel.getHero().getScreen_y()
                    && world_y - game_panel.getTile_size_x() * 2 < game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y()
            ) {
                g2.drawImage(getTiles()[getMap()[world_col][world_row]].getImage(), screen_x, screen_y, null);
            }
            world_col++;

            if (world_col == game_panel.getMax_world_col()) {
                world_col = 0;
                world_row++;

            }
        }
    } catch (Exception e) {
        logger.log(Level.SEVERE, "Failed to load map", e);
    }
}
    public Tile[] getTiles() {
        return tiles;
    }

    public Tile getTileByNumber(int x) {
        return tiles[x];
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public int[][] getMap() {
        return map;
    }

    public int getTileNumberFromMap(int x, int y) {
        return map[x][y];
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
}
