package com.example;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;



public class GamePanel extends JPanel{

    // Member data
    private float xpos, ypos;
    private int width, height;

    // private int xDelta = 100, yDelta = 100;

    public GamePanel(){
        setPanelSize();
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1920,1080);
        setPreferredSize(size); // เซ็ทหน้าต่างให้มีขอบเขตโดยที่ไม่รสมตัวขอบของwindow
        setMaximumSize(size); // ไม่ต้องสนใจทำเผื่อไว้เฉยๆตัวที่ใช้จริงคือ preferred
        setMinimumSize(size); // ไม่ต้องสนใจทำเผื่อไว้เฉยๆตัวที่ใช้จริงคือ preferred
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public void update(){
        repaint();
    }
}