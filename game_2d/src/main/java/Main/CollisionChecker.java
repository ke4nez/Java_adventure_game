package Main;

import NPC.NPC;

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
            }

        }

        if (npc.getDirection() == "down") {
            npc_bottom_row = (npc_rectangle_bottom_y + npc.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_bottom_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_bottom_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                npc.setCollision(true);
            }

        }

        if (npc.getDirection() == "right") {
            npc_right_col = (npc_rectangle_right_x + npc.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_bottom_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                npc.setCollision(true);
            }
        }


        if (npc.getDirection() == "left") {
            npc_left_col = (npc_rectangle_left_x - npc.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_bottom_row);
            if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                    game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
                npc.setCollision(true);
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
                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())){
                            npc.setCollision(true);
                            return (index = i);
                        }
                        npc.getNPC_rectangle().y += npc.getSpeed()*2;// down

                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            npc.setCollision(true);
                            return (index = i);
                        }

                        npc.getNPC_rectangle().y -= npc.getSpeed();//up
                        npc.getNPC_rectangle().x -= npc.getSpeed();// left

                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            npc.setCollision(true);
                            return (index = i);
                        }
                        npc.getNPC_rectangle().x += npc.getSpeed()*2;// right

                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            npc.setCollision(true);
                            return (index = i);
                        }
                        break;
                    case "up":
                        npc.getNPC_rectangle().y -= npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            System.out.println("up collision");
                            if(game_panel.getObjFromObjects(i).isCollision() == true){
                                npc.setCollision(true);
                            }
                            index = i;
                        }

                        break;

                    case "down":
                        npc.getNPC_rectangle().y += npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            System.out.println("down collision");
                            if(game_panel.getObjFromObjects(i).isCollision() == true){
                                npc.setCollision(true);
                            }
                            index = i;
                        }
                        break;
                    case "left":
                    npc.getNPC_rectangle().x -= npc.getSpeed();
                    if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                        System.out.println("left collision");
                        if(game_panel.getObjFromObjects(i).isCollision() == true){
                            npc.setCollision(true);
                        }
                        index = i;
                    }
                    break;
                    case "right":
                        npc.getNPC_rectangle().x += npc.getSpeed();
                        if (npc.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            System.out.println("right collision");
                            if(game_panel.getObjFromObjects(i).isCollision() == true){
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
}



























