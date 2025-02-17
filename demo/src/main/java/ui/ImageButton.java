package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Stages.Loader;

public class ImageButton {
    private int x,y,w,h,i;
    private Rectangle bounds;
    private boolean mouseOver;
    private BufferedImage Img;
    private Loader loader;

    public ImageButton(String path,int x, int y, int width, int height){
        loader = new Loader();
        Img = loader.loadMap(path);
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        initBounds();
    }

    private void initBounds(){
        this.bounds = new Rectangle(x,y,w,h); //สร้าง Hitbox สำหรับการกด
    }

    public void draw(Graphics G){
        // G.setColor(Color.WHITE); /// ตอนเทสว่าปุ่ม hitbox มันตรงมั้ย
        // G.fillRect(x, y, w, h); /// ตอนเทสว่าปุ่ม hitbox มันตรงมั้ย
        G.drawImage(Img.getSubimage(i*320, 0, 320, 120), x, y, w, h, null);
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
        if(mouseOver){ i=1; }
        else{ i=0; }
    }

    public Rectangle getBounds(){
        return bounds;
    }

}
