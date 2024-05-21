/**
 * The Objects package is responsible for handling in-game objects,
 * including interactive elements, items, and non-player characters (NPCs).
 */
package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

/**
 * The Door class represents a door object in the game.
 * It extends the Game_Object class and adds functionality for opening and closing the door.
 */
public class Door extends Game_Object{

private boolean open;

//String for messages;
String text = "";


    private long lastChangeTime;
    private static final long CHANGE_DELAY = 1000; // Задержка в миллисекундах

    /**
     * Constructor to initialize a Door object.
     * Sets initial properties for the door, such as collision, interactability, and image.
     *
     * @param game_panel The Game_panel instance to which this door belongs.
     */
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


    /**
     * Opens the door if it's closed and enough time has passed since the last state change.
     * Notifies the GUI with a message when the door is opened.
     */
   private void open(){
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

    /**
     * Closes the door if it's open and enough time has passed since the last state change.
     * Notifies the GUI with a message when the door is closed.
     */
    private void close(){
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


    /**
     * Interacts with the door object.
     * If the player has an axe, the door will open when interacted with.
     * If the door is already open, it will close.
     * If the player doesn't have an axe, a message will be displayed indicating the requirement.
     */
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
