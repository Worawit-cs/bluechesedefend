package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Stages.Loader;

public class ImageButton {
    private int x,y,w,h,pixX,pixY;
    private Rectangle bounds;
    private boolean mouseOver, mouseClicked;
    private BufferedImage Img;
    private Loader loader;

    private boolean clone = false;
    private int xMouse, yMouse, cWidth, cHeight;

    private long tick = 0;
    private int debouceTime = 75;

    public ImageButton(String path,int x, int y, int width, int height, int pixX, int pixY){
        loader = new Loader();
        Img = loader.loadMap(path);
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.pixX = pixX;
        this.pixY = pixY;
        initBounds();
    }

    private void initBounds(){
        this.bounds = new Rectangle(x,y,w,h); //สร้าง Hitbox สำหรับการกด
    }

    public void draw(Graphics G){
        // G.setColor(Color.WHITE); /// ตอนเทสว่าปุ่ม hitbox มันตรงมั้ย
        // G.fillRect(x, y, w, h); /// ตอนเทสว่าปุ่ม hitbox มันตรงมั้ย
        if (mouseClicked){
            G.drawImage(Img.getSubimage(pixX, 0, pixX, pixY), x, y, w, h, null);
            if (System.currentTimeMillis() - tick >= debouceTime){ mouseClicked = false; }

        } else if (mouseOver){
            G.drawImage(Img.getSubimage(2*pixX, 0, pixX, pixY), x, y, w, h, null);
        } else {
            G.drawImage(Img.getSubimage(0, 0, pixX, pixY), x, y, w, h, null);
        }

        if (clone){
            G.drawImage(Img.getSubimage(0, 0, pixX, pixY), xMouse, yMouse, cWidth, cHeight, null);
        }
    }
    
    public void removeClone(){
        clone = false;
    }

    public void setClonePos(int x, int y){
        xMouse = x;
        yMouse = y;
    }

    public void createMouseClone(int x, int y){
        clone = true;
        xMouse = x;
        yMouse = y;
        cWidth = (int)(w/1.5);
        cHeight = (int)(h/1.5);
    }

    public void setMouseClick(boolean mouseClicked){
        this.mouseClicked = mouseClicked;
        tick = System.currentTimeMillis();
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }

    public Rectangle getBounds(){
        return bounds;
    }

}
