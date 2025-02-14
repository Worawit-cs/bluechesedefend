package Scenes;

import java.awt.Color;
import java.awt.Graphics;

import com.example.Game;

public class Settings extends GameScene implements SceneMethods{

    public Settings(Game game) {
        super(game);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void render(Graphics G){
        G.setColor(Color.BLUE);
        G.fillRect(0,0,640,640);
        
    }

    @Override
    public void mouseClicked(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mouseMoved(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }
    
}
