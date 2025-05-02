package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Entity.Player;
import java.text.DecimalFormat;
import javax.print.attribute.Size2DSyntax;
import object.OBJ_Key;



public class UI {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage keyImage;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }
    public void draw(Graphics2D g2) {
        if (gameFinished == true){
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String Text;
            int textLength;
            int x ;
            int y;
            Text = "you found the treasure";
            textLength = (int)g2.getFontMetrics().getStringBounds(Text,g2).getWidth();
             x = gp.screenWidth/2 - textLength/2;
             y = gp.screenHeight/2 - (gp.tileSize * 3);
            g2.drawString(Text,x,y);

            Text = "Your Time is:  " + dFormat.format(playtime)+ "1";
            g2.drawString("time: " +dFormat.format(playtime), gp.tileSize * 11, 65);
            textLength = (int)g2.getFontMetrics().getStringBounds(Text,g2).getWidth();
             x = gp.screenWidth/2 - textLength/2;
             y = gp.screenHeight/2 + (gp.tileSize * 4);
            g2.drawString(Text,x,y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            Text = "Congratulation";
            textLength = (int)g2.getFontMetrics().getStringBounds(Text,g2).getWidth();
             x = gp.screenWidth/2 - textLength/2;
             y = gp.screenHeight/2 + (gp.tileSize * 2);
            g2.drawString(Text,x,y);
            gp.gameThread = null;
        }else{
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x" + gp.player.hasKey,74,70);

            playtime += (double)1/60;
            g2.drawString("time: " +dFormat.format(playtime), gp.tileSize * 11, 65);
            if ( messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize* 5 );
                messageCounter ++;
                if (messageCounter > 120 ){
                    messageCounter = 0;
                messageOn = false;
                }
            }
        }
    }
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
}