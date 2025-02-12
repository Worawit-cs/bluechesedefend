package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.example.*;

public class MouseInputs implements MouseListener,MouseMotionListener{
    // Data member
    private GamePanel gamePanel;
    private Boolean Attract = false;

    // Constructor

    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);
    }

    public void setAttract(Boolean Attract){
        this.Attract = Attract;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (Attract) {
            gamePanel.setRectPos(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Click!");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
           
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

}
