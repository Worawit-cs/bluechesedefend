package Scenes;

import static com.example.GameStates.GAME_OVER;
import static com.example.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.print.MultiDocPrintService;

import com.example.Game;
import com.example.GameScreen;

import Manager.MonsterManager;
import Stages.Loader;

public class Playing extends GameScene implements SceneMethods{

    private Loader loader;
    private BufferedImage Img;  
    private MonsterManager monstermanager;
    private long tick = 0;

    public Playing(Game game) {
        super(game);
        loader = new Loader();
        Img = loader.loadMap("/Assets/BG.png");

        monstermanager = new MonsterManager(this);
        //TODO Auto-generated constructor stub
    }

    // ถูกเรียกใน loop ของ Game class
    public void update(){
        monstermanager.update();

        // Test
        if (System.currentTimeMillis() - tick >= 1000){
            if (monstermanager.getAmount() < 10){
                monstermanager.spawn("Dr_Parkarn");
                tick = System.currentTimeMillis();
            } else {
                SetGameState(GAME_OVER);
            }
        }
    }


    // ถูกเรียกใน Render class
    @Override
    public void render(Graphics G, GameScreen screen){
        G.drawImage(Img, 0, 0, screen.getWidth(), screen.getHeight(), screen);
        monstermanager.draw(G, screen);
    }

    @Override
    public void mouseClicked(int x, int y) {
    }

    @Override
    public void mouseMoved(int x, int y) {
    }
    
}
