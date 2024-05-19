package Entity;

import Main.Game_panel;

import java.io.*;
import java.util.Random;





public class NPC_stranger extends Entity {
    int counter = 1; //COUNTER TO SET RANDOM DIRECTION TO NPS
    public  NPC_stranger(Game_panel game_panel){
        super(game_panel);
        this.setSpeed(1);
        this.setName("Stranger");
        this.setupNPCimages("Stranger");
        setDialogue(game_panel.getHero().getOn_level_number());
    }
    //LOAD DIALOGUES FROM NPC FOLDER FROM LEVEL FOLDER
    public void setDialogue(int level_number) {
        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream("Levels/Level_" + level_number+ "/Stranger/Dialogue.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
               this.dialogues.add(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }




    public void speak(){
        super.speak();
    }






    //AI WALKING
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
