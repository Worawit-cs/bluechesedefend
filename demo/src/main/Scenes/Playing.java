package Scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.print.MultiDocPrintService;

import Main.Game;
import Manager.MonsterManager;
import Stages.Loader;

public class Playing extends GameScene implements SceneMethods{

    private Loader loader;
    private BufferedImage Img;  
    private MonsterManager monstermanager;

    public Playing(Game game) {
        super(game);
        loader = new Loader();
        Img = loader.loadMap("/Assets/BG/Map_demo.png");

        monstermanager = new MonsterManager(this);
        //TODO Auto-generated constructor stub
    }

    public void update(){
        monstermanager.update();
    }



    @Override
    public void render(Graphics G){
        G.drawImage(Img, 0, 0, null);
        monstermanager.draw(G);
    }

    

    @Override
    public void mouseClicked(int x, int y) {
    }

    @Override
    public void mouseMoved(int x, int y) {
    }
    
}
