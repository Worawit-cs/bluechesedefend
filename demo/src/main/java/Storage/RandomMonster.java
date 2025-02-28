package Storage;

import java.util.Random;

import Manager.MonsterManager;
import Scenes.Playing;
import Stages.Wave;

public class RandomMonster {
    private Random random;
    private Playing playing;
    private Wave wave;

    private MonsterManager monsterManager;
    private String[] Monster, Boss;

    private int bossfreq;

    public RandomMonster(Playing playing){
        this.playing = playing;
        this.wave = playing.getWave();
        this.bossfreq = wave.getBossSpawnFreq();

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
        int bossIndex = (wave.getCurrentWave()/bossfreq)%Boss.length;
        monsterManager.spawn(Boss[bossIndex]);
    }
}
