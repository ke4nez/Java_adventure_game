/**
 * The Main package is responsible for managing the core aspects of the game,
 * such as level loading, collision detection, game controls, game panel,
 * user interface. And image scaling.
 */
package Main;

import Entity.Entity;
import Entity.Hero;
import java.util.ArrayList;

/**
 * The CollisionChecker class is responsible for detecting collisions between entities and tiles or objects in the game world.
 * It provides methods to check for collisions with tiles and objects based on the movement direction of the entity.
 */
public class CollisionChecker {
    Game_panel game_panel;

    /**
     * Constructs a CollisionChecker with a reference to the Game_panel.
     *
     * @param game_panel The Game_panel object representing the game environment.
     */
    public CollisionChecker(Game_panel game_panel) {
        this.game_panel = game_panel;
    }

    /**
     * Checks for collisions between the given entity and tiles in the game world based on its movement direction.
     * Updates the entity's collision flag if collision occurs with any impassable tile.
     *
     * @param entity The Entity object whose collision with tiles needs to be checked.
     */
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
            checktiles(tile_1_number,tile_2_number,entity);

        }

        if (entity.getDirection() == "down") {
            npc_bottom_row = (npc_rectangle_bottom_y + entity.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_bottom_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_bottom_row);
            checktiles(tile_1_number,tile_2_number,entity);
        }


        if (entity.getDirection() == "right") {
            npc_right_col = (npc_rectangle_right_x + entity.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_right_col, npc_bottom_row);
            checktiles(tile_1_number,tile_2_number,entity);
        }


        if (entity.getDirection() == "left") {
            npc_left_col = (npc_rectangle_left_x - entity.getSpeed()) / game_panel.getTile_size_x();
            tile_1_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_top_row);
            tile_2_number = game_panel.getTileManager().getTileNumberFromMap(npc_left_col, npc_bottom_row);
            checktiles(tile_1_number,tile_2_number,entity);
        }
    }

    //ENTITY TO TILES COLLISION
    private void checktiles(int tile_1_number, int tile_2_number, Entity entity){
        if (game_panel.getTileManager().getTileByNumber(tile_1_number).getIfNotPasseble() == true ||
                game_panel.getTileManager().getTileByNumber(tile_2_number).getIfNotPasseble() == true) {
            entity.setCollision(true);
        }
    }

    /**
     * Checks for collisions between the given entity and game objects in the game world.
     * Determines the collision based on the entity's movement direction and updates its collision flag accordingly.
     *
     * @param entity The Entity object whose collision with game objects needs to be checked.
     * @return The index of the colliding object in the game_panel's object list, or 99 if no collision occurs.
     */
    public int check_object(Entity entity) {

        int index = 99;

        for (int i = 0; i < game_panel.getObj().size(); i++) {
            if (game_panel.getObjFromObjects(i) != null) {
                entity.getNPC_rectangle().x = entity.getPosition_x() + entity.getNPC_rectangle().x;
                entity.getNPC_rectangle().y = entity.getPosition_y() + entity.getNPC_rectangle().y;

                game_panel.getObjFromObjects(i).getObject_rectangle().x = game_panel.getObjFromObjects(i).getPosition_x() + game_panel.getObjFromObjects(i).getObject_rectangle().x;
                game_panel.getObjFromObjects(i).getObject_rectangle().y = game_panel.getObjFromObjects(i).getPosition_y() + game_panel.getObjFromObjects(i).getObject_rectangle().y;


                switch (entity.getDirection()) {

                    case "interaction":
                        entity.getNPC_rectangle().y -= entity.getSpeed();//up

                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            entity.setCollision(true);
                            return (index = i);
                        }
                        entity.getNPC_rectangle().y += entity.getSpeed() * 2;// down

                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            entity.setCollision(true);
                            return (index = i);
                        }
                        entity.getNPC_rectangle().y -= entity.getSpeed();//up
                        entity.getNPC_rectangle().x -= entity.getSpeed();// left

                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            entity.setCollision(true);

                            return (index = i);
                        }
                        entity.getNPC_rectangle().x += entity.getSpeed() * 2;// right

                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            entity.setCollision(true);
                            return (index = i);
                        }
                        break;


                    case "up":
                        entity.getNPC_rectangle().y -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                entity.setCollision(true);
                            }
                            index = i;
                        }
                        break;
                    case "down":
                        entity.getNPC_rectangle().y += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                entity.setCollision(true);
                            }
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getNPC_rectangle().x -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
                            if (game_panel.getObjFromObjects(i).isCollision() == true) {
                                entity.setCollision(true);
                            }
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getNPC_rectangle().x += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(game_panel.getObjFromObjects(i).getObject_rectangle())) {
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

    /**
     * Checks for collisions between the specified entity and a list of target entities (NPCs).
     * Updates the collision flag of the entity if a collision is detected.
     *
     * @param entity The entity for which collision is being checked.
     * @param target The list of target entities (NPCs) to check collision against.
     * @return The index of the target entity with which collision occurred, or 99 if no collision.
     */
    public int checknpc(Entity entity, ArrayList<Entity> target) {

        int index = 99;


        for (int i = 0; i < target.size(); i++) {
            if (target.get(i) != null) {

                entity.getNPC_rectangle().x = entity.getPosition_x() + entity.getNPC_rectangle().x;
                entity.getNPC_rectangle().y = entity.getPosition_y() + entity.getNPC_rectangle().y;

                target.get(i).getNPC_rectangle().x = target.get(i).getPosition_x() + target.get(i).getNPC_rectangle().x;
                target.get(i).getNPC_rectangle().y = target.get(i).getPosition_y() + target.get(i).getNPC_rectangle().y;


                switch (entity.getDirection()) {
                    case "interaction":

                        entity.getNPC_rectangle().y -= entity.getSpeed();//up

                        if (entity.getNPC_rectangle().intersects(target.get(i).getNPC_rectangle())) {
                            entity.setCollision(true);
                            index = i;
                        }

                        entity.getNPC_rectangle().y += entity.getSpeed() * 2;// down

                        if (entity.getNPC_rectangle().intersects(target.get(i).getNPC_rectangle())) {
                            entity.setCollision(true);
                            index = i;

                        }

                        entity.getNPC_rectangle().y -= entity.getSpeed();//up
                        entity.getNPC_rectangle().x -= entity.getSpeed();// left

                        if (entity.getNPC_rectangle().intersects(target.get(i).getNPC_rectangle())) {
                            entity.setCollision(true);
                            index = i;
                        }

                        entity.getNPC_rectangle().x += entity.getSpeed() * 2;// right

                        if (entity.getNPC_rectangle().intersects(target.get(i).getNPC_rectangle())) {
                            entity.setCollision(true);
                            index = i;
                        }
                        break;
                    case "up":
                        entity.getNPC_rectangle().y -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(target.get(i).getNPC_rectangle())) {
                            entity.setCollision(true);
                            index = i;
                        }

                        break;
                    case "down":
                        entity.getNPC_rectangle().y += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(target.get(i).getNPC_rectangle())) {
                            entity.setCollision(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getNPC_rectangle().x -= entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(target.get(i).getNPC_rectangle())) {
                            entity.setCollision(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getNPC_rectangle().x += entity.getSpeed();
                        if (entity.getNPC_rectangle().intersects(target.get(i).getNPC_rectangle())) {
                            entity.setCollision(true);
                            index = i;
                        }
                        break;
                }
                entity.getNPC_rectangle().x = entity.getNPC_rectangle_default_x();
                entity.getNPC_rectangle().y = entity.getNPC_rectangle_default_y();
                target.get(i).getNPC_rectangle().x = target.get(i).getNPC_rectangle_default_x();
                target.get(i).getNPC_rectangle().y = target.get(i).getNPC_rectangle_default_y();
            }
        }
        return  index;
    }

    /**
     * Checks for collisions between the specified entity and the hero.
     * Updates the collision flag of the entity if a collision is detected.
     *
     * @param entity The entity for which collision is being checked.
     * @param hero   The hero entity to check collision against.
     */
    public void checkhero (Entity entity, Hero hero) {
        entity.getNPC_rectangle().x = entity.getPosition_x() + entity.getNPC_rectangle().x;
        entity.getNPC_rectangle().y = entity.getPosition_y() + entity.getNPC_rectangle().y;

        hero.getNPC_rectangle().x = hero.getPosition_x() + hero.getNPC_rectangle().x;
        hero.getNPC_rectangle().y = hero.getPosition_y() + hero.getNPC_rectangle().y;


        switch (entity.getDirection()) {

            case "interaction":
                entity.getNPC_rectangle().y -= entity.getSpeed();//up


                if (entity.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                    entity.setCollision(true);
                }
                entity.getNPC_rectangle().y += entity.getSpeed() * 2;// down

                if (entity.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                    entity.setCollision(true);
                }

                entity.getNPC_rectangle().y -= entity.getSpeed();//up
                entity.getNPC_rectangle().x -= entity.getSpeed();// left

                if (entity.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                    entity.setCollision(true);
                }
                entity.getNPC_rectangle().x += entity.getSpeed() * 2;// right

                if (entity.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                    entity.setCollision(true);
                }
                break;


            case "up":
                entity.getNPC_rectangle().y -= entity.getSpeed();
                if (entity.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                    entity.setCollision(true);
                }
                break;
            case "down":
                entity.getNPC_rectangle().y += entity.getSpeed();
                if (entity.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                    entity.setCollision(true);
                }
                break;
            case "left":
                entity.getNPC_rectangle().x -= entity.getSpeed();
                if (entity.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                    entity.setCollision(true);
                }
                break;
            case "right":
                entity.getNPC_rectangle().x += entity.getSpeed();
                if (entity.getNPC_rectangle().intersects(hero.getNPC_rectangle())) {
                    entity.setCollision(true);
                }
                break;
        }
        entity.getNPC_rectangle().x = entity.getNPC_rectangle_default_x();
        entity.getNPC_rectangle().y = entity.getNPC_rectangle_default_y();
        hero.getNPC_rectangle().x = hero.getNPC_rectangle_default_x();
        hero.getNPC_rectangle().y = hero.getNPC_rectangle_default_y();

    }
}
































