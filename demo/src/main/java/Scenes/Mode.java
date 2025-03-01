package Scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.example.Game;
import com.example.GameScreen;

import Stages.Loader;

import static com.example.GameStates.*;

import ui.ImageButton;

public class Mode extends GameScene implements SceneMethods{

    private Game game;
    private ImageButton Normal,Hard,BacktoMenu;
    private Loader loader;
    private BufferedImage Img;

    public Mode(Game game) {
        super(game);
        this.game = game;
        loader = new Loader();
        Img = loader.loadMap("/Assets/BG_main_manu.png");

        Normal = new ImageButton("/Assets/Botton_normal.png", 340, 328, 200, 81, 400, 162);
        Hard = new ImageButton("/Assets/Botton_hard.png", 740, 328, 200, 81, 400, 162);
        BacktoMenu = new ImageButton("/Assets/Botton_back.png", 30, 30, 200, 81, 400, 162);
    }

    @Override
    public void render(Graphics G, GameScreen screen){
        G.drawImage(Img, 0, 0,screen.getWidth(),screen.getHeight(), screen);
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
