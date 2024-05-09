package surroundings;

import Main.Game_panel;
import NPC.Hero;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TileManager {

Game_panel game_panel;
Tile[] tiles;
int map[][];

public TileManager(Game_panel game_panel){
    this.game_panel = game_panel;
    map = new int[game_panel.getMax_world_col()][game_panel.getMax_world_row()];
    tiles = new Tile[64];
    getTileImages();
    loadmap("Maps/map_1.txt");

}

private void getTileImages(){
    try{
        tiles[0] = new Tile();
        tiles[0].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/water.png")));

        tiles[1] = new Tile();
        tiles[1].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/grass.png")));

        tiles[2] = new Tile();
        tiles[2].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/sand.png")));

        tiles[3] = new Tile();
        tiles[3].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/bricks.png")));

        tiles[4] = new Tile();
        tiles[4].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/leafs.png")));

        tiles[5] = new Tile();
        tiles[5].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("")));

        tiles[5] = new Tile();
        tiles[5].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("")));

    }catch (Exception e){
        System.out.println("tile images not working");
    }

}


private void loadmap(String map_name){
    try{
        InputStream is = getClass().getClassLoader().getResourceAsStream(map_name);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int col = 0;
        int row = 0;
        while(col < game_panel.getMax_world_col() && row < game_panel.getMax_world_row()){

            String line = br.readLine();

            while(col< game_panel.getMax_world_col() && row < game_panel.getMax_world_row()){
                 String numbers[] = line.split(" ");
                 int number = Integer.parseInt(numbers[col]);

                 map[col][row] = number;
                 col++;


            }

            if(col == game_panel.getMax_screen_col()){
                col = 0;
                row ++;
            }


        }
        br.close();


    }catch (Exception e){
        System.out.println("cannot load map");
    }
}

public void draw(Graphics2D g2){
    int world_col = 0;
    int world_row = 0;

    //CAMERA FUNCTION
    while(world_col < game_panel.getMax_world_col() && world_row < game_panel.getMax_world_row()){

        int index = map[world_col][world_row];
        int world_x = world_col * game_panel.getTile_size_x();
        int world_y = world_row * game_panel.getTile_size_y();
        int screen_x = world_x - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
        int screen_y = world_y - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();
        g2.drawImage(tiles[map[world_col][world_row]].getImage(),screen_x,screen_y,game_panel.getTile_size_x(), game_panel.getTile_size_y(),null );
        world_col++;


        if(world_col == game_panel.getMax_world_col()){
            world_col = 0;

            world_row++;

        }
    }

}

}
