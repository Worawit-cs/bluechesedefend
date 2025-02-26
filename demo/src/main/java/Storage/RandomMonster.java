package Storage;

import java.util.Random;

import Manager.MonsterManager;
import Scenes.Playing;

public class RandomMonster {
    private Random random;
    private Playing playing;
    private MonsterManager monsterManager;
    private String[] Monster, Boss;

    public RandomMonster(Playing playing){
        this.playing = playing;
        random = new Random();
        monsterManager = playing.getMonsterManager();

        Monster = new String[]{"Dr_Parkarn"};
        Boss = new String[]{"Dr_Parkarn"};
    }

    public void spawnRandom(String type){
        
        int randomIndex;
        String Name = "None";
        switch (type) {
            case "Monster": 
                randomIndex = random.nextInt(Monster.length);
                Name = Monster[randomIndex];    
                break;
        
            case "Boss":
                randomIndex = random.nextInt(Boss.length);
                Name = Boss[randomIndex];    
                break;
        }

        monsterManager.spawn(Name);
    }
}
