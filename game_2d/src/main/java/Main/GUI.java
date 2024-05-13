package Main;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class GUI {

    private Game_panel game_panel;
    Graphics2D g2;
    private Font font;
    private Font logFont;

    private String currentDialogue = "";
    private ArrayList<Message> messages = new ArrayList<>();
    private Timer timer = new Timer();

    private BufferedImage Main_menu_image;

    private int command_number = 1;




    public GUI(Game_panel game_panel) {
        this.game_panel = game_panel;

        setMain_menu_image();

        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/GUI/bigdonstarve.ttf"));

            this.font = customFont.deriveFont(Font.BOLD | Font.ITALIC, 70);


            Font customlogFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/GUI/bigdonstarve.ttf"));

            this.logFont = customlogFont.deriveFont(Font.BOLD | Font.ITALIC, 40);


        } catch (FontFormatException | IOException e) {
            System.out.println("Fail to load custom font: " + e.getMessage());
        }
    }


    public void setMain_menu_image(){
        Toolbox toolbox = new Toolbox();
        try {

             Main_menu_image = toolbox.scale_image((ImageIO.read(getClass().getClassLoader().getResourceAsStream("GUI/Main_menu/main.png"))), game_panel.getWindow_width(),game_panel.getWindow_height());
        }catch (Exception e){
            System.out.print("can not load main menu image");
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(font);
        g2.setColor(Color.white);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //GAME MENU
        if(game_panel.getGameState() == game_panel.getMainMenuState()){
            drawMenu();
        }

        //PAUSE MENU STATE
        if(game_panel.getGameState() == game_panel.getPause_menu()){
            drawPauseMenu();
        }

        //GAME RUNNING
        if (game_panel.getGameState() == game_panel.getPlayState()) {


            drawWithOutline(g2, "test text", 100, 100,  Color.white, Color.black);


            // Отрисовка сообщений и удаление прочитанных
            Iterator<Message> iterator = messages.iterator();
            while (iterator.hasNext()) {
                Message message = iterator.next();
                drawWithOutline(g2, message.text, message.position.x, message.position.y, Color.white, Color.black);
                if (message.isReaded) {
                    iterator.remove(); // Удаление прочитанных сообщений из списка
                }
            }
        }

        //PAUSE
        if(game_panel.getGameState() == game_panel.getPauseState()){
           drawPauseSreen();

        }

        //DIALOG
        if(game_panel.getGameState() == game_panel.getDialogState()){
            drawDialogscreen();
        }
    }

    public void drawPauseSreen(){
        String text = "Pause";
        int x = getXfortextincenter(text);
        int y = game_panel.getWindow_height()/2;

        g2.drawString(text,x,y);
    }

   public void drawPauseMenu(){
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       g2.drawImage(Main_menu_image,0,0,null);
       //GAME TITLE
       g2.setFont(g2.getFont().deriveFont(Font.BOLD,game_panel.getWindow_width()/10f));
       String text ="Survival island adventure";
       int x = getXfortextincenter(text);
       int y = game_panel.getTile_size_y() * 4;
       drawWithOutline(g2,text,x,y,new Color(68,64,60),new Color(0,0,0));


       //GAME MENU
       g2.setFont(g2.getFont().deriveFont(Font.BOLD,game_panel.getWindow_width()/25f));
       text = "Resume game";
       x = getXfortextincenter(text);
       y += game_panel.getTile_size_y()*5;
       drawWithOutline(g2,text,x,y,new Color(68,64,60),new Color(0,0,0));
       if(getCommand_number() == 1){
           drawWithOutline(g2,">",x- game_panel.getTile_size_x(),y + game_panel.getTile_size_y()/6,new Color(68,64,60),new Color(0,0,0));
       }

       g2.setFont(g2.getFont().deriveFont(Font.BOLD,game_panel.getWindow_width()/25f));
       text = "Load game";
       x = getXfortextincenter(text);
       y += game_panel.getTile_size_y()*2;
       drawWithOutline(g2,text,x,y,new Color(68,64,60),new Color(0,0,0));
       if(getCommand_number() == 2){
           drawWithOutline(g2,">",x- game_panel.getTile_size_x(),y + game_panel.getTile_size_y()/6,new Color(68,64,60),new Color(0,0,0));
       }

       g2.setFont(g2.getFont().deriveFont(Font.BOLD,game_panel.getWindow_width()/25f));
       text = "Quit";
       x = getXfortextincenter(text);
       y += game_panel.getTile_size_y()*2;
       drawWithOutline(g2,text,x,y,new Color(68,64,60),new Color(0,0,0));
       if(getCommand_number() == 3){
           drawWithOutline(g2,">",x- game_panel.getTile_size_x(),y + game_panel.getTile_size_y()/6,new Color(68,64,60),new Color(0,0,0));
       }

   }

    public void drawDialogscreen(){
        //WINDOW
        int x = game_panel.getWindow_width()/4;
        int y = game_panel.getWindow_height()/6;
        int width = game_panel.getWindow_width()/2;
        int heigth = game_panel.getTile_size_y() * 5;
        drawWindow(x,y,width,heigth);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
        x += game_panel.getTile_size_x();
        y += game_panel.getTile_size_y();
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }

    }

    public void drawMenu(){
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(Main_menu_image,0,0,null);
        //GAME TITLE
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,game_panel.getWindow_width()/10f));
        String text ="Survival island adventure";
        int x = getXfortextincenter(text);
        int y = game_panel.getTile_size_y() * 4;
        drawWithOutline(g2,text,x,y,new Color(68,64,60),new Color(0,0,0));


        //GAME MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,game_panel.getWindow_width()/25f));
        text = "Start new game";
        x = getXfortextincenter(text);
        y += game_panel.getTile_size_y()*5;
        drawWithOutline(g2,text,x,y,new Color(68,64,60),new Color(0,0,0));
        if(getCommand_number() == 1){
            drawWithOutline(g2,">",x- game_panel.getTile_size_x(),y + game_panel.getTile_size_y()/6,new Color(68,64,60),new Color(0,0,0));
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,game_panel.getWindow_width()/25f));
        text = "Load game";
        x = getXfortextincenter(text);
        y += game_panel.getTile_size_y()*2;
        drawWithOutline(g2,text,x,y,new Color(68,64,60),new Color(0,0,0));
        if(getCommand_number() == 2){
            drawWithOutline(g2,">",x- game_panel.getTile_size_x(),y + game_panel.getTile_size_y()/6,new Color(68,64,60),new Color(0,0,0));
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,game_panel.getWindow_width()/25f));
        text = "Quit";
        x = getXfortextincenter(text);
        y += game_panel.getTile_size_y()*2;
        drawWithOutline(g2,text,x,y,new Color(68,64,60),new Color(0,0,0));
        if(getCommand_number() == 3){
            drawWithOutline(g2,">",x- game_panel.getTile_size_x(),y + game_panel.getTile_size_y()/6,new Color(68,64,60),new Color(0,0,0));
        }

    }

    public void drawWindow(int x, int y, int width, int heigth){
        g2.setColor(new Color(0,0,0,200));
        g2.fillRoundRect(x,y,width,heigth,35,35);

        g2.setColor(new Color(255,255,255));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,heigth-10,25,25);
    }



    public int getXfortextincenter(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = game_panel.getWindow_width()/2 - lenght/2;
        return x;
    }

    // Метод для добавления нового сообщения
    public void addMessage(String text, int x, int y) {
        Message message = new Message(text, new Point(x, y));
        messages.add(message);

        // Запуск таймера для пометки сообщения как прочитанного через 2 секунды
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                message.isReaded = true;
            }
        }, 2000); // 2000 миллисекунд (2 секунды)
    }

    // Метод для отрисовки текста с черной рамкой
    public void drawWithOutline(Graphics2D g2, String text, int x, int y, Color textColor, Color outlineColor) {
        g2.setColor(outlineColor);
        g2.drawString(text, x - 5, y - 5);
        g2.drawString(text, x + 5, y - 5);
        g2.drawString(text, x - 5, y + 5);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(textColor);
        g2.drawString(text, x, y);
    }


    public void drawLogRenderTime(long x) {
        String renderTimeAsString = String.valueOf(x);
        g2.drawString(renderTimeAsString,getXfortextincenter(renderTimeAsString)+400,400 );
    }

    public String getCurrentDialogue() {
        return currentDialogue;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    public int getCommand_number() {
        return command_number;
    }

    public void setCommand_number(int command_number) {
        this.command_number = command_number;
    }


    private static class Message {
        String text;
        Point position;
        boolean isReaded = false;

        Message(String text, Point position) {
            this.text = text;
            this.position = position;
        }
    }















    public Game_panel getGame_panel() {
        return game_panel;
    }

    public void setGame_panel(Game_panel game_panel) {
        this.game_panel = game_panel;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }



}



