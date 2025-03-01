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
    
    private String Stage = "Normal";
    private Playing playing;
    private Player player;
    private RandomHero randomHero;

    private CustomFont coinText, gemText, coinCost, gemCost, upNCost, upECost, upLCost;

    private ImageButton SpinHeroButton, BinButton, UpgradeButton, 
    LuckySpinButton, UpgradeNormal, UpgradeEpic, UpgradeLegendary;

    private double intcoinCost = 10, intgemCost = 2;

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

        coinCost = new CustomFont("/Font/number.ttf", 1045, 510,1,20f,false);
        gemCost = new CustomFont("/Font/number.ttf", 1045, 636,1,20f,false);
        upNCost = new CustomFont("/Font/number.ttf", 910, 325,2,12f,true);
        upECost = new CustomFont("/Font/number.ttf", 1045, 328,2,12f,true);
        upLCost = new CustomFont("/Font/number.ttf", 1185, 328,2,12f,true);
        // hero[0] = new Hero(count, x, y, 0);
        
        UpgradeNormal = new ImageButton("/Assets/Botton_up.png", 884, 335, 70, 40, 100, 66);
        UpgradeEpic = new ImageButton("/Assets/Botton_up.png", 1018, 335, 70, 40, 100, 66);
        UpgradeLegendary = new ImageButton("/Assets/Botton_up.png", 1158, 335, 70, 40, 100, 66);
    }

    public String getStage(){ return Stage; }

    public void draw(Graphics G,GameScreen screen){
        SpinHeroButton.draw(G);
        LuckySpinButton.draw(G);
        BinButton.draw(G);
        UpgradeButton.draw(G);
        UpgradeNormal.draw(G);
        UpgradeEpic.draw(G);
        UpgradeLegendary.draw(G);
        
        
        coinText.draw(G,String.valueOf(player.getCoin()));
        gemText.draw(G, String.valueOf(player.getGem()));
        coinCost.draw(G, String.valueOf((int) intcoinCost));
        gemCost.draw(G, String.valueOf((int) intgemCost));
        upNCost.draw(G, String.valueOf(player.getCost("Common")));
        upECost.draw(G, String.valueOf(player.getCost("Epic")));
        upLCost.draw(G, String.valueOf(player.getCost("Legendary")));
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
            if(player.isEnough("Coin", (int) intcoinCost) && !playing.getHeroManager().isHeroFull()){ 
                player.decreaseValue("Coin", (int) intcoinCost);
                intcoinCost += (intcoinCost * 0.05);
                randomHero.randomAllWeight("Coin"); 
            }

        } else if(LuckySpinButton.getBounds().contains(x,y)){
            LuckySpinButton.setMouseClick(true);
            if(player.isEnough("Gem", (int) intgemCost) && !playing.getHeroManager().isHeroFull()){
                player.decreaseValue("Gem", (int) intgemCost);
                randomHero.randomAllWeight("Gem");
            }

        } else if (BinButton.getBounds().contains(x,y)){
            changeStage("Bin", x, y);

        } else if (UpgradeButton.getBounds().contains(x,y)){
            changeStage("Upgrade", x, y);
            

        } else if(UpgradeNormal.getBounds().contains(x,y)){
            UpgradeNormal.setMouseClick(true);
            if(player.isEnough("Coin", player.getCost("Common"))){
                UpgradeAllHero("Common");
            }
        } else if(UpgradeEpic.getBounds().contains(x,y)){
            UpgradeEpic.setMouseClick(true);
            if(player.isEnough("Coin", player.getCost("Epic"))){
                UpgradeAllHero("Epic");
            }
        } else if(UpgradeLegendary.getBounds().contains(x,y)){
            UpgradeLegendary.setMouseClick(true);
            if(player.isEnough("Coin", player.getCost("Legendary"))){   
                UpgradeAllHero("Legendary");
            }
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

    private void UpgradeAllHero(String Tier){
        Hero[][] heroArray = playing.getHeroManager().getHeros();
        ArrayList<Hero> heros = new ArrayList<>();
        for (Hero[] heroRow : heroArray) { //array 2d to array 1d 
            for (Hero hero : heroRow) {
                heros.add(hero);
            }
        }
        for (Hero hero : heros){ //upgrade hero ทั้งหมดบน field
            if (Tier == "Common"){
                if (hero != null && (hero.getTier() == "Common" || hero.getTier() == "Rare")){
                    hero.upgradeATK();
                }
            }
            else{
                if (hero != null && hero.getTier() == Tier){
                    hero.upgradeATK();
                }
            }
        }
        player.UpgradeHero(Tier);
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
