package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.example.Game;
import com.example.GameStates;

public class Keyboard implements KeyListener{

    private Game game;
    public Keyboard(Game game){
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("A is pressed");
        }
        switch(GameStates.gameState){
            case MODE:
                break;
            case GAME_OVER:
                break;
            case MENU:
                break;
            case PLAYING:
                game.getPlaying().keyPressed(e);
                break;
            case SETTINGS:
                break;
            default:
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
