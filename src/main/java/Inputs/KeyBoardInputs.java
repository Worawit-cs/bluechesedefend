package Inputs;

import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.example.*;

import java.awt.event.KeyEvent;

public class KeyBoardInputs implements KeyListener{
    private GamePanel gamePanel;
    
    // Constructor
    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        gamePanel.addKeyListener(this);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.changeyDelta(-5);
                break;
            case KeyEvent.VK_A:
                gamePanel.changexDelta(-5);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeyDelta(5);
                break;
            case KeyEvent.VK_D:
                gamePanel.changexDelta(5);
                break;
        
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
            
    }
}
