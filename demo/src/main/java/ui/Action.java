package ui;

import Entity.Hero;
import Manager.HeroManager;

import java.awt.Graphics;
import java.awt.Menu;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.text.html.parser.Entity;

import ui.ImageButton;
import ui.CustomFont;

import com.example.GameScreen;

import Scenes.Playing;
import Storage.Player;
import Storage.RandomHero;

public class Action {
    private int wave = 1;
    private String Stage = "Normal";
    private Playing playing;
    private Player player;
    private RandomHero randomHero;

    private CustomFont coinText,monsText,gemText,maxMonsText,waveText;
    private ImageButton SpinHeroButton, BinButton, UpgradeButton, LuckySpinButton, UpgradeNormal, UpgradeEpic, UpgradeLegendary;

    public Action(Playing playing){
        this.playing = playing;
        this.player = playing.getPlayer();
        this.randomHero = playing.getRandomHero();

        init();
    }

    private void init(){
        SpinHeroButton = new ImageButton("/Assets/Botton_summon_coin.png", 
        949, 411, 210, 60, 320, 120);
        LuckySpinButton = new ImageButton("/Assets/Botton_summon_daimond.png",
        949, 537, 210, 60, 320, 120);
        BinButton = new ImageButton("/Assets/bin.png", 
        830, 415, 64, 64, 64, 64);
        UpgradeButton = new ImageButton("/Assets/Arrow.png", 
        880, 415, 64, 64, 64, 64);
        

        coinText = new CustomFont("/Font/number.ttf", 970, 686,1,20f,false);
        gemText = new CustomFont("/Font/number.ttf", 1097, 686,1,20f,false);
        monsText = new CustomFont("/Font/number.ttf", 1014, 153, 2,17f,true);
        maxMonsText = new CustomFont("/Font/number.ttf", 1064, 153, 2, 17f, false);
        // hero[0] = new Hero(count, x, y, 0);
        
        UpgradeNormal = new ImageButton("/Assets/Botton_up.png", 884, 335, 70, 40, 100, 66);
        UpgradeEpic = new ImageButton("/Assets/Botton_up.png", 1018, 335, 70, 40, 100, 66);
        UpgradeLegendary = new ImageButton("/Assets/Botton_up.png", 1158, 335, 70, 40, 100, 66);
    }

    public String getStage(){ return Stage; }
    public int getWave(){ return wave; }

    public void draw(Graphics G,GameScreen screen){
        SpinHeroButton.draw(G);
        LuckySpinButton.draw(G);
        BinButton.draw(G);
        UpgradeButton.draw(G);
        UpgradeNormal.draw(G);
        UpgradeEpic.draw(G);
        UpgradeLegendary.draw(G);
        
        
        coinText.draw(G,String.valueOf(player.getCoin()));
        monsText.draw(G,String.valueOf(playing.getMonsterManager().getAmount()));
        // maxMonsText.draw(G, String.valueOf(playing.getmaxMons()));
        gemText.draw(G, String.valueOf(player.getGem()));

        // monsText.draw(G,String.valueOf(100));
        // g.drawString(""+coin,x,y);
    }

    public void waveIncrease(){
        wave++;
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
            
        } else if(LuckySpinButton.getBounds().contains(x,y)){
            LuckySpinButton.setMouseClick(true);
            if(player.isEnough("Gem", 1)){
                player.decreaseValue("Gem", 1);
                randomHero.randomAllWeight();
            }

        } else if(UpgradeNormal.getBounds().contains(x,y)){
            UpgradeNormal.setMouseClick(true);
            

        } else if(UpgradeEpic.getBounds().contains(x,y)){
            UpgradeEpic.setMouseClick(true);
        } else if(UpgradeLegendary.getBounds().contains(x,y)){
            UpgradeLegendary.setMouseClick(true);
        }
        else {
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
                
            default:
                break;
        }
        SpinHeroButton.setMouseOver(false);
        if (SpinHeroButton.getBounds().contains(x, y)){
            SpinHeroButton.setMouseOver(true);
        }
        if(LuckySpinButton.getBounds().contains(x,y)){
            LuckySpinButton.setMouseOver(true);
        } else {
            LuckySpinButton.setMouseOver(false);
        }
        if(UpgradeNormal.getBounds().contains(x,y)){
            UpgradeNormal.setMouseOver(true);
        } else {
            UpgradeNormal.setMouseOver(false);
        }
        if(UpgradeEpic.getBounds().contains(x,y)){
            UpgradeEpic.setMouseOver(true);
        } else {
            UpgradeEpic.setMouseOver(false);
        }
        if(UpgradeLegendary.getBounds().contains(x,y)){
            UpgradeLegendary.setMouseOver(true);
        } else {
            UpgradeLegendary.setMouseOver(false);
        };
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
