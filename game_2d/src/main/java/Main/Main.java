package Main;

import javax.swing.JFrame;



public class Main {

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("2d_game");
        window.setResizable(false);

        Game_panel game_panel = new Game_panel();

        game_panel.set_game_panel();

        window.add(game_panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);



        game_panel.start_game();

    }
}
