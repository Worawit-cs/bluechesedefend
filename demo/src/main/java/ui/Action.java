package ui;

import Entity.Hero;
import Manager.HeroManager;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import ui.ImageButton;
import ui.CustomFont;

import com.example.GameScreen;

import Scenes.Playing;
import Storage.Player;
import Storage.RandomHero;

public class Action {
    private String Stage = "Normal";
    
    private Playing playing;
    private Player player;
    private RandomHero randomHero;

    private CustomFont coinText,monsText,gemText,maxMonsText;
    private ImageButton SpinHeroButton, BinButton, UpgradeButton;

    public Action(Playing playing){
        this.playing = playing;
        this.player = playing.getPlayer();
        this.randomHero = playing.getRandomHero();

        init();
    }

    private void init(){
        SpinHeroButton = new ImageButton("/Assets/Botton_summon_coin.png", 
        949, 412, 210, 60, 320, 120);
        BinButton = new ImageButton("/Assets/bin.png", 
        1000, 340, 64, 64, 64, 64);
        UpgradeButton = new ImageButton("/Assets/Arrow.png", 
        1050, 340, 64, 64, 64, 64);

        coinText = new CustomFont("/Font/number.ttf", 970, 686,1,20f,false);
        gemText = new CustomFont("/Font/number.ttf", 1097, 686,1,20f,false);
        // hero[0] = new Hero(count, x, y, 0);
    }

    public String getStage(){ return Stage; }


    public void draw(Graphics G,GameScreen screen){
        SpinHeroButton.draw(G);
        BinButton.draw(G);
        UpgradeButton.draw(G);

        coinText.draw(G,String.valueOf(player.getCoin()));
        gemText.draw(G, String.valueOf(player.getGem()));
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
            if(player.isEnough("Coin", 10) && !playing.getHeroManager().isHeroFull()){ 
                player.decreaseValue("Coin", 10);
                randomHero.randomAllWeight(); 
            }

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
