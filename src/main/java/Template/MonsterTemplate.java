package Template;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.example.*;

public class MonsterTemplate {
    private static int GobalID = 0;
    private int ID;

    private GamePanel gamePanel;
    private Vector2D position = new Vector2D(100, 100);
    
    private BufferedImage img;
    private BufferedImage[] moveMentAni;
    private int aniTick,aniIndex,aniSpeed = 15;

    EntityInfo info = new EntityInfo(100, 1, 10);

    public EntityInfo GetInfo(){
        return info;
    } 
    
    // reward amountReward ...
    
    public void setInfo(int health, int tier, float speed, String AccessImg){
        this.ID = GobalID;
        info = new EntityInfo(health, tier, speed);
        
        GobalID++;
        importImage(AccessImg);
        loadAnimations();
        // create this entity panel
    }

    // Getter
    public int[] getWh(){
        return new int[]{gamePanel.getWidth(), gamePanel.getHeight()};
    }
    
    public Vector2D getPos(){
        return position;
    }
    
    public int getID(){
        return ID;
    }

    public GamePanel getPanel(){
        return gamePanel;
    }

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

    // movement
    public void move(){

        /* xDelta, yDelta จะเปลี่ยนแปลงหลังจากการเคลื่อนไหว 
        เพราะ gamePanel.changeyxDelta จะ return ตำแหน่ง x/y หลังการเปลี่ยนแปลงมาให้เลย */
        
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

    // Animation
    public void importImage(String Accessimg){
        
        try {
            img = ImageIO.read(new File(Accessimg));
        } catch (IOException e) {
            e.printStackTrace();
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
}

class EntityInfo
{
    public EntityInfo(int MaxHealth, int tier, float speed){
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


class Vector2D
{
    public Vector2D(float xDelta, float yDelta){
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    public void Set(float xDelta, float yDelta)
    {
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    public void MoveXDelta(float value)
    {
        this.xDelta += value;
    }

    public void MoveYDelta(float value)
    {
        this.yDelta += value;
    }

    float xDelta, yDelta;
}
