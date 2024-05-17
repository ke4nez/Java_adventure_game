package Entity;

import Main.Game_panel;

import java.util.Random;





public class NPC_stranger extends Entity {
    public  NPC_stranger(Game_panel game_panel){

        super(game_panel);
        this.setSpeed(1);
        this.setupNPCimages("Stranger");
        setDialogue();

    }

int counter = 1;


    public void setDialogue(){
        dialogues[0] = "Hello, stranger";
        dialogues[1] = "ye, i see";
        dialogues[2] = "you hunting for treasure";
        dialogues[3] = "la-la-la-la-la-la-la-la-la-la-la-la-la-la-la-la-la-la-\n la-la-la-la-la -la-la-la-la\n-la-la-la-la-la a-la-la-la-la-la-la-la-la-la-la-la-la-la-la-la";
    }




    public void speak(){
        super.speak();
    }







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
