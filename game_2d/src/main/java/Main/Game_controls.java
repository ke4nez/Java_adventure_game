package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Game_controls implements KeyListener {

    Game_panel game_panel;

    public boolean go_right, go_left, go_up, go_down, interaction, jump;
    private int counter = 0;

    public Game_controls (Game_panel game_panel){
        this.game_panel = game_panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //PLAY STATE
        if (game_panel.getGameState() == game_panel.getPlayState()) {
            if (e.getKeyCode() == 87) {
                go_up = true;
            }
            if (e.getKeyCode() == 83) {
                go_down = true;
            }
            if (e.getKeyCode() == 65) {
                go_left = true;
            }
            if (e.getKeyCode() == 68) {
                go_right = true;
            }
            if (e.getKeyCode() == 69) {
                interaction = true;
            }
            if (e.getKeyCode() == 32) {
                jump = true;
            }
            if (e.getKeyCode() == 76) {
                if (game_panel.isLogON()) {
                    game_panel.setLogON(false);
                    System.out.println("LOG OFF");
                    game_panel.getGui().addMessage("Log messages is OFF", 1400, 300);
                } else if (game_panel.isLogON() == false) {
                    game_panel.setLogON(true);
                    System.out.println("LOG ON");
                    game_panel.getGui().addMessage("Log messages is ON", 1400, 300);
                }
            }


            if (e.getKeyCode() == 80) {
                game_panel.setGameState(game_panel.getPauseState());
            }

        }


        //PAUSE STATE
       else if(game_panel.getGameState() == game_panel.getPauseState()) {
            if (e.getKeyCode() == 80) {
                game_panel.setGameState(game_panel.getPlayState());
            }
        }

        //DIALOGUE STATE
        else if(game_panel.getGameState()== game_panel.getDialogState()){
            if(e.getKeyCode() == 80){
                game_panel.setGameState(game_panel.getPlayState());
            }

        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

        counter++;

        if (e.getKeyCode() == 87) {
            go_up = false;
        }
        if (e.getKeyCode() == 83) {
            go_down = false;
        }
        if (e.getKeyCode() == 65) {
            go_left = false;
        }
        if (e.getKeyCode() == 68) {
            go_right = false;
        }
        if (e.getKeyCode() == 69) {
            interaction = false;
        }

        if (e.getKeyCode() == 32) {
            jump = false;
        }
        counter++;
        if (counter >120){counter = 0;}
    }
}