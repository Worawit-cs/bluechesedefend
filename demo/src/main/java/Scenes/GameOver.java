package Scenes;

import static com.example.GameStates.MENU;
import static com.example.GameStates.SetGameState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.example.Game;
import com.example.GameScreen;

import Stages.Loader;
import ui.Button;
import ui.CustomFont;

public class GameOver extends GameScene implements SceneMethods {
    private Game game;
    private CustomFont ModeText;
    private Button Replay;
    private Loader loader = new Loader();
    private BufferedImage BG,Victory,Lose;

    public GameOver(Game game){
        super(game);
        this.game = game;
        initButtons();
    }
    
    public void initButtons(){
        int w = 200;
        int h = 75;
        BG = loader.loadMap("/Assets/BG_main_manu.png");
        Victory = loader.loadMap("/Assets/Victory.png");
        Lose = loader.loadMap("/Assets/Lose.png");
        Replay = new Button("Replay",640 - (int)w/2,400 - (int)h/2,w,h);
    }   

    @Override
    public void render(Graphics G, GameScreen screen) {
        G.drawImage(BG, 0, 0,screen.getWidth(),screen.getHeight(), screen);
        Replay.draw(G);
        
        // congratText
        if(game.getPlaying().isWin()){
            G.drawImage(Victory, 640 - 435, 200 - 135, 870, 270, null);
        }else{
            G.drawImage(Lose, 640 - 435, 200 - 135, 870, 270, null);
        }

        // ModeText.draw(G, game.getPlaying().getMode());
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (Replay.getBounds().contains(x, y)){
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        Replay.setMouseOver(false);
        if(Replay.getBounds().contains(x,y)){
            Replay.setMouseOver(true);
        }
        
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
