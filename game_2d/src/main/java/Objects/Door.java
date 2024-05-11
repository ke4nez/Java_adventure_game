package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Door extends Game_Object{


    public Door (Game_panel game_panel){
        this.setIndex(1);
        this.setGame_panel(game_panel);
        this.setName("door");
        this.setCollision(true);



        try{
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/doors.png")));

        }catch (Exception e) {
            System.out.println("cannot load door image");
        }

    }




}
