package com.example;

import javax.swing.JPanel;

import inputs.Keyboard;
import inputs.Mouse;

import java.awt.Dimension;
import java.awt.Graphics;


public class GameScreen extends JPanel{
    private Game game;
    private Dimension size;

    private Mouse mouse;
    private Keyboard keyboard;
    
    public GameScreen(Game game){
        this.game = game;
        setPanelSize();
    }

    void initInputs(){
        mouse = new Mouse(game);
        keyboard = new Keyboard();

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addKeyListener(keyboard);

        requestFocus();
    }

    private void setPanelSize() {
		size = new Dimension(1920, 1080);

		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}
    
    public void paintComponent(Graphics G){
        // ฝรั่งบอกว่า เรียก superclass เพื่อให้มันทำพวก graphics ย่อยๆที่เราไม่จำเป็นต้องไปยุ่งเกี่ยวกับมัน
        super.paintComponent(G);
        game.getRender().render(G, this);

    }
    
}
