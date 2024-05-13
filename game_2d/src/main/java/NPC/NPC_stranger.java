package NPC;

import Main.Game_panel;

import java.util.Random;



public class NPC_stranger extends  NPC {
    public  NPC_stranger(Game_panel game_panel){

        super(game_panel);
      this.setupNPCimages("Stranger");
      this.setSpeed(1);

    }

int counter = 1;
    public void setAction() {
        if (counter == 1) {
            Random i = new Random();
            int x = i.nextInt(1, 10);

            if (x == 1) {
                this.setDirection("stands");
            }
            if (x == 2) {
                this.setDirection("up");
            }
            if (x == 3) {
                this.setDirection("down");
            }
            if (x == 4) {
                this.setDirection("left");
            }
            if (x == 5) {
                this.setDirection("right");
            }
        }
        counter++;
        if(counter >90){
            counter = 1;
        }
    }

}
