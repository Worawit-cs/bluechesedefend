package com.example;

import javax.swing.JFrame;
public class GameWindow extends JFrame {

    public GameWindow(){

        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setExtendedState(MAXIMIZED_BOTH);

        //setUndecorated(true); -- make Undecorated
        setVisible(true); // -- visible frame
    }

    public void addGamePanel(GamePanel gamePanel){
        add(gamePanel); // add image
    }
}