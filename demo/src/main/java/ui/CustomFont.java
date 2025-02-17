package ui;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;


// import Stages.Loader;

public class CustomFont extends JPanel {
    private Font customFont;
    private int x,y,color;
    private float size;
    private boolean center;

    public CustomFont(String path,int x,int y,int color,float size,boolean center){
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = size;
        this.center = center;

        try {
            // Load the custom font from a resource file
            InputStream is = ImageButton.class.getResourceAsStream(path);
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = null;
        }
    }

    public void draw(Graphics g,String text){
        g.setFont(customFont.deriveFont(size));
        switch(color){
            case 1:
                g.setColor(Color.WHITE);
                break;
            case 2:
                g.setColor(new Color(0xff0000));
                break;
        }
        // g.setColor(Color.WHITE);
        // g.fillRect(x, y, w, h); // เอาไว้ debug
        int w=0;
        int h=0;
        if(center){
            w = g.getFontMetrics().stringWidth(text) - 30;
            h = g.getFontMetrics().getAscent() - 12;
        }
        g.drawString(text,x-w/2,y+h/2);
    }
}
