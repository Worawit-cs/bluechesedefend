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
import ui.ImageButton;

public class GameOver extends GameScene implements SceneMethods {
    private Game game;
    private CustomFont ModeText;
    private ImageButton Replay, NormalMode, HardMode;
    private Loader loader = new Loader();
    private BufferedImage BG,Victory,Lose;

    public GameOver(Game game){
        super(game);
        this.game = game;
        initButtons();
    }
    
    public void initButtons(){

        BG = loader.loadMap("/Assets/BG_main_manu.png");
        Victory = loader.loadMap("/Assets/Victory.png");
        Lose = loader.loadMap("/Assets/Lose.png");

        NormalMode = new ImageButton("/Assets/Botton_normal.png", 565, 280,
         150, 51, 400, 162);
        HardMode = new ImageButton("/Assets/Botton_hard.png", 565, 280, 
        150, 51, 400, 162);

        Replay = new ImageButton("/Assets/Botton_retry.png", 
        540, 400, 200, 81, 400, 162);
    }   

    @Override
    public void render(Graphics G, GameScreen screen) {
        G.drawImage(BG, 0, 0,screen.getWidth(),screen.getHeight(), screen);
        Replay.draw(G);

        // congratText
        if(game.getPlaying().isWin()){
            G.drawImage(Victory, 205, 65, 870, 270, null);
        }else{
            G.drawImage(Lose, 205, 65, 870, 270, null);
        }

        // ModeText
        if (game.getPlaying().getMode() == "Normal"){
            NormalMode.draw(G);
        } else {
            HardMode.draw(G);
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (Replay.getBounds().contains(x, y)){
            Replay.setMouseClick(true);
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
