package Stages;

import java.awt.Graphics;

import com.example.GameScreen;

import Manager.MonsterManager;
import Scenes.Playing;
import Storage.RandomMonster;
import ui.CustomFont;

public class Wave {
    private Playing playing;
    private RandomMonster randomMonster;
    private MonsterManager monsterManager;

    private CustomFont waveText, timeText, monsText, maxMonsText;
    private int CurrentWave = 1;
    private int MaxWave, bossSpawnFreq;

    private int AlreadySpawn = 0;
    private int MaxMon, MaxMonPerWave, increaseRate, RateFreq;
    private boolean isBossSpawn = false;

    private boolean showTime = false;
    private int bossTimeLimit, breakTime = 20; //(Sec)
    private long tick, timeleft;
    
    public Wave(Playing playing){
        this.playing = playing;
        this.randomMonster = new RandomMonster(playing);
        this.monsterManager = playing.getMonsterManager();

        waveText = new CustomFont("/Font/number.ttf", 1130, 85, 1, 55f, false);
        timeText = new CustomFont("/Font/number.ttf", 500, 686,1,20f,false);
        monsText = new CustomFont("/Font/number.ttf", 1014, 153, 2,17f,true);
        maxMonsText = new CustomFont("/Font/number.ttf", 1064, 153, 2, 17f, false);
        setMode();
    }

    private void setMode(){
        switch (playing.getMode()) {
            case "Normal":
                MaxMon = 100; // condition to lose
                MaxWave = 50;
                MaxMonPerWave = 10; // first Wave
                increaseRate = 5; // เพิ่มจำนวน MaxMon ตามจำนวน increaseRate
                RateFreq = 1; // เพิ่มจำนวน MaxMon ทุกๆ กี่เวฟ

                bossTimeLimit = 120;
                bossSpawnFreq = 5; // boss spawn every ... wave
                break;
        
            case "Hard":
                MaxMon = 90;
                MaxWave = 55;
                MaxMonPerWave = 15; // first Wave
                increaseRate = 10;
                RateFreq = 1;

                bossTimeLimit = 60;
                bossSpawnFreq = 5;
                break;
        }
    }

    private void increaseWave(){
        if (CurrentWave == MaxWave){
            if(monsterManager.getAmount() > 0){return;} 
            // กรณีเวฟสุดท้ายที่บอสตายแล้ว แต่ยังเหลือมอนบนจอ ก็จะรอให้ผู้เล่นเคลียร์ให้หมดก่อน

            playing.end("Victory"); 
            return;
        }

        CurrentWave++;
        AlreadySpawn = 0;
        
        if (CurrentWave % RateFreq == 0){MaxMonPerWave += increaseRate;}
    }

    public void BossDied(){
        isBossSpawn = false;
        showTime = false;
        timeText.changeColor(1);
    }

    public void update(){

        if (AlreadySpawn < MaxMonPerWave && System.currentTimeMillis() - tick >= 500){
            tick = System.currentTimeMillis();

            if (CurrentWave % bossSpawnFreq == 0 && AlreadySpawn == MaxMonPerWave - 1){
                isBossSpawn = true;
                
                timeText.changeColor(2);
                randomMonster.spawnRandom("Boss");
            } else{
                randomMonster.spawnRandom("Monster");
            }

            if (monsterManager.getAmount() == MaxMon){ playing.end("Lose"); }
            AlreadySpawn++;
        }

        if (AlreadySpawn < MaxMonPerWave){return;}
    
        if (monsterManager.getAmount() > 0 && // ในกรณีที่เคลียร์เสร็จไว ก็จะไม่แสดงเวลา แล้วไปเวฟต่อไปเลย 
        (isBossSpawn || (System.currentTimeMillis() - tick < (breakTime * 1000)))){
        // ถ้าบอสยังเกิดอยู่ หรีอ ยังมีกีกี้อยู่ในช่วงพัก (BreakTime) ก็จะรอให้บอสตายหรือหมดช่วงพักก่อน ก่อนจะไปเวฟต่อไป
        
            long x = System.currentTimeMillis() - tick;

            if(isBossSpawn){
                showTime = true;
                timeleft = (bossTimeLimit*1000) - x;
                if (x >= bossTimeLimit*1000){ playing.end("Lose");} // เคลียร์บอสไม่ทันเวลา ก็แพ้เลย
            }else {
                timeleft = (breakTime*1000) - (System.currentTimeMillis() - tick);
                
                if (timeleft <= 10000){
                    showTime = true;
                }
            }
            return; 
        }

        showTime = false;
        increaseWave();
    }

    public void draw(Graphics G, GameScreen Screen){

        maxMonsText.draw(G, String.valueOf(MaxMon));
        waveText.draw(G, String.valueOf(CurrentWave));
        monsText.draw(G, String.valueOf(monsterManager.getAmount()));

        if (!showTime){return;}
        timeText.draw(G, String.valueOf((int)timeleft/1000));
    }
}
