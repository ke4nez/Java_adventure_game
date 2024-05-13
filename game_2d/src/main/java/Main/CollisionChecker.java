package Main;

import NPC.NPC;
import NPC.Hero;

import java.awt.*;

public class CollisionChecker {
    Game_panel game_panel;


    public CollisionChecker(Game_panel game_panel) {
        this.game_panel = game_panel;
    }

    public void check_tile(NPC npc) {

        // 4 coordinates of rectangle of collision left x,

        //  1/////////////////////2      1(left x, top y)
        //                   //      2(left x, bottom y)
        //                   //      3(right x, top y)
        //                   //      4(right x, bottom y)
        //                   //
        //                   //
        //                   //
        //                   //
        //  3/////////////////////4

        int npc_rectangle_left_x = npc.getPosition_x() + (int) (npc.getNPC_rectangle().getX()); // take x from rectangle and convert it to int
        int npc_rectangle_right_x = npc.getPosition_x() + (int) (npc.getNPC_rectangle().getX()) + (int) npc.getNPC_rectangle().getWidth(); //right x
        int npc_rectangle_top_y = npc.getPosition_y() + (int) (npc.getNPC_rectangle().getY());
        int npc_rectangle_bottom_y = npc.getPosition_y() + (int) (npc.getNPC_rectangle().getY()) + (int) (npc.getNPC_rectangle().getHeight());

        int npc_left_col = npc_rectangle_left_x / game_panel.getTile_size_x();
        int npc_right_col = npc_rectangle_right_x / game_panel.getTile_size_x();
        int npc_top_row = npc_rectangle_top_y / game_panel.getTile_size_y();
        int npc_bottom_row = npc_rectangle_bottom_y / game_panel.getTile_size_y();

        int tile_1_number;
        int tile_2_number;

        if (npc.getDirection() == "up") {
            npc_top_row = (npc_rectangle_top_y - npc.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_top_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                npc.setCollision(true);
              //  System.out.println("Player hitting solid block up");

            }


        }

        if (npc.getDirection() == "down") {
            npc_bottom_row = (npc_rectangle_bottom_y + npc.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_bottom_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_bottom_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                npc.setCollision(true);


                //System.out.println("Player hitting solid block down");
            }
        }


        if (npc.getDirection() == "right") {
            npc_right_col = (npc_rectangle_right_x + npc.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_bottom_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                npc.setCollision(true);

               // System.out.println("Player hitting solid block right");
            }
        }


        if (npc.getDirection() == "left") {
            npc_left_col = (npc_rectangle_left_x - npc.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_bottom_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                npc.setCollision(true);
                //System.out.println("Player hitting solid block left");
            }

        }
    }

    public int check_object(NPC npc, boolean isplayer) {

        int index = 99;

        for (int i = 0; i < game_panel.getObj().length; i++) {
            if (game_panel.getObjFromObjects(i) != null) {
                npc.getNPC_rectangle().x = npc.getPosition_x() + npc.getNPC_rectangle().x;
                npc.getNPC_rectangle().y = npc.getPosition_y() + npc.getNPC_rectangle().y;

                game_panel.getObjFromObjects(i).getObject_rectangle().x = game_panel.getObjFromObjects(i).getPosition_x() + game_panel.getObjFromObjects(i).getObject_rectangle().x;
                game_panel.getObjFromObjects(i).getObject_rectangle().y = game_panel.getObjFromObjects(i).getPosition_y() + game_panel.getObjFromObjects(i).getObject_rectangle().y;


                switch (npc.getDirection()) {

                    case "interaction":
                        npc.getNPC_rectangle().y -= npc.getSpeed();//up

                        // npc.getNPC_rectangle().x -= npc.getSpeed();// left
                        // npc.getNPC_rectangle().x += npc.getSpeed();// right


                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            npc.setCollision(true);
                            game_panel.getGui().addMessage("you  interacted with object infront of you", 600, 100);
                            return (index = i);
                        }
                        npc.getNPC_rectangle().y += npc.getSpeed() * 2;// down

                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            npc.setCollision(true);
                            game_panel.getGui().addMessage("you  interacted with object behind of you", 600, 100);
                            return (index = i);
                        }

                        npc.getNPC_rectangle().y -= npc.getSpeed();//up
                        npc.getNPC_rectangle().x -= npc.getSpeed();// left

                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            npc.setCollision(true);
                            game_panel.getGui().addMessage("you  interacted with object to  you left", 600, 100);
                            return (index = i);
                        }
                        npc.getNPC_rectangle().x += npc.getSpeed() * 2;// right

                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            npc.setCollision(true);
                            game_panel.getGui().addMessage("you can interacted with object to  you right", 600, 100);
                            return (index = i);
                        }
                        break;


                    case "up":
                        npc.getNPC_rectangle().y -= npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            // System.out.println("up collision");
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                npc.setCollision(true);
                            }
                            index = i;
                        }

                        break;

                    case "down":
                        npc.getNPC_rectangle().y += npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            // System.out.println("down collision");
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                npc.setCollision(true);
                            }
                            index = i;
                        }
                        break;
                    case "left":
                        npc.getNPC_rectangle().x -= npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            // System.out.println("left collision");
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                npc.setCollision(true);
                            }
                            index = i;
                        }
                        break;
                    case "right":
                        npc.getNPC_rectangle().x += npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            // System.out.println("right collision");
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                npc.setCollision(true);
                            }
                        }
                        index = i;
                        break;
                }


                npc.getNPC_rectangle().x = npc.getNPC_rectangle_default_x();
                npc.getNPC_rectangle().y = npc.getNPC_rectangle_default_y();
                game_panel.getObjFromObjects(i).getObject_rectangle().x = game_panel.getObjFromObjects(i).getObject_rectangle_default_x();
                game_panel.getObjFromObjects(i).getObject_rectangle().y = game_panel.getObjFromObjects(i).getObject_rectangle_default_y();
            }


        }
        return index;
    }


    public int checknpc(NPC npc, NPC[] target) {

        int index = 99;


        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                npc.getNPC_rectangle().x = npc.getPosition_x() + npc.getNPC_rectangle().x;
                npc.getNPC_rectangle().y = npc.getPosition_y() + npc.getNPC_rectangle().y;

               target[i].getNPC_rectangle().x = target[i].getPosition_x() + target[i].getNPC_rectangle().x;
               target[i].getNPC_rectangle().y = target[i].getPosition_y() + target[i].getNPC_rectangle().y;


                switch (npc.getDirection()) {

                    case "interaction":
                        npc.getNPC_rectangle().y -= npc.getSpeed();//up

                        // npc.getNPC_rectangle().x -= npc.getSpeed();// left
                        // npc.getNPC_rectangle().x += npc.getSpeed();// right


                        if (npc.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {

                            npc.setCollision(true);
                            game_panel.getGui().addMessage("you  interacted with npc infront of you", 600, 100);
                            index = i;
                        }
                        npc.getNPC_rectangle().y += npc.getSpeed() * 2;// down

                        if (npc.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            npc.setCollision(true);

                            game_panel.getGui().addMessage("you  interacted with npc behind of you", 600, 100);
                            index = i;

                        }

                        npc.getNPC_rectangle().y -= npc.getSpeed();//up
                        npc.getNPC_rectangle().x -= npc.getSpeed();// left

                        if (npc.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            npc.setCollision(true);
                            game_panel.getGui().addMessage("you  interacted with npc to  you left", 600, 100);
                            index = i;

                        }
                        npc.getNPC_rectangle().x += npc.getSpeed() * 2;// right

                        if (npc.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            npc.setCollision(true);
                            game_panel.getGui().addMessage("you can interacted with npc to  you right", 600, 100);
                            index = i;

                        }
                        break;


                    case "up":
                        npc.getNPC_rectangle().y -= npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            System.out.println("up collision");
                            npc.setCollision(true);
                            index = i;


                        }

                        break;

                    case "down":
                        npc.getNPC_rectangle().y += npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            System.out.println("down collision");
                            npc.setCollision(true);
                            index = i;


                        }
                        break;
                    case "left":
                        npc.getNPC_rectangle().x -= npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            System.out.println("left collision");
                            System.out.print(target[i].getCollision());
                            npc.setCollision(true);
                            index = i;


                        }
                        break;
                    case "right":
                        npc.getNPC_rectangle().x += npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            System.out.println("right collision");
                            npc.setCollision(true);
                            index = i;

                        }

                        break;
                }
                npc.getNPC_rectangle().x = npc.getNPC_rectangle_default_x();
                npc.getNPC_rectangle().y = npc.getNPC_rectangle_default_y();
                target[i].getNPC_rectangle().x = target[i].getNPC_rectangle_default_x();
                target[i].getNPC_rectangle().y = target[i].getNPC_rectangle_default_y();



            }




        }
        return  index;
    }

    public void checkhero (NPC npc, Hero hero){




                npc.getNPC_rectangle().x = npc.getPosition_x() + npc.getNPC_rectangle().x;
                npc.getNPC_rectangle().y = npc.getPosition_y() + npc.getNPC_rectangle().y;

                hero.getNPC_rectangle().x =  hero.getPosition_x() +  hero.getNPC_rectangle().x;
        hero.getNPC_rectangle().y =  hero.getPosition_y() +  hero.getNPC_rectangle().y;


                switch (npc.getDirection()) {

                    case "interaction":
                        npc.getNPC_rectangle().y -= npc.getSpeed();//up

                        // npc.getNPC_rectangle().x -= npc.getSpeed();// left
                        // npc.getNPC_rectangle().x += npc.getSpeed();// right


                        if (npc.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {

                            npc.setCollision(true);
                          //  game_panel.getGui().addMessage("you  interacted with npc player of you", 600, 100);

                        }
                        npc.getNPC_rectangle().y += npc.getSpeed() * 2;// down

                        if (npc.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                            npc.setCollision(true);

                          //  game_panel.getGui().addMessage("you  interacted with npc behind of you", 600, 100);


                        }

                        npc.getNPC_rectangle().y -= npc.getSpeed();//up
                        npc.getNPC_rectangle().x -= npc.getSpeed();// left

                        if (npc.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                            npc.setCollision(true);
                          //  game_panel.getGui().addMessage("you  interacted with npc to  you left", 600, 100);


                        }
                        npc.getNPC_rectangle().x += npc.getSpeed() * 2;// right

                        if (npc.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                            npc.setCollision(true);
                         //   game_panel.getGui().addMessage("you can interacted with npc to  you right", 600, 100);


                        }
                        break;


                    case "up":
                        npc.getNPC_rectangle().y -= npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                         //   System.out.println("up collision");
                            npc.setCollision(true);



                        }

                        break;

                    case "down":
                        npc.getNPC_rectangle().y += npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                          //  System.out.println("down collision");
                            npc.setCollision(true);



                        }
                        break;
                    case "left":
                        npc.getNPC_rectangle().x -= npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                          //  System.out.println("left collision");
                            npc.setCollision(true);



                        }
                        break;
                    case "right":
                        npc.getNPC_rectangle().x += npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                           // System.out.println("right collision");
                            npc.setCollision(true);


                        }

                        break;
                }
                npc.getNPC_rectangle().x = npc.getNPC_rectangle_default_x();
                npc.getNPC_rectangle().y = npc.getNPC_rectangle_default_y();
                hero.getNPC_rectangle().x =  hero.getNPC_rectangle_default_x();
                hero.getNPC_rectangle().y =  hero.getNPC_rectangle_default_y();



            }


        }
































