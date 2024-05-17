package Main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;

import Data.SaveLoad;
import Entity.Entity;
import Entity.Hero;
import Objects.Game_Object;
import enviroment.EnviromentManager;
import enviroment.Lightning;
import surroundings.TileManager;


public class Game_panel extends JPanel implements Runnable{




    //GAME SETTINGS


    private int tile_size_x = 48;
    private int tile_size_y =48;
    private final int max_world_col = 32;
    private final int max_world_row = 32;

    private final int max_world_width = getMax_world_col() * tile_size_x;
    private final int max_world_heigth = getMax_world_col() * tile_size_y;

    //SCREEN SETTINGS

    private final int window_width = 900;
    private final int window_height = 600;
    private int max_screen_col = window_width/tile_size_x;
    private int max_screen_row = window_height/tile_size_y;




    //CREATION OF THE GAME WINDOW AND THREAD

    Game_controls game_controls = new Game_controls(this);

    Thread gameThread;

    private Hero hero = new Hero(this,game_controls);

    private Game_Object[] obj = new Game_Object[32];
    private Entity[] npcs = new Entity[32];

    private AssetManager assetManager = new AssetManager(this);

    private TileManager tileManager = new TileManager(this);
    private CollisionChecker collisionChecker = new CollisionChecker(this);

    private EnviromentManager enviromentManager = new EnviromentManager(this);

    private GUI gui = new GUI(this);

    SaveLoad saveLoad = new SaveLoad(this);



    //LOGGING

    private  final int MainMenuState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogState = 3;
    private final int pause_menu = 4;

    private final int inventoryState = 5;

    private int gameState;
    private boolean logON = false;

    long draw_rime_start ;
    long draw_time_end = System.nanoTime();
    long passed;








    public void setGame(){
        assetManager.setObjects();
        assetManager.setNPCS();
        setGameState(getMainMenuState());
    }


    public void set_game_panel(){

        this.setPreferredSize(new Dimension(getWindow_width(), getWindow_height()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(game_controls);
        this.setFocusable(true);
        getEnviromentManager().setup();


    }


    public void start_game(){
        gameThread = new Thread(this);
        gameThread.start();
    }



    public void run() {

        while(gameThread != null) {

            update();
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

    //PLAY STATE
    if(getGameState() == getPlayState()) {


        hero.updatehero();


        for(int i = 0 ; i < npcs.length; i++) {
            if (npcs[i] != null) {
                npcs[i].update();
            }
        }

    }

    //PAUSE STATE
    if (getGameState() == getPauseState()){
        //
    }
}

    public void paintComponent(Graphics g){
        //LOGS
        long draw_rime_start = System.nanoTime();
        long passed;


        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //MAIN MENU OR PAUSE MENU
        if(gameState == getMainMenuState()){
        gui.draw(g2);
        }
        else {
            //Tiles

            tileManager.draw(g2);


            //objects
            for(int i = 0 ; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].paintObject(g2);
                }
            }
            //NPCs
            for(int i = 0 ; i < npcs.length; i++) {
                if (npcs[i] != null) {
                    npcs[i].draw(g2,this);
                }
            }
            hero.painthero(g2);


            //enviroment

            getEnviromentManager().draw(g2);

            if(logON) {
                long draw_time_end = System.nanoTime();
                passed = draw_time_end - draw_rime_start;
                gui.drawLogRenderTime(passed);
            }


            //GUI
            gui.draw(g2);


        }

    }


public void changelightning(int x){
        getEnviromentManager().setLightning(new Lightning(this,x));
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

    public int getWindow_width() {
        return window_width;
    }

    public int getWindow_height() {
        return window_height;
    }

    public int getMax_world_width() {
        return max_world_width;
    }

    public int getMax_world_heigth() {
        return max_world_heigth;
    }

    public Hero getHero(){
       return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public void setCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    public Game_Object[] getObj() {
        return obj;
    }

    public Game_Object getObjFromObjects(int x) {
        return obj[x];
    }
    public void setObj(Game_Object [] obj) {
        this.obj = obj;
    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public boolean isLogON() {
        return logON;
    }

    public void setLogON(boolean logON) {
        this.logON = logON;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public Entity[] getNpcs() {
        return npcs;
    }

    public Entity getNPCfromNPCs(int x){
        return npcs[x];
    }

    public void setNpcs(Entity[] entities) {
        this.npcs = entities;
    }

    public int getDialogState() {
        return dialogState;
    }

    public int getMainMenuState() {
        return MainMenuState;
    }

    public int getPause_menu() {
        return pause_menu;
    }

    public int getInventoryState() {
        return inventoryState;
    }

    public Game_panel getGamePanel(){
        return this;
    }

    public EnviromentManager getEnviromentManager() {
        return enviromentManager;
    }

    public void setEnviromentManager(EnviromentManager enviromentManager) {
        this.enviromentManager = enviromentManager;
    }
}