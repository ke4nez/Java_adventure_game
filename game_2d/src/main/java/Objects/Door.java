package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Door extends Game_Object{

private boolean open;


    private long lastChangeTime;
    private static final long CHANGE_DELAY = 1000; // Задержка в миллисекундах
    public Door (Game_panel game_panel){
        this.setIndex(1);
        this.setGame_panel(game_panel);
        this.setName("Door");
        this.setCollision(true);
        this.open = false;
        this.setIsinteractable(true);
        //this.setIspickeble(true);



        try{
            this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/doors.png")));

        }catch (Exception e) {
            System.out.println("cannot load door image");
        }

    }


   public void open(){
       long currentTime = System.currentTimeMillis();
       if (currentTime - lastChangeTime >= CHANGE_DELAY) {
           try {
               this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/doors_open.png")));
           } catch (Exception e) {
               System.out.println("cannot load  opened door image");
           }

           this.setCollision(false);
           this.open = true;
           lastChangeTime = currentTime;
           getGame_panel().getGui().addMessage("door now opened ", 300, 350);

       }
   }

   public void close(){
       long currentTime = System.currentTimeMillis();
       if (currentTime - lastChangeTime >= CHANGE_DELAY) {
           try {
               this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/doors.png")));
           } catch (Exception e) {
               System.out.println("cannot load  door image");
           }
           this.open = false;
           this.setCollision(true);
           getGame_panel().getGui().addMessage("door now closed ", 300, 350);
           lastChangeTime = currentTime;
       }
    }


    public boolean isopen() {
        return open;
    }

    public void setIsopen(boolean isopen) {
        this.open = isopen;
    }
}
