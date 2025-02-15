package Manager;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Entity.Monster;
import Scenes.Playing;
import Stages.Loader;

/*
 * เหมือนว่าคลาสmonstermanger จะจัดการทุกอย่างของมอนส?เตอร์ พ๔ติกรรมการแสดงผลของมอนส์จะรวมอยู่กันในนี้ทั้งหมด
 */

public class MonsterManager {
    
    private Playing playing;
    private BufferedImage img;
    private BufferedImage[] monsterImgs;
    private Monster testmonster;
    private Loader loader;
    private int aniIndex = 0,aniTick = 0,aniSpeed = 30;

    public MonsterManager(Playing playing){
        this.playing = playing;
        testmonster = new Monster(30,30,0,1,5);
        loadMonsterImgs();
        loadAnimations();
    }

    public void loadMonsterImgs(){

        // monsterImgs[0] = loader.loadMap("/Assets/Monsters/Slime.png");
        
        // โหลดรูปภาพโดยใช้จาก class อื่นไม่เป็นเลยใช้คำสั่งมั่วๆมาก่อน
        try {
            InputStream is = getClass().getResourceAsStream("/Assets/Monsters/Slime_new.png");
            if (is == null) {
                System.err.println("Error: Images not found: " + "/Assets/Monsters/Slime_new.png");
                img = null;
            }
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Error loading map: " + e.getMessage());
            img = null;
        }
    }

    public void update(){
        testmonster.move(); //ทำให้มอนขยับ
    }

    public void draw(Graphics G){
        drawMonster(testmonster,G);
    }

    public void loadAnimations(){
        monsterImgs = new BufferedImage[4];
        for(int i=0;i<monsterImgs.length;i++){
            monsterImgs[i] = img.getSubimage(i*128, 0, 128, 128);
        }
    }

    public void updateAnimation(){
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex = (aniIndex + 1)%monsterImgs.length;
        }
    }

    private void drawMonster(Monster m, Graphics G){
        updateAnimation();
        G.drawImage(monsterImgs[aniIndex], (int) m.getX(), (int) m.getY(), null);
    }
}
