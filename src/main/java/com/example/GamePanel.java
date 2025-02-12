package com.example;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
// <<<<<<< HEAD
    // Member data
    private int xpos, ypos, width, height;
    private BufferedImage img;
    private BufferedImage[] moveMentAni;
    private int aniTick,aniIndex,aniSpeed = 15;
    
    // Method
// =======
    // private int xDelta = 100, yDelta = 100;
    
// >>>>>>> a75c4af1cdf92f0815524474d60d6a7080067738
    public GamePanel(int width, int height, int xpos, int ypos){
        this.width = width;
        this.height = height;
        this.xpos = xpos;
        this.ypos = ypos;
        // importImage();
        // loadAnimations();
    }
    
    // Method change xDelta on our KeyBoard in class KeyBoardInputs
    public void changexDelta(int value){
        this.xpos += value;
        repaint();
    }

    // Method change yDelta on our KeyBoard in class KeyBoardInputs
    public void changeyDelta(int value){
        this.ypos += value;
        repaint();
    }

    // Method change position in class MouseInputs
    public void setRectPos(int x,int y){
        this.xpos = x;
        this.ypos = y;
        repaint();
    }

    public int[] getPos(){
        return new int[]{xpos, ypos};
    }

    public int[] getWh(){
        return new int[]{width, height};
    }

    // protected void importImage(){
    //     InputStream is = getClass().getResourceAsStream("/Asset/Monster/dr_prakarn.png");

    //     if(is == null){
    //         System.out.println("Cannot find your Resorces!");
    //     }

    //     try {
    //         img = ImageIO.read(is);
    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }

    // protected void loadAnimations(){
    //     moveMentAni = new BufferedImage[4];

    //     for(int i=0;i<moveMentAni.length;i++){
    //         moveMentAni[i] = img.getSubimage(i*128, 0, 128, 128);
    //     }
    // }

    // protected void updateAnimation(){
    //     aniTick++;
    //     if(aniTick>=aniSpeed){
    //         aniTick=0;
    //         aniIndex++;
    //         if(aniIndex >= moveMentAni.length){
    //             aniIndex = 0;
    //         }
    //     }
    // }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // updateAnimation();
        
        // g.drawImage(moveMentAni[aniIndex],xpos, ypos,64,64, null);
        g.fillRect(xpos, ypos, width, height);
    }
}