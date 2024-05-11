package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Door extends Game_Object{

private boolean open;
    public Door (Game_panel game_panel){
        this.setIndex(1);
        this.setGame_panel(game_panel);
        this.setName("door");
        this.setCollision(true);
        this.setIsopen(false);



        try{
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/doors.png")));

        }catch (Exception e) {
            System.out.println("cannot load door image");
        }

    }


   public void open(){
        try {
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/doors/open.png")));
        }catch (Exception e){
            System.out.println("cannot load  opened door image");
        }

        this.setCollision(false);
   }

   public void close(){
       try {
           setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/doors.png")));
       }catch (Exception e){
           System.out.println("cannot load  door image");
       }

       this.setCollision(true);

    }


    public boolean isopen() {
        return open;
    }

    public void setIsopen(boolean isopen) {
        this.open = isopen;
    }
}
