package Main;

import Entity.Entity;
import Entity.Hero;

public class CollisionChecker {
    Game_panel game_panel;


    public CollisionChecker(Game_panel game_panel) {
        this.game_panel = game_panel;
    }

    public void check_tile(Entity entity) {

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

        int npc_rectangle_left_x = entity.getPosition_x() + (int) (entity.getNPC_rectangle().getX()); // take x from rectangle and convert it to int
        int npc_rectangle_right_x = entity.getPosition_x() + (int) (entity.getNPC_rectangle().getX()) + (int) entity.getNPC_rectangle().getWidth(); //right x
        int npc_rectangle_top_y = entity.getPosition_y() + (int) (entity.getNPC_rectangle().getY());
        int npc_rectangle_bottom_y = entity.getPosition_y() + (int) (entity.getNPC_rectangle().getY()) + (int) (entity.getNPC_rectangle().getHeight());

        int npc_left_col = npc_rectangle_left_x / game_panel.getTile_size_x();
        int npc_right_col = npc_rectangle_right_x / game_panel.getTile_size_x();
        int npc_top_row = npc_rectangle_top_y / game_panel.getTile_size_y();
        int npc_bottom_row = npc_rectangle_bottom_y / game_panel.getTile_size_y();

        int tile_1_number;
        int tile_2_number;

        if (entity.getDirection() == "up") {
            npc_top_row = (npc_rectangle_top_y - entity.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_top_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                entity.setCollision(true);
              //  System.out.println("Player hitting solid block up");

            }


        }

        if (entity.getDirection() == "down") {
            npc_bottom_row = (npc_rectangle_bottom_y + entity.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_bottom_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_bottom_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                entity.setCollision(true);


                //System.out.println("Player hitting solid block down");
            }
        }


        if (entity.getDirection() == "right") {
            npc_right_col = (npc_rectangle_right_x + entity.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_bottom_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                entity.setCollision(true);

               // System.out.println("Player hitting solid block right");
            }
        }


        if (entity.getDirection() == "left") {
            npc_left_col = (npc_rectangle_left_x - entity.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_bottom_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                entity.setCollision(true);
                //System.out.println("Player hitting solid block left");
            }

        }
    }

    public int check_object(Entity entity, boolean isplayer) {

        int index = 99;

        for (int i = 0; i < game_panel.getObj().length; i++) {
            if (game_panel.getObjFromObjects(i) != null) {
                entity.getNPC_rectangle().x = entity.getPosition_x() + entity.getNPC_rectangle().x;
                entity.getNPC_rectangle().y = entity.getPosition_y() + entity.getNPC_rectangle().y;

                game_panel.getObjFromObjects(i).getObject_rectangle().x = game_panel.getObjFromObjects(i).getPosition_x() + game_panel.getObjFromObjects(i).getObject_rectangle().x;
                game_panel.getObjFromObjects(i).getObject_rectangle().y = game_panel.getObjFromObjects(i).getPosition_y() + game_panel.getObjFromObjects(i).getObject_rectangle().y;


                switch (entity.getDirection()) {

                    case "interaction":
                        entity.getNPC_rectangle().y -= entity.getSpeed();//up

                        // entity.getNPC_rectangle().x -= entity.getSpeed();// left
                        // entity.getNPC_rectangle().x += entity.getSpeed();// right


                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            entity.setCollision(true);
                           // game_panel.getGui().addMessage("you  interacted with object infront of you", 600, 100);
                            return (index = i);
                        }
                        entity.getNPC_rectangle().y += entity.getSpeed() * 2;// down

                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            entity.setCollision(true);
                           // game_panel.getGui().addMessage("you  interacted with object behind of you", 600, 100);
                            return (index = i);
                        }

                        entity.getNPC_rectangle().y -= entity.getSpeed();//up
                        entity.getNPC_rectangle().x -= entity.getSpeed();// left

                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            entity.setCollision(true);
                          //  game_panel.getGui().addMessage("you  interacted with object to  you left", 600, 100);
                            return (index = i);
                        }
                        entity.getNPC_rectangle().x += entity.getSpeed() * 2;// right

                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            entity.setCollision(true);
                           // game_panel.getGui().addMessage("you can interacted with object to  you right", 600, 100);
                            return (index = i);
                        }
                        break;


                    case "up":
                        entity.getNPC_rectangle().y -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            // System.out.println("up collision");
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                entity.setCollision(true);
                            }
                            index = i;
                        }

                        break;

                    case "down":
                        entity.getNPC_rectangle().y += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            // System.out.println("down collision");
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                entity.setCollision(true);
                            }
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getNPC_rectangle().x -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            // System.out.println("left collision");
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                entity.setCollision(true);
                            }
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getNPC_rectangle().x += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            // System.out.println("right collision");
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                entity.setCollision(true);
                            }
                        }
                        index = i;
                        break;
                }


                entity.getNPC_rectangle().x = entity.getNPC_rectangle_default_x();
                entity.getNPC_rectangle().y = entity.getNPC_rectangle_default_y();
                game_panel.getObjFromObjects(i).getObject_rectangle().x = game_panel.getObjFromObjects(i).getObject_rectangle_default_x();
                game_panel.getObjFromObjects(i).getObject_rectangle().y = game_panel.getObjFromObjects(i).getObject_rectangle_default_y();
            }


        }
        return index;
    }


    public int checknpc(Entity entity, Entity[] target) {

        int index = 99;


        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                entity.getNPC_rectangle().x = entity.getPosition_x() + entity.getNPC_rectangle().x;
                entity.getNPC_rectangle().y = entity.getPosition_y() + entity.getNPC_rectangle().y;

               target[i].getNPC_rectangle().x = target[i].getPosition_x() + target[i].getNPC_rectangle().x;
               target[i].getNPC_rectangle().y = target[i].getPosition_y() + target[i].getNPC_rectangle().y;


                switch (entity.getDirection()) {

                    case "interaction":
                        entity.getNPC_rectangle().y -= entity.getSpeed();//up

                        // entity.getNPC_rectangle().x -= entity.getSpeed();// left
                        // entity.getNPC_rectangle().x += entity.getSpeed();// right


                        if (entity.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {

                            entity.setCollision(true);
                            game_panel.getGui().addMessage("you  interacted with entity infront of you", 600, 100);
                            index = i;
                        }
                        entity.getNPC_rectangle().y += entity.getSpeed() * 2;// down

                        if (entity.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            entity.setCollision(true);

                            game_panel.getGui().addMessage("you  interacted with entity behind of you", 600, 100);
                            index = i;

                        }

                        entity.getNPC_rectangle().y -= entity.getSpeed();//up
                        entity.getNPC_rectangle().x -= entity.getSpeed();// left

                        if (entity.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            entity.setCollision(true);
                            game_panel.getGui().addMessage("you  interacted with entity to  you left", 600, 100);
                            index = i;

                        }
                        entity.getNPC_rectangle().x += entity.getSpeed() * 2;// right

                        if (entity.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            entity.setCollision(true);
                            game_panel.getGui().addMessage("you can interacted with entity to  you right", 600, 100);
                            index = i;

                        }
                        break;


                    case "up":
                        entity.getNPC_rectangle().y -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            System.out.println("up collision");
                            entity.setCollision(true);
                            index = i;


                        }

                        break;

                    case "down":
                        entity.getNPC_rectangle().y += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            System.out.println("down collision");
                            entity.setCollision(true);
                            index = i;


                        }
                        break;
                    case "left":
                        entity.getNPC_rectangle().x -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            System.out.println("left collision");
                            System.out.print(target[i].getCollision());
                            entity.setCollision(true);
                            index = i;


                        }
                        break;
                    case "right":
                        entity.getNPC_rectangle().x += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(target[i].getNPC_rectangle())) {
                            System.out.println("right collision");
                            entity.setCollision(true);
                            index = i;

                        }

                        break;
                }
                entity.getNPC_rectangle().x = entity.getNPC_rectangle_default_x();
                entity.getNPC_rectangle().y = entity.getNPC_rectangle_default_y();
                target[i].getNPC_rectangle().x = target[i].getNPC_rectangle_default_x();
                target[i].getNPC_rectangle().y = target[i].getNPC_rectangle_default_y();



            }




        }
        return  index;
    }

    public void checkhero (Entity entity, Hero hero){




                entity.getNPC_rectangle().x = entity.getPosition_x() + entity.getNPC_rectangle().x;
                entity.getNPC_rectangle().y = entity.getPosition_y() + entity.getNPC_rectangle().y;

                hero.getNPC_rectangle().x =  hero.getPosition_x() +  hero.getNPC_rectangle().x;
        hero.getNPC_rectangle().y =  hero.getPosition_y() +  hero.getNPC_rectangle().y;


                switch (entity.getDirection()) {

                    case "interaction":
                        entity.getNPC_rectangle().y -= entity.getSpeed();//up

                        // entity.getNPC_rectangle().x -= entity.getSpeed();// left
                        // entity.getNPC_rectangle().x += entity.getSpeed();// right


                        if (entity.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {

                            entity.setCollision(true);
                          //  game_panel.getGui().addMessage("you  interacted with entity player of you", 600, 100);

                        }
                        entity.getNPC_rectangle().y += entity.getSpeed() * 2;// down

                        if (entity.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                            entity.setCollision(true);

                          //  game_panel.getGui().addMessage("you  interacted with entity behind of you", 600, 100);


                        }

                        entity.getNPC_rectangle().y -= entity.getSpeed();//up
                        entity.getNPC_rectangle().x -= entity.getSpeed();// left

                        if (entity.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                            entity.setCollision(true);
                          //  game_panel.getGui().addMessage("you  interacted with entity to  you left", 600, 100);


                        }
                        entity.getNPC_rectangle().x += entity.getSpeed() * 2;// right

                        if (entity.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                            entity.setCollision(true);
                         //   game_panel.getGui().addMessage("you can interacted with entity to  you right", 600, 100);


                        }
                        break;


                    case "up":
                        entity.getNPC_rectangle().y -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                         //   System.out.println("up collision");
                            entity.setCollision(true);



                        }

                        break;

                    case "down":
                        entity.getNPC_rectangle().y += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                          //  System.out.println("down collision");
                            entity.setCollision(true);



                        }
                        break;
                    case "left":
                        entity.getNPC_rectangle().x -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                          //  System.out.println("left collision");
                            entity.setCollision(true);



                        }
                        break;
                    case "right":
                        entity.getNPC_rectangle().x += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects( hero.getNPC_rectangle())) {
                           // System.out.println("right collision");
                            entity.setCollision(true);


                        }

                        break;
                }
                entity.getNPC_rectangle().x = entity.getNPC_rectangle_default_x();
                entity.getNPC_rectangle().y = entity.getNPC_rectangle_default_y();
                hero.getNPC_rectangle().x =  hero.getNPC_rectangle_default_x();
                hero.getNPC_rectangle().y =  hero.getNPC_rectangle_default_y();



            }


        }
































