package Scenes;

import static com.example.GameStates.MENU;
import static com.example.GameStates.SetGameState;

import java.awt.Graphics;

import com.example.Game;
import com.example.GameScreen;

import ui.Button;
import ui.CustomFont;

public class GameOver extends GameScene implements SceneMethods {
    private Game game;
    private CustomFont CongratText, ModeText;
    private Button Replay;

    public GameOver(Game game){
        super(game);
        this.game = game;
        initButtons();
    }
    
    public void initButtons(){
        int w = 200;
        int h = 75;
        CongratText = new CustomFont("/Font/number.ttf", 500, 300,3,25f,false);
        ModeText = new CustomFont("/Font/number.ttf", 500, 330,2,25f,false);
        Replay = new Button("Replay",640 - (int)w/2,210 - (int)h/2,w,h);
    }   

    @Override
    public void render(Graphics G, GameScreen screen) {
        Replay.draw(G);
        
        // congratText
        if(game.getPlaying().isWin()){
            CongratText.changeColor(3);
            CongratText.draw(G, "VICTORY");
        }else{
            CongratText.changeColor(2);
            CongratText.draw(G, "LOSE");
        }

        ModeText.draw(G, game.getPlaying().getMode());
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (Replay.getBounds().contains(x, y)){
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(int x, int y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(int x, int y) {
        // TODO Auto-generated method stub
        
    }
}
