package ui;

import Entity.Hero;
import Manager.HeroManager;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import ui.ImageButton;
import com.example.GameScreen;

import Scenes.Playing;

public class Action {
    private int coin = 100;
    private int gem = 0;
    private Hero[] hero;
    private HeroManager HeroManager;
    private ImageButton SpinHeroButton;
    private Playing playing;

    public Action(Playing playing){
        this.playing = playing;
        SpinHeroButton = new ImageButton(null, 1055-110, 400-30, 220, 60);
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
        // เพิ่ม hero ไป
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
        // g.drawString(""+coin,x,y);
    }

    public void mouseClicked(int x, int y){
        // contain x,y ให้เรัยก ismoneyenough แล้วเรัยก randomhero
        if(SpinHeroButton.getBounds().contains(x,y)){
            System.out.println("Pressed!");
        }
    }

}
