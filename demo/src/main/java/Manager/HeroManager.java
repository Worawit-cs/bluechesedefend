package Manager;

import Entity.Hero;
import Scenes.Playing;
import Stages.Loader;

public class HeroManager {
    private Loader loader;
    private Playing playing;
    private int heroamount = 0;
    private Hero[][] heros = new Hero[3][6];
    
    public HeroManager(Playing playing){
        this.playing = playing;

    }
    
    public void addHero(Hero hero,int x,int y){
        //heros.add(new Hero(heroamount++,x,y,hero.GetCode()));
    }

    public void removeHero(Hero hero){
        //for (int i = 0 ; i < heros.size() ; i++){
            //if(heros.get(i).GetID() == hero.GetID()){
                //heros.remove(i);
            //}
        //}
    }
    
}
