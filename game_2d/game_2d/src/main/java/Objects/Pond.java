package Objects;

import Main.Game_panel;

import javax.imageio.ImageIO;

public class Pond extends Game_Object{

    private boolean hasBulp;



    public Pond(Game_panel game_panel){
            super();
            this.setGame_panel(game_panel);
            this.setName("Pond");
            this.setCollision(true);
            this.setIsinteractable(true);
            this.hasBulp = true;

            try{
                this.setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("Objects/pond.png")));

            }catch (Exception e) {
                System.out.println("cannot load door image");
            }

        }

    public boolean isHasBulp() {
        return hasBulp;
    }

    public void setHasBulp(boolean hasBulp) {
        this.hasBulp = hasBulp;
    }
}
