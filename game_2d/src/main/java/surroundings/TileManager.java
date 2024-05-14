package surroundings;
import Main.Game_panel;
import Main.Toolbox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
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
      //  setup(0,"water",true);
      //  setup(1,"water",false);
      //  setup(3,"trees_2_tets",true);
      //  setup(2,"grass_2_test_2",false);
      //  setup(33,"trees_2_tets",false);
        setup(0,"water",true);
        setup(1,"asset_1",false);
        setup(2,"grass",false);
        setup(3,"asset_2",false);
        setup(4,"asset_3",false);
        setup(5,"asset_4",false);
        setup(6,"asset_5",false);
        setup(7,"asset_6",false);
        setup(8,"asset_7",false);
        setup(9,"asset_8",false);
        setup(10,"trees_2_final",true);





        // getTiles()[2] = new Tile();
        //getTiles()[2].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/grass_2_test_2.png")));

        System.out.println("tiles was loaded");

    }catch (Exception e){
        System.out.println("tile images not working");
    }

}


public void  setup (int index, String imagePath, boolean collision){
    Toolbox toolbox = new Toolbox();
    try {
        getTiles()[index] = new Tile();
        getTiles()[index].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/"+ imagePath +".png")));
        getTiles()[index].setImage(toolbox.scale_image(getTiles()[index].getImage(), game_panel.getTile_size_x(), game_panel.getTile_size_y()));
        getTiles()[index].setCollision(collision);

    }catch (IOException e){
        System.out.println("Can not load tile image");
    }
}


private void loadmap(){
    try {
        InputStream is = getClass().getClassLoader().getResourceAsStream("Maps/map_1.txt");
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
    while(world_col < game_panel.getMax_world_col() && world_row < game_panel.getMax_world_row()) {


            int world_x = world_col * game_panel.getTile_size_x();
            int world_y = world_row * game_panel.getTile_size_y();
            int screen_x = world_x - game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x();
            int screen_y = world_y - game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y();


        if (      world_x + game_panel.getTile_size_x() * 2 > game_panel.getHero().getPosition_x() - game_panel.getHero().getScreen_x()
               && world_x - game_panel.getTile_size_x() * 2 < game_panel.getHero().getPosition_x() + game_panel.getHero().getScreen_x()
               && world_y + game_panel.getTile_size_x() * 2 > game_panel.getHero().getPosition_y() - game_panel.getHero().getScreen_y()
               && world_y - game_panel.getTile_size_x() * 2 < game_panel.getHero().getPosition_y() + game_panel.getHero().getScreen_y()
          ){
            g2.drawImage(getTiles()[getMap()[world_col][world_row]].getImage(), screen_x, screen_y, null);

        }
        world_col++;

            if (world_col == game_panel.getMax_world_col()) {
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
