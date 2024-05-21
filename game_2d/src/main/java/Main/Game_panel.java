package Main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Data.SaveLoad;
import Entity.Entity;
import Entity.Hero;
import Objects.Game_Object;
import Enviroment.EnviromentManager;
import Enviroment.Lightning;
import Objects.Lamp_of;
import Surroundings.TileManager;

/**
 * The Game_panel class extends JPanel and implements the Runnable interface.
 * This class is responsible for setting up the game panel, handling the game loop,
 * and managing the rendering and updating of game objects.
 */
public class Game_panel extends JPanel implements Runnable{




    //WORLD SETTINGS
    private int tile_size_x = 64;
    private int tile_size_y =64;
    private final int max_world_col = 64;
    private final int max_world_row = 64;
    //SCREEN SETTINGS

    private final int window_width = 1600;
    private final int window_height =900;



    //CREATION OF THE GAME WINDOW AND THREAD

    private Game_controls game_controls = new Game_controls(this);

    Thread gameThread;

    private Hero hero = new Hero(this, game_controls);

    private ArrayList <Game_Object>  obj = new ArrayList<>();
    private ArrayList<Entity> npcs = new ArrayList<>();
    private TileManager tileManager = new TileManager(this);
    private AssetManager assetManager = new AssetManager(this);
    private CollisionChecker collisionChecker = new CollisionChecker(this);

    private EnviromentManager enviromentManager = new EnviromentManager(this);

    private GUI gui = new GUI(this);

    private SaveLoad saveLoad = new SaveLoad(this);



    //LOGGING

private int gameState;
    private  final int MainMenuState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogState = 3;
    private final int pause_menu = 4;
    private final int inventoryState = 5;
    private final int GameEndState = 6;

    //private int gameState;
    private boolean logON = false;

    public void setGame(){
        hero.setOn_level_number(1);
        setGameState(getMainMenuState());
    }

    /**
     * Configures the game panel settings.
     */
    public void set_game_panel(){

        this.setPreferredSize(new Dimension(getWindow_width(), getWindow_height()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(game_controls);
        this.setFocusable(true);
    }

    /**
     * Starts the game by initializing the game thread.
     */
    public void start_game(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    /**
     * The main game loop, responsible for updating the game state and repainting the panel.
     */
    public void run() {

        while(gameThread != null) {
            update();
            repaint();
            try {
                Thread.sleep(1000 / 60); //60 fps
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the game objects based on the current game state.
     */
public void update()
{

    //PLAY STATE
    if(getGameState() == getPlayState()) {


        hero.updatehero();


        for(int i = 0 ; i < npcs.size(); i++) {
            if (npcs.get(i) != null) {
                npcs.get(i).update();
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
            //TILES
            tileManager.draw(g2);

            //OBJECTS
            if (obj.size() > 0) {
                for (int i = 0; i < obj.size(); i++) {
                    if (obj.get(i) != null) {
                        obj.get(i).paintObject(g2);
                    }
                }
            }

            //NPCs
            if(npcs.size()> 0) {
                for (int i = 0; i < npcs.size(); i++) {
                    if (npcs.get(i) != null) {
                        npcs.get(i).draw(g2, this);
                    }
                }
            }

            //HERO
            hero.painthero(g2);

            //Enviroment
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
        getEnviromentManager().setLightning(new Lightning(this, x));
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


    public int getTile_size_y() {
        return tile_size_y;
    }

    public int getWindow_width() {
        return window_width;
    }

    public int getWindow_height() {
        return window_height;
    }

    public Hero getHero(){
       return hero;
    }
    public TileManager getTileManager() {
        return tileManager;
    }
    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public ArrayList<Game_Object> getObj() {
        return obj;
    }

    public Game_Object getObjFromObjects(int x) {
        if (x < obj.size()) {
            return obj.get(x);
        }
        return new Lamp_of(this);
    }

    public GUI getGui() {
        return gui;
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
    public ArrayList<Entity> getNpcs() {
        return npcs;
    }
    public Entity getNPCfromNPCs(int x){
        return npcs.get(x);
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
    public EnviromentManager getEnviromentManager() {
        return enviromentManager;
    }
    public AssetManager getAssetManager() {
        return assetManager;
    }
    public int getGameEndState() {
        return GameEndState;
    }

    public Game_controls getGame_controls() {
        return game_controls;
    }

    public void setGame_controls(Game_controls game_controls) {
        this.game_controls = game_controls;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setObj(ArrayList<Game_Object> obj) {
        this.obj = obj;
    }

    public void setNpcs(ArrayList<Entity> npcs) {
        this.npcs = npcs;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void setCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    public void setEnviromentManager(EnviromentManager enviromentManager) {
        this.enviromentManager = enviromentManager;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void setSaveLoad(SaveLoad saveLoad) {
        this.saveLoad = saveLoad;
    }

    public SaveLoad getSaveLoad() {
        return saveLoad;
    }
}