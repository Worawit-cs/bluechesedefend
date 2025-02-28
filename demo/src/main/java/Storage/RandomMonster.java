package Storage;

import java.util.Random;

import Manager.MonsterManager;
import Scenes.Playing;
import Stages.Wave;

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
        Boss = new String[]{"Dr_Parkarn"}; // boss order.
    }

    public void spawnMonster(){
        
        int randomIndex;
        randomIndex = random.nextInt(Monster.length);  
        monsterManager.spawn(Monster[randomIndex]);
    }
    
    public void spawnBoss(){
        Wave wave = playing.getWave();
        int bossIndex = (wave.getCurrentWave()/wave.getBossSpawnFreq())%Boss.length;
        monsterManager.spawn(Boss[bossIndex]);
    }
}
