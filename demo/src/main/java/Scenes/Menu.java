package Scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.example.Game;
import com.example.GameScreen;

import Stages.Loader;

import static com.example.GameStates.*;
import ui.ImageButton;

public class Menu extends GameScene implements SceneMethods{

    private BufferedImage Img;
    private Loader loader;
    private ArrayList<BufferedImage> Assets = new ArrayList<>(); //เก็บไฟล์รูปภาพหลายๆอันได้

    private ImageButton PlayButton, QuitButton;
    public Menu(Game game) {
        super(game);
        loader = new Loader();
        Img = loader.loadMap("/Assets/BG_main_manu.png");
        // importImg("/Assets/BG.png");
        initButtons();
    }

    public void initButtons(){
        int w = 200;
        int h = 75;
        
        PlayButton = new ImageButton("/Assets/Botton_start.png", 540, 350, 200, 81, 400, 162);
        QuitButton = new ImageButton("/Assets/Botton_quit.png", 540, 460, 200, 81, 400, 162);

    }   

    @Override
    public void render(Graphics G, GameScreen screen){
        //G.drawImage(Img ,0 ,0 ,null); // background for menu
        G.drawImage(Img, 0, 0,screen.getWidth(),screen.getHeight(), screen);
        drawButtons(G);
    }

    private void drawButtons(Graphics G){
        PlayButton.draw(G);
        QuitButton.draw(G);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(PlayButton.getBounds().contains(x,y)){
            SetGameState(MODE);
        }
        else if(QuitButton.getBounds().contains(x,y)){
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        PlayButton.setMouseOver(false);
        QuitButton.setMouseOver(false);
        if(PlayButton.getBounds().contains(x,y)){
            PlayButton.setMouseOver(true);
        }
        else if(QuitButton.getBounds().contains(x,y)){
            QuitButton.setMouseOver(true);
        }

    }
    
    @Override
    public void mouseReleased(int x, int y) {
    }

    @Override
    public void mousePressed(int x, int y) {
    }
}
