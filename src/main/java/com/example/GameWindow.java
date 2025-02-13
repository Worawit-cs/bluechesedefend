package com.example;

import java.awt.Component;

import javax.swing.JFrame;
public class GameWindow extends JFrame {
    // list of gamePanel ?

    public GameWindow(){

        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setExtendedState(MAXIMIZED_BOTH);

        //setUndecorated(true); -- make Undecorated
        setVisible(true); // -- visible frame
    }

    @Override
    public void remove(Component comp) {
        // TODO Auto-generated method stub
        super.remove(comp);
        this.repaint();
    }
}