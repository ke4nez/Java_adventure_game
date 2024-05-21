package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Game_controls class implements the KeyListener interface and handles
 * various game controls based on keyboard input.
 * This class is responsible for updating the game state, handling player
 * movement, interactions, and menu navigation.
 */
public class Game_controls implements KeyListener {

    Game_panel game_panel;

    public boolean go_right, go_left, go_up, go_down, interaction;
    private int counter = 0;
    // CRAFT
    private int craft_counter = 0;
    int index_1 = 0;
    int index_2 = 0;

    String text;


    private long lastInteractionTime = 0;
    private final long interactionCooldown = 2000; // 1 секунда




    public Game_controls(Game_panel game_panel) {
        this.game_panel = game_panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Invoked when a key has been pressed. This method processes the key press events
     * and updates the game state or player actions accordingly.
     *
     * @param e The KeyEvent to be processed.
     */
    @Override
    public void keyPressed(KeyEvent e) {


        //MENU STATE
        if (game_panel.getGameState() == game_panel.getMainMenuState()) {


            if (e.getKeyCode() == KeyEvent.VK_W) {
                game_panel.getGui().setCommand_number(game_panel.getGui().getCommand_number() - 1);
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                game_panel.getGui().setCommand_number(game_panel.getGui().getCommand_number() + 1);
            }

            if (game_panel.getGui().getCommand_number() > 3) {
                game_panel.getGui().setCommand_number(1);
            }
            if (game_panel.getGui().getCommand_number() < 1) {
                game_panel.getGui().setCommand_number(3);
            }


            if (e.getKeyCode() == KeyEvent.VK_E) {
                if (game_panel.getGui().getCommand_number() == 1) {
                    game_panel.setGameState(game_panel.getPlayState());
                }
                if (game_panel.getGui().getCommand_number() == 2) {
                    game_panel.getSaveLoad().load();
                    game_panel.setGameState(game_panel.getPlayState());
                }
                if (game_panel.getGui().getCommand_number() == 3) {
                    System.exit(0);
                }
            }
        }


        //PAUSE MENU STATE
        if (game_panel.getGameState() == game_panel.getPause_menu()) {


            if (e.getKeyCode() == KeyEvent.VK_W) {
                game_panel.getGui().setCommand_number(game_panel.getGui().getCommand_number() - 1);
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                game_panel.getGui().setCommand_number(game_panel.getGui().getCommand_number() + 1);
            }

            if (game_panel.getGui().getCommand_number() > 3) {
                game_panel.getGui().setCommand_number(1);
            }
            if (game_panel.getGui().getCommand_number() < 1) {
                game_panel.getGui().setCommand_number(3);
            }

            if (e.getKeyCode() == KeyEvent.VK_E) {
                if (game_panel.getGui().getCommand_number() == 1) {
                    game_panel.setGameState(game_panel.getPlayState());
                }
                if (game_panel.getGui().getCommand_number() == 2) {
                    game_panel.getSaveLoad().save();
                    game_panel.setGameState(game_panel.getPlayState());
                    text = "Game saved";
                    game_panel.getGui().addMessage(text, game_panel.getGui().getXfortextincenter(text),
                            game_panel.getGui().getYForCenterinGameMessage());
                }
                if (game_panel.getGui().getCommand_number() == 3) {
                    System.exit(0);
                }
            }
        }
        //END GAME STATE
        if(game_panel.getGameState() == game_panel.getGameEndState()){
            if(e.getKeyCode() == KeyEvent.VK_E){
                game_panel.setGameState(game_panel.getMainMenuState());
            }
        }
        //PLAY STATE
        if (game_panel.getGameState() == game_panel.getPlayState()) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                go_up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                go_down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                go_left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                go_right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                interaction = true;

            }


                //interaction = true;
            if (e.getKeyCode() == KeyEvent.VK_L) {
                if (game_panel.isLogON()) {
                    game_panel.setLogON(false);
                    //  System.out.println("LOG OFF");
                    text = "Log messages is OFF";
                    game_panel.getGui().addMessage(text, game_panel.getGui().getXfortextincenter(text),
                            game_panel.getGui().getYForCenterinGameMessage());

                } else if (game_panel.isLogON() == false) {

                    game_panel.setLogON(true);
                    // System.out.println("LOG ON");
                    text = "Log messages is ON";
                    game_panel.getGui().addMessage(text, game_panel.getGui().getXfortextincenter(text),
                            game_panel.getGui().getYForCenterinGameMessage());
                }
            }


            if (e.getKeyCode() == KeyEvent.VK_P) {
                game_panel.setGameState(game_panel.getPauseState());
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                game_panel.setGameState(game_panel.getPause_menu());
            }

            if (e.getKeyCode() == KeyEvent.VK_I) {
                game_panel.setGameState(game_panel.getInventoryState());
            }

        }
        //INVENTORY STATE
        else if (game_panel.getGameState() == game_panel.getInventoryState()) {

            if (e.getKeyCode() == KeyEvent.VK_I) {
                game_panel.setGameState(game_panel.getPlayState());
            }

            //d
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (game_panel.getGui().getSlot_col() != 4) {
                    game_panel.getGui().setSlot_col(game_panel.getGui().getSlot_col() + 1);
                }
            }

            //a

            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (game_panel.getGui().getSlot_col() != 0) {
                    game_panel.getGui().setSlot_col(game_panel.getGui().getSlot_col() - 1);
                }
            }
            //w

            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (game_panel.getGui().getSlot_row() != 0) {
                    game_panel.getGui().setSlot_row(game_panel.getGui().getSlot_row() - 1);
                }
            }
            //s

            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (game_panel.getGui().getSlot_row() != 4) {
                    game_panel.getGui().setSlot_row(game_panel.getGui().getSlot_row() + 1);
                }
            }

            //c CRAFT ITEMS
            if (e.getKeyCode() == KeyEvent.VK_C) {


                if (craft_counter == 0) {
                    index_1 = game_panel.getGui().getindexofitem();
                    text = "you pick first item";
                    game_panel.getGui().addMessage(text, game_panel.getGui().getXfortextincenter(text),
                            game_panel.getGui().getYForCenterinGameMessage());
                    craft_counter++;
                    System.out.println("you pick first item");

                } else if (craft_counter == 1) {
                    index_2 = game_panel.getGui().getindexofitem();
                    text = "you pick second item";
                    game_panel.getGui().addMessage(text, game_panel.getGui().getXfortextincenter(text),
                            game_panel.getGui().getYForCenterinGameMessage());
                    System.out.println("you pick second item");
                    craft_counter++;
                } else if (craft_counter == 2) {

                    if (index_1 < game_panel.getHero().inventory.size() && index_2 < game_panel.getHero().inventory.size()) {
                        text = "crafting...";
                        game_panel.getGui().addMessage(text, game_panel.getGui().getXfortextincenter(text),
                                game_panel.getGui().getYForCenterinGameMessage());
                         System.out.println("crafting...");
                        game_panel.getHero().craftItem(index_1, index_2);
                        index_1 = 0;
                        index_2 = 0;
                        craft_counter = 0;
                    } else {
                        text = "You pick empty slot or slots!";
                        game_panel.getGui().addMessage(text, game_panel.getGui().getXfortextincenter(text),
                                game_panel.getGui().getYForCenterinGameMessage());
                        System.out.println("You pick empty slot or slots");
                        index_1 = 0;
                        index_2 = 0;
                        craft_counter = 0;
                    }
                }
            }
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    game_panel.getHero().selectitem();
                }
        }

            //PAUSE STATE
            else if (game_panel.getGameState() == game_panel.getPauseState()) {
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    game_panel.setGameState(game_panel.getPlayState());
                }
            }

            //DIALOGUE STATE
            else if (game_panel.getGameState() == game_panel.getDialogState()) {
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    game_panel.setGameState(game_panel.getPlayState());
                }
            }
    }
        @Override
        public void keyReleased (KeyEvent e){

            counter++;

            if (e.getKeyCode() == KeyEvent.VK_W) {
                go_up = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                go_down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                go_left = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                go_right = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                interaction = false;
            }
            counter++;
            if (counter > 120) {
                counter = 0;
            }
        }
}
