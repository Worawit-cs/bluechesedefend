package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

    // Member data
    private int xpos, ypos, width, height;
    private BufferedImage img;
    private BufferedImage[] moveMentAni;
    private int aniTick,aniIndex,aniSpeed = 15;
    
    

    // private int xDelta = 100, yDelta = 100;

    public GamePanel(){}
    
    // for normal Panel
    public GamePanel(int width, int height, int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
        this.width = width;
        this.height = height;

        setPanelSize();
    }

    // for Panel with image
    public GamePanel(int width, int height, int xpos, int ypos, String Accessimg){
        this.width = width;
        this.height = height;
        this.xpos = xpos;
        this.ypos = ypos;
        
        setPanelSize();
        importImage(Accessimg);
        loadAnimations();
        
        /*Now We Dont Need to Repaint in panel We also repaint in Gameloop in class Game */
        // repaint();
        
    }

    public GamePanel(int xpos, int ypos, int width, int height, int row, int column, int spaceX, int spaceY){
        this.xpos = xpos;
        this.ypos = ypos;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(row, column, spaceX, spaceY));
        
        for (int i = 0; i < row*column; i++){
            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(width, height));
            cell.setBackground(Color.RED);
            p.add(cell);
        }
        
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1920,1080);
        setPreferredSize(size); // เซ็ทหน้าต่างให้มีขอบเขตโดยที่ไม่รสมตัวขอบของwindow
        setMaximumSize(size); // ไม่ต้องสนใจทำเผื่อไว้เฉยๆตัวที่ใช้จริงคือ preferred
        setMinimumSize(size); // ไม่ต้องสนใจทำเผื่อไว้เฉยๆตัวที่ใช้จริงคือ preferred
    }
    
    // Method change xDelta on our KeyBoard in class KeyBoardInputs
    public int changexDelta(int value){
        this.xpos += value;
        // repaint();
        return xpos;
    }

    // Method change yDelta on our KeyBoard in class KeyBoardInputs
    public int changeyDelta(int value){
        this.ypos += value;
        // repaint();
        return ypos;
    }

    // Method change position in class MouseInputs
    public void setRectPos(int x,int y){
        this.xpos = x;
        this.ypos = y;
        // repaint();
    }

    public int[] getPos(){
        return new int[]{xpos, ypos};
    }

    public void importImage(String Accessimg){
        
        try {
            img = ImageIO.read(new File(Accessimg));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //  repaint();
    }

    public void loadAnimations(){
        moveMentAni = new BufferedImage[4];

        for(int i=0;i<moveMentAni.length;i++){
            moveMentAni[i] = img.getSubimage(i*128, 0, 128, 128);
        }
        // repaint();
    }

    public void updateAnimation(){
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex = (aniIndex + 1)%moveMentAni.length;
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimation();

        if (moveMentAni != null){
            g.drawImage(moveMentAni[aniIndex],xpos, ypos, width, height, null);
        } else{
            g.fillRect(xpos, ypos, width, height);
        }
    }
}