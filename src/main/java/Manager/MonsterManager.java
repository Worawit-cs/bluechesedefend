package Manager;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Entity.Monster;
import Scenes.Playing;
import Stages.Loader;

public class MonsterManager {
    
    private Playing playing;
    private BufferedImage[] monsterImgs;
    private Monster testmonster;
    private Loader loader;

    public MonsterManager(Playing playing){
        this.playing = playing;
        monsterImgs = new BufferedImage[9];
        testmonster = new Monster(30,30,0,1,5);
        loadMonsterImgs();
    }

    public void loadMonsterImgs(){

        // monsterImgs[0] = loader.loadMap("/Assets/Monsters/Slime.png");
        
        // โหลดรูปภาพโดยใช้จาก class อื่นไม่เป็นเลยใช้คำสั่งมั่วๆมาก่อน
        try {
            InputStream is = getClass().getResourceAsStream("/Assets/Monsters/Slime.png");
            if (is == null) {
                System.err.println("Error: Images not found: " + "/Assets/Monsters/Slime.png");
                monsterImgs[0] = null;
            }
            monsterImgs[0] = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Error loading map: " + e.getMessage());
            monsterImgs[0] = null;
        }
    }

    public void update(){
        testmonster.move(); //ทำให้มอนขยับ
    }

    public void draw(Graphics G){

        drawMonster(testmonster,G);
    }

    private void drawMonster(Monster m, Graphics G){
        G.drawImage(monsterImgs[0], (int) m.getX(), (int) m.getY(), null);
    }
}
