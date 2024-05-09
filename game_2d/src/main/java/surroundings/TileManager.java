package surroundings;

import Main.Game_panel;

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
    map = new int[game_panel.getMax_screen_col()][game_panel.getMax_screen_row()];
    tiles = new Tile[64];
    getTileImages();
    loadmap("Maps/map_1.txt");

}

private void getTileImages(){
    try{
        tiles[0] = new Tile();
        tiles[0].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/grass.png")));

        tiles[1] = new Tile();
        tiles[1].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/water.png")));

        tiles[2] = new Tile();
        tiles[2].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("")));

        tiles[3] = new Tile();
        tiles[3].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("")));

        tiles[4] = new Tile();
        tiles[4].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("")));

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
        while(col < game_panel.getMax_screen_col() && row < game_panel.getMax_screen_row()){

            String line = br.readLine();

            while(col< game_panel.getMax_screen_col() && row < game_panel.getMax_screen_row()){
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
    int row = 0;
    int col = 0;
    int x = 0;
    int y = 0;

    while(col < game_panel.getMax_screen_col() && row < game_panel.getMax_screen_row()){
        g2.drawImage(tiles[map[col][row]].getImage(),x,y,game_panel.getTile_size_x(), game_panel.getTile_size_y(),null );
        col++;
        x += game_panel.getTile_size_x();

        if(col == game_panel.getMax_screen_col()){
            col = 0;
            x = 0;
            row++;
            y += game_panel.getTile_size_y();
        }
    }

}

}
