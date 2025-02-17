package ui;

import Entity.Hero;
import Manager.HeroManager;

import java.awt.Graphics;
import java.util.Random;

import ui.ImageButton;
import ui.CustomFont;

import com.example.GameScreen;

import Scenes.Playing;

public class Action {
    private int coin = 1000;
    private int gem = 1000;
    private Hero[] hero;
    private HeroManager heroManager;
    private ImageButton SpinHeroButton;
    private Playing playing;
    private CustomFont coinText,monsText,gemText;

    public Action(Playing playing,HeroManager heromanager){
        this.playing = playing;
        this.heroManager = heromanager;
        SpinHeroButton = new ImageButton("/Assets/Botton_random.png", 1055-105, 400-30, 210, 60);
        coinText = new CustomFont("/Font/number.ttf", 970, 686,1,20f,false);
        gemText = new CustomFont("/Font/number.ttf", 1097, 686,1,20f,false);
        monsText = new CustomFont("/Font/number.ttf", 1014, 153, 2,17f,true);
        // hero[0] = new Hero(count, x, y, 0);
    }

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
        coinText.draw(G,String.valueOf(coin));
        monsText.draw(G,String.valueOf(playing.getMonsterManager().getAmount()));
        gemText.draw(G, String.valueOf(gem));
        // monsText.draw(G,String.valueOf(100));
        // g.drawString(""+coin,x,y);
    }

    public void mouseClicked(int x, int y){
        // contain x,y ให้เรัยก ismoneyenough แล้วเรัยก randomhero
        if(SpinHeroButton.getBounds().contains(x,y)){
            System.out.println("Pressed!");
            if(isMoneyEnough(10) && playing.getHeroManager().isHeroFull()){ randomHero(); }
        }
    }

    public void mouseMoved(int x, int y){
        SpinHeroButton.setMouseOver(false);
        if(SpinHeroButton.getBounds().contains(x,y)){
            SpinHeroButton.setMouseOver(true);
        }
    }

}
