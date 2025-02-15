package Manager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Entity.Hero;
import Scenes.Playing;
import Stages.Loader;

public class HeroManager {
    private Playing playing;
    private BufferedImage[] heroImgs;
    private ArrayList<Hero> heros = new ArrayList<>();
    private Loader loader;
    private int heroamount = 0;
    
    public HeroManager(Playing playing){
        this.playing = playing;
        LoadHeroImages();
    }
    
    private void LoadHeroImages(){
        heroImgs = new BufferedImage[3]; // มีกี่ตัวก้ใส่ไปตามนั้น
        heroImgs[0] = loader.loadMap("/Hero/folkidel.png");

    }

    public void addHero(Hero hero,int x,int y){
        heros.add(new Hero(heroamount++,x,y,hero.GetCode()));
    }

    public void removeHero(Hero hero){
        for (int i = 0 ; i < heros.size() ; i++){
            if(heros.get(i).GetID() == hero.GetID()){
                heros.remove(i);
            }
        }
    }
    
}
