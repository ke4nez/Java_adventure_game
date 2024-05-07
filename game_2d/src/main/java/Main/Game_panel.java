package Main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import NPC.Hero;
import surroundings.TileManager;


public class Game_panel extends JPanel implements Runnable{





    private int tile_size_x = 48;
    private int tile_size_y = 48;

    private final int max_world_col = 1920/tile_size_x;

    private final int max_world_row = 1920/tile_size_y;

    private int max_screen_col =1920/tile_size_x;
    private int max_screen_row =1080/tile_size_y;

    final int window_width = 1920;
    final int window_height = 1080;



    Game_controls game_controls = new Game_controls();

    Thread gameThread;

    Hero hero = new Hero(this,game_controls);
    TileManager tileManager = new TileManager(this);


    public void set_game_panel(){

        this.setPreferredSize(new Dimension(window_width, window_height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(game_controls);
        this.setFocusable(true);


    }


    public void start_game(){
        gameThread = new Thread(this);
        gameThread.start();
    }



    public void run() {

        while(gameThread != null) {

            hero.updatehero();
            repaint();
            try {
                Thread.sleep(1000 / 60); // Задержка для 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }
    }

public void update()
{
        hero.updatehero();
}

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);
        hero.painthero(g2);

    }

    public int getMax_world_row() {
        return max_world_row;
    }

    public int getMax_world_col() {
        return max_world_col;
    }

    public int getTile_size_x() {
        return tile_size_x;
    }

    public void setTile_size_x(int tile_size_x) {
        this.tile_size_x = tile_size_x;
    }

    public int getTile_size_y() {
        return tile_size_y;
    }

    public void setTile_size_y(int tile_size_y) {
        this.tile_size_y = tile_size_y;
    }

    public int getMax_screen_col() {
        return max_screen_col;
    }

    public void setMax_screen_col(int max_screen_height) {
        this.max_screen_col = max_screen_height;
    }

    public int getMax_screen_row() {
        return max_screen_row;
    }

    public void setMax_screen_row(int max_screen_width) {
        this.max_screen_row = max_screen_row;
    }
}