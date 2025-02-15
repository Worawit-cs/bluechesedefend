package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;



// This is Monster Template.


public class Monster {
    private static int GlobalID = 0;
    private int ID;
    private EntityInfo info;
    private Vector2D position;

    // animation info
    private BufferedImage img;
    private BufferedImage[] moveMentAni;
    private int aniTick,aniIndex,aniSpeed = 15;

    public Monster(int Tier, int health, float speed, String AccessImg){
        this.ID = GlobalID;
        GlobalID++;

        info = new EntityInfo(Tier, health, speed);
        position = new Vector2D(100, 100);
        importImage(AccessImg);
        loadAnimations();
    }

    public void move(){

        // xDelta, yDelta จะเปลี่ยนแปลงหลังจากการเคลื่อนไหว
        
        //  เดินลงจากบน->ล่าง
        if(position.xDelta == 100 && position.yDelta < 680 && position.yDelta > 0){
            //xDelta = gamePanel.changexDelta(0);
            position.MoveYDelta(info.GetSpeed());
        }
        // เดินข้างไปทางขาว->ซ้าย
        if(position.yDelta == 680 && position.xDelta < 1000){
            position.MoveXDelta(info.GetSpeed());
            //gamePanel.changeyDelta(0);
        }
        // เดินขึ้นจากล่าง->บน
        if(position.xDelta == 1000 && position.yDelta > 100){
            //gamePanel.changexDelta(0);
            position.MoveYDelta(-info.GetSpeed());
        }
        // เดินจากซ้าย->ขวา
        if( position.xDelta > 100 && position.yDelta == 100){
            position.MoveXDelta(-info.GetSpeed());
            //gamePanel.changeyDelta(0);
        }
        
    }

    public Vector2D getPos(){ return position; }
    public int getID(){ return ID; }

    // HP Method
    public boolean isAlive(){
        return info.GetHealth() > 0;
    }

    public void takeDamage(int Damage){
       info.DecreaseHealth(Damage);
    }

    public void heal(int value){
        info.AddHealth(value);
    }

    // Animation
    public void importImage(String AccessImg){

        // monsterImgs[0] = loader.loadMap("/Assets/Monsters/Slime.png");
        
        // โหลดรูปภาพโดยใช้จาก class อื่นไม่เป็นเลยใช้คำสั่งมั่วๆมาก่อน
        try {
            InputStream is = getClass().getResourceAsStream(AccessImg);
            if (is == null) {
                System.err.println("Error: Images not found: " + AccessImg);
                img = null;
            }
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Error loading map: " + e.getMessage());
            img = null;
        }
    }

    public void loadAnimations(){
        moveMentAni = new BufferedImage[4];

        for(int i=0;i<moveMentAni.length;i++){
            moveMentAni[i] = img.getSubimage(i*128, 0, 128, 128);
        }
    }

    public void updateAnimation(){
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex = (aniIndex + 1)%moveMentAni.length;
        }
    }

    public void draw(Graphics g){
        updateAnimation();
        g.drawImage(moveMentAni[aniIndex],(int)position.xDelta, (int)position.yDelta, 64, 64, null);
    }
} // end Monster Class




class EntityInfo
{
    public EntityInfo(int tier, int MaxHealth, float speed){
        this.MaxHealth = MaxHealth;
        this.Health = MaxHealth;
        this.tier = tier;
        this.speed = speed;
    }

    private int MaxHealth;
    private int Health;
    private int tier;
    private float speed;

    public int GetMaxHealth(){
        return MaxHealth;
    }

    public int GetHealth(){
        return Health;
    }
    public int GetTier(){
        return tier;
    }
    public float GetSpeed(){
        return speed;
    }

    public void AddHealth(int value){
        Health += value;
        Health = Math.min(Health, MaxHealth);
    }

    public void DecreaseHealth(int value){
        Health -= value;
        Health = Math.max(Health, 0);
    }
}