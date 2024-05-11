package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game_controls implements KeyListener {

  public boolean go_right, go_left, go_up, go_down, interaction, jump;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 87){
            go_up = true;
        }
        if(e.getKeyCode() == 83){
            go_down = true;
        }
        if(e.getKeyCode() == 65){
            go_left = true;
        }
        if(e.getKeyCode() == 68){
            go_right = true;
        }
        if(e.getKeyCode() == 69){
            interaction = true;
        }
        if(e.getKeyCode() == 32){
            jump = true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == 87){
            go_up = false;
        }
        if(e.getKeyCode() == 83){
            go_down = false;
        }
        if(e.getKeyCode() == 65){
            go_left = false;
        }
        if(e.getKeyCode() == 68){
            go_right = false;
        }
        if(e.getKeyCode() == 69){
            interaction = false;
        }

        if(e.getKeyCode() == 32){
            jump = false;
        }


    }
}
