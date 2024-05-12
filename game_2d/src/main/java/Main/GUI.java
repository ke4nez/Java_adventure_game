package Main;
import java.awt.*;
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
    private ArrayList<Message> messages = new ArrayList<>();
    private Timer timer = new Timer();

    public GUI(Game_panel game_panel) {
        this.game_panel = game_panel;
        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/GUI/bigdonstarve.ttf"));

            this.font = customFont.deriveFont(Font.BOLD | Font.ITALIC, 70);


            Font customlogFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/GUI/bigdonstarve.ttf"));

            this.logFont = customlogFont.deriveFont(Font.BOLD | Font.ITALIC, 40);


        } catch (FontFormatException | IOException e) {
            System.out.println("Fail to load custom font: " + e.getMessage());
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(font);
        g2.setColor(Color.white);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
        if(game_panel.getGameState() == game_panel.getPauseState()){
           drawPauseSreen();

        }
    }

    public void drawPauseSreen(){
        String text = "Pause";
        int x = getXfortextincenter(text);
        int y = game_panel.getWindow_height()/2;

        g2.drawString(text,x,y);
    }

    public int getXfortextincenter(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = game_panel.getWindow_width()/2 - lenght;
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
        g2.drawString(text, x - 1, y - 1);
        g2.drawString(text, x + 1, y - 1);
        g2.drawString(text, x - 1, y + 1);
        g2.drawString(text, x + 1, y + 1);

        g2.setColor(textColor);
        g2.drawString(text, x, y);
    }


    public void drawLogRenderTime(long x) {
        String renderTimeAsString = String.valueOf(x);
        g2.drawString(renderTimeAsString,getXfortextincenter(renderTimeAsString)+400,400 );
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



