package ui;

import Entity.Hero;
import Manager.HeroManager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import ui.ImageButton;
import ui.CustomFont;

import com.example.GameScreen;

import Scenes.Playing;

public class Action {
    private int coin = 1000;
    private int gem = 1000;
    private String Stage = "Normal";

    private Hero[] hero;
    private HeroManager heroManager;
    private ImageButton SpinHeroButton, BinButton, UpgradeButton;
    private Playing playing;
    private CustomFont coinText,monsText,gemText,maxMonsText;

    public Action(Playing playing,HeroManager heromanager){
        this.playing = playing;
        this.heroManager = heromanager;
        SpinHeroButton = new ImageButton("/Assets/Botton_random.png", 
        950, 370, 210, 60, 320, 120);
        BinButton = new ImageButton("/Assets/bin.png", 
        950, 450, 64, 64, 64, 64);
        UpgradeButton = new ImageButton("/Assets/Arrow.png", 
        1000, 450, 64, 64, 64, 64);

        coinText = new CustomFont("/Font/number.ttf", 970, 686,1,20f,false);
        gemText = new CustomFont("/Font/number.ttf", 1097, 686,1,20f,false);
        monsText = new CustomFont("/Font/number.ttf", 1014, 153, 2,17f,true);
        maxMonsText = new CustomFont("/Font/number.ttf", 1064, 153, 2, 17f, false);
        // hero[0] = new Hero(count, x, y, 0);
    }

    public String getStage(){ return Stage; }

    public void addCoin(int N){
        coin += N;
    } 

    public void addGem(int N){
        gem += N;
    }

    public void randomHero(){
        Random rand = new Random();
        int herocode = rand.nextInt();
        coin -= 10;
        heroManager.spawn("New");//ควรจะ random
    }

    public void resetAssets(){
        coin = 100;
        gem = 0;
    }

    public boolean isMoneyEnough(int x){
        if (x < coin){
            return true;
        }
        return false;
    }


    public void draw(Graphics G,GameScreen screen){
        SpinHeroButton.draw(G);
        BinButton.draw(G);
        UpgradeButton.draw(G);

        coinText.draw(G,String.valueOf(coin));
        monsText.draw(G,String.valueOf(playing.getMonsterManager().getAmount()));
        maxMonsText.draw(G, String.valueOf(playing.getmaxMons()));
        gemText.draw(G, String.valueOf(gem));
        // monsText.draw(G,String.valueOf(100));
        // g.drawString(""+coin,x,y);
    }

    public void clearStage(){
        Stage = "Normal";
        UpgradeButton.removeClone();
        BinButton.removeClone();
    }

    private void changeStage(String changeTo, int x, int y){
        switch (changeTo) {
            case "Bin":
                clearStage();
                BinButton.createMouseClone(x, y);
                Stage = "Bin";
                break;
        
            case "Upgrade":
                clearStage();
                UpgradeButton.createMouseClone(x, y);
                Stage = "Upgrade";
                break;
        }
    }

    public void mouseClicked(int x, int y){
        // contain x,y ให้เรัยก ismoneyenough แล้วเรัยก randomhero

        if(SpinHeroButton.getBounds().contains(x,y)){
            SpinHeroButton.setMouseClick(true);
            if(isMoneyEnough(10) && !playing.getHeroManager().isHeroFull()){ randomHero(); }

        } else if (BinButton.getBounds().contains(x,y)){
            changeStage("Bin", x, y);

        } else if (UpgradeButton.getBounds().contains(x,y)){
            changeStage("Upgrade", x, y);
            
        } else {
            if (Stage != "Normal" && !playing.getHeroManager().contains(x, y)){ clearStage(); }
        }
    }

    public void mouseMoved(int x, int y){
        switch (Stage) {
            case "Bin":
                BinButton.setClonePos(x, y);
                break;
        
            case "Upgrade":
                UpgradeButton.setClonePos(x, y);
                break;
        }
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_E){
            changeStage("Upgrade", 500, 200);
        } else if (e.getKeyCode() == KeyEvent.VK_R){
            changeStage("Bin", 500, 200);
        } else if (e.getKeyCode() == KeyEvent.VK_Q){
            clearStage();
        }
    }
}
