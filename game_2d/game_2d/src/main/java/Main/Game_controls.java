package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Game_controls implements KeyListener {

    Game_panel game_panel;

    public boolean go_right, go_left, go_up, go_down, interaction, jump;
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

    @Override
    public void keyPressed(KeyEvent e) {


        //MENU STATE
        if (game_panel.getGameState() == game_panel.getMainMenuState()) {


            if (e.getKeyCode() == 87) {
                game_panel.getGui().setCommand_number(game_panel.getGui().getCommand_number() - 1);
            }

            if (e.getKeyCode() == 83) {
                game_panel.getGui().setCommand_number(game_panel.getGui().getCommand_number() + 1);
            }

            if (game_panel.getGui().getCommand_number() > 3) {
                game_panel.getGui().setCommand_number(1);
            }
            if (game_panel.getGui().getCommand_number() < 1) {
                game_panel.getGui().setCommand_number(3);
            }


            if (e.getKeyCode() == 69) {
                if (game_panel.getGui().getCommand_number() == 1) {
                    game_panel.setGameState(game_panel.getPlayState());
                }
                if (game_panel.getGui().getCommand_number() == 2) {
                    game_panel.saveLoad.load();
                    game_panel.setGameState(game_panel.getPlayState());
                }
                if (game_panel.getGui().getCommand_number() == 3) {
                    System.exit(0);
                }
            }
        }


        //PAUSE MENU STATE
        if (game_panel.getGameState() == game_panel.getPause_menu()) {


            if (e.getKeyCode() == 87) {
                game_panel.getGui().setCommand_number(game_panel.getGui().getCommand_number() - 1);
            }

            if (e.getKeyCode() == 83) {
                game_panel.getGui().setCommand_number(game_panel.getGui().getCommand_number() + 1);
            }

            if (game_panel.getGui().getCommand_number() > 3) {
                game_panel.getGui().setCommand_number(1);
            }
            if (game_panel.getGui().getCommand_number() < 1) {
                game_panel.getGui().setCommand_number(3);
            }


            if (e.getKeyCode() == 69) {
                if (game_panel.getGui().getCommand_number() == 1) {
                    game_panel.setGameState(game_panel.getPlayState());
                }
                if (game_panel.getGui().getCommand_number() == 2) {
                    game_panel.saveLoad.save();
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
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastInteractionTime >= interactionCooldown) {
                        interaction = true;
                        lastInteractionTime = currentTime;
                     }
            }


                //interaction = true;

            if (e.getKeyCode() == 32) {
                jump = true;
            }
            if (e.getKeyCode() == 76) {
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


            if (e.getKeyCode() == 80) {
                game_panel.setGameState(game_panel.getPauseState());
            }

            if (e.getKeyCode() == 27) {
                game_panel.setGameState(game_panel.getPause_menu());
            }

            if (e.getKeyCode() == 73) {
                game_panel.setGameState(game_panel.getInventoryState());
            }

        }
        //INVENTORY STATE
        else if (game_panel.getGameState() == game_panel.getInventoryState()) {

            if (e.getKeyCode() == 73) {
                game_panel.setGameState(game_panel.getPlayState());
            }

            //d
            if (e.getKeyCode() == 68) {
                if (game_panel.getGui().getSlot_col() != 4) {
                    game_panel.getGui().setSlot_col(game_panel.getGui().getSlot_col() + 1);
                }
            }

            //a

            if (e.getKeyCode() == 65) {
                if (game_panel.getGui().getSlot_col() != 0) {
                    game_panel.getGui().setSlot_col(game_panel.getGui().getSlot_col() - 1);
                }
            }
            //w

            if (e.getKeyCode() == 87) {
                if (game_panel.getGui().getSlot_row() != 0) {
                    game_panel.getGui().setSlot_row(game_panel.getGui().getSlot_row() - 1);
                }
            }
            //s

            if (e.getKeyCode() == 83) {
                if (game_panel.getGui().getSlot_row() != 4) {
                    game_panel.getGui().setSlot_row(game_panel.getGui().getSlot_row() + 1);
                }
            }

            //c CRAFT ITEMS
            if (e.getKeyCode() == 67) {


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

                if (e.getKeyCode() == 69) {
                    game_panel.getHero().selectitem();
                }



        }

            //PAUSE STATE
            else if (game_panel.getGameState() == game_panel.getPauseState()) {
                if (e.getKeyCode() == 80) {
                    game_panel.setGameState(game_panel.getPlayState());
                }
            }

            //DIALOGUE STATE
            else if (game_panel.getGameState() == game_panel.getDialogState()) {
                if (e.getKeyCode() == 69) {
                    game_panel.setGameState(game_panel.getPlayState());
                }


            }


    }



        @Override
        public void keyReleased (KeyEvent e){

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
            if (counter > 120) {
                counter = 0;
            }
        }



}
