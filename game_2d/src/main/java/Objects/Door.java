package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Door extends Game_Object{

private boolean open;

//String for messages;
String text = "";


    private long lastChangeTime;
    private static final long CHANGE_DELAY = 1000; // Задержка в миллисекундах
    public Door (Game_panel game_panel){
        super(game_panel);
        this.setGame_panel(game_panel);
        this.setIndex(1);
        this.setName("Door");
        this.setCollision(true);
        this.getObject_rectangle().setRect(8,10,getObj_rectangle_width()-10,getObj_rectangle_height()-10);
        this.open = false;
        this.setIsinteractable(true);



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
           text = "Door now opened";
           getGame_panel().getGui().addMessage(text,getGame_panel().getGui().getXfortextincenter(text),
                   getGame_panel().getGui().getYForCenterinGameMessage());
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
           text = "Door now closed";
           getGame_panel().getGui().addMessage(text,getGame_panel().getGui().getXfortextincenter(text),
                   getGame_panel().getGui().getYForCenterinGameMessage());

           lastChangeTime = currentTime;
       }
    }



    public void interactObject() {

        if (getGame_panel().getHero().getItemInHeands() != null) {
            if (isopen()) {
                close();
            }
            else if (!isopen() && getGame_panel().getHero().getItemInHeands().getName() == "Axe") {
                open();
            } else {
                text = "You need axe to open doors";
                getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                        getGame_panel().getGui().getYForCenterinGameMessage());
            }
        }
        else {
            text = "You need axe to open doors";
            getGame_panel().getGui().addMessage(text, getGame_panel().getGui().getXfortextincenter(text),
                    getGame_panel().getGui().getYForCenterinGameMessage());
        }

    }


    public boolean isopen() {
        return open;
    }

    public void setIsopen(boolean isopen) {
        this.open = isopen;
    }
}
