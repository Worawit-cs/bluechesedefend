package Scenes;

import java.awt.Graphics;

import com.example.Game;
import com.example.GameScreen;

import static com.example.GameStates.*;

import ui.Button;

public class Mode extends GameScene implements SceneMethods{

    private Game game;
    private Button Normal,Hard,BacktoMenu;

    public Mode(Game game) {
        super(game);
        this.game = game;

        int w = 200;
        int h = 75;
        Normal = new Button("Normal",440 - (int)w/2,360 - (int)h/2,w,h);
        Hard = new Button("Hard",840 - (int)w/2,360 - (int)h/2,w,h);
        BacktoMenu = new Button("Back",30,30, w ,h);
    }

    @Override
    public void render(Graphics G, GameScreen screen){
        Normal.draw(G);
        Hard.draw(G);
        BacktoMenu.draw(G);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(Normal.getBounds().contains(x,y)){
            game.getPlaying().start("Normal");
            SetGameState(PLAYING);
        }
        else if(Hard.getBounds().contains(x,y)){
            // setmonster เลือดเยอะขึ้น (ทำทีหลัง)
            game.getPlaying().start("Hard");
            SetGameState(PLAYING);
        }
        else if(BacktoMenu.getBounds().contains(x,y)){
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        Normal.setMouseOver(false);
        Hard.setMouseOver(false);
        BacktoMenu.setMouseOver(false);
        if(Normal.getBounds().contains(x,y)){
            Normal.setMouseOver(true);
        }
        else if(Hard.getBounds().contains(x,y)){
            Hard.setMouseOver(true);
        }
        else if(BacktoMenu.getBounds().contains(x,y)){
            BacktoMenu.setMouseOver(true);
        }
    }
    
    @Override
    public void mouseReleased(int x, int y) {
    }

    @Override
    public void mousePressed(int x, int y) {
    }
}
