package surroundings;
import Main.Game_panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TileManager {

Game_panel game_panel;
 private Tile[] tiles;
 private int[][] map;

public TileManager(Game_panel game_panel){
    this.game_panel = game_panel;
    setMap(new int[game_panel.getMax_world_col()][game_panel.getMax_world_row()]);
    setTiles(new Tile[64]);
    getTileImages();
    loadmap();

}

private void getTileImages(){
    try{
        getTiles()[0] = new Tile();
        getTiles()[0].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/water.png")));
        getTiles()[0].setCollision(true);

        getTiles()[1] = new Tile();
        getTiles()[1].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/grass.png")));

        getTiles()[2] = new Tile();
        getTiles()[2].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/sand.png")));

        getTiles()[3] = new Tile();
        getTiles()[3].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/bricks.png")));
        getTiles()[3].setCollision(true);

        getTiles()[4] = new Tile();
        getTiles()[4].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/leafs.png")));
        getTiles()[4].setCollision(true);

        getTiles()[5] = new Tile();
        getTiles()[5].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/dirt.png")));
        getTiles()[5].setCollision(true);

        System.out.println("tiles was loaded");


    }catch (Exception e){
        System.out.println("tile images not working");
    }

}


private void loadmap(){
    try{
        InputStream is = getClass().getClassLoader().getResourceAsStream("Maps/map_1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));


        int col = 0;
        int row = 0;


        while(col < game_panel.getMax_world_col() && row < game_panel.getMax_world_row()){

            String line = br.readLine();

            while(col< game_panel.getMax_world_col()){
                String numbers[] = line.split(" ");
                 int number = Integer.parseInt(numbers[col]);

                 getMap()[col][row] = number;
                 col++;

            }
            if(col == game_panel.getMax_world_col()) {
                col = 0;
                row++;
            }
        }
        System.out.println("map was load");
        br.close();


    }catch (Exception e){
        System.out.println("fail to load map");
    }
}

public void draw(Graphics2D g2){
    int world_col = 0;
    int world_row = 0;

    //CAMERA FUNCTION
    while(world_col < game_panel.getMax_world_col() && world_row < game_panel.getMax_world_row()){

        //int index = map[world_col][world_row];
        int world_x = world_col * game_panel.getTile_size_x();
        int world_y = world_row * game_panel.getTile_size_y();
        int screen_x = world_x - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
        int screen_y = world_y - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();
        g2.drawImage(getTiles()[getMap()[world_col][world_row]].getImage(),screen_x,screen_y,game_panel.getTile_size_x(), game_panel.getTile_size_y(),null );
        world_col++;


        if(world_col == game_panel.getMax_world_col()){
            world_col = 0;
            world_row++;

        }
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
