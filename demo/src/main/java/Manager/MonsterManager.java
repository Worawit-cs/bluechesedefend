package Manager;

import java.awt.Graphics;
import java.util.ArrayList;

import com.example.GameScreen;

import Entity.Benjamas;
import Entity.Dr_Kanmonphop;
import Entity.Dr_Kittipitch;
import Entity.Dr_Matinee;
import Entity.Dr_Parkarn;
import Entity.Dr_Samerkae;
import Entity.Jerr;
import Entity.Krarok;
import Entity.Lion;
import Entity.Monster;
import Entity.Slime;
import Entity.Toi;
import Scenes.Playing;

/*
 * เหมือนว่าคลาสmonstermanger จะจัดการทุกอย่างของมอนส?เตอร์ พ๔ติกรรมการแสดงผลของมอนส์จะรวมอยู่กันในนี้ทั้งหมด
 */

public class MonsterManager {
    
    private static ArrayList<Monster> monsters = new ArrayList<Monster>();
    private Playing playing;
    private double percent;

    public MonsterManager(Playing playing){
        this.playing = playing;
        setMode();
    }

    public static void resetMonsters(){
        monsters.removeAll(monsters);
    }

    public static ArrayList<Monster> getMonsters(){
        return monsters;
    }
    
    public int getAmount(){
        return monsters.size();
    }

    private void setMode(){
        switch (playing.getMode()) {
            case "Normal":
                percent = 1.2;
                break;
        
            case "Hard":
                percent = 1.4;
                break;
        }
    }

    // ใช้ลบ monster หลังพบว่าตายแล้ว
    public void remove(int index){
        String[] reward = monsters.get(index).getReward();
        playing.getPlayer().increaseValue(reward[0], Integer.parseInt(reward[1]));
        if (monsters.get(index).getType() == "Boss"){ playing.getWave().BossDied(); }

        monsters.remove(index);
    }
    
    // spawn function ใส่ชื่อแล้วจะไปสร้างคลาสของมอนตัวนั้น (extends MonsterTemplate ทำให้เข้าถึงง่ายขึ้น) แล้วมาเพิ่มใน entity
    public void spawn(String name){
        // นำ Entity ตัวใหม่ไปใส่ใน ArrayList เพื่อใช้ใน method update
        
        Monster mon = null;
        double multiple = (Math.pow(percent, playing.getWave().getCurrentWave() - 1));
        switch (name) {
            case "Dr_Parkarn":
                mon = new Dr_Parkarn(multiple);
                break;
            case "Dr_Kamonphop":
                mon = new Dr_Kanmonphop(multiple);
                break;
            case "Dr_Kittipitch":
                mon = new Dr_Kittipitch(multiple);
                break;
            case "Dr_Matinee":
                mon = new Dr_Matinee(multiple);
                break;
            case "Dr_Samerkae":
                mon = new Dr_Samerkae(multiple);
                break;
            case "Benjamas":
                mon = new Benjamas(multiple);
                break;
            case "Jerr":
                mon = new Jerr(multiple);
                break;
            case "Krarok":
                mon = new Krarok(multiple);
                break;
            case "Lion":
                mon = new Lion(multiple);
                break;
            case "Slime":
                mon = new Slime(multiple);
                break;
            case "Toi":
                mon = new Toi(multiple);
                break;
            
            // make another name case for create new monster object
            default:
                System.out.println("Not Found in MonsterManager");
                break;
        }
        System.out.println("Spawn Monster With " + mon.getHealth() + " HP" );
        monsters.add(mon);
    }

    // ถูกเรียกใน Playing class
    public void update(){
        // ทำให้ขยับ
        for (int i = monsters.size() - 1; i >= 0; i--){
            if (!monsters.get(i).isAlive()){ remove(i); continue;}
            monsters.get(i).move();
        }
    }

    // ถูกเรียกใน Playing class
    public void draw(Graphics G, GameScreen screen){
        for (int i = monsters.size() - 1; i >= 0; i--){
            monsters.get(i).draw(G);
        }
    }
}
