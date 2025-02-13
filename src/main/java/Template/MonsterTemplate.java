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
    private int MaxHealth;
    private int Health;
    private int tier;
    private float speed;
    private float xDelta = 100, yDelta = 100;
    
    private BufferedImage img;
    private BufferedImage[] moveMentAni;
    private int aniTick,aniIndex,aniSpeed = 15;
    // reward amountReward ...
    
    public void setInfo(int health, int tier, float speed, String AccessImg){
        this.ID = GobalID;
        this.MaxHealth = health;
        this.Health = health;
        this.tier = tier;
        this.speed = speed;
        
        GobalID++;
        importImage(AccessImg);
        loadAnimations();
        // create this entity panel
    }

    // Getter
    public int[] getWh(){
        return new int[]{gamePanel.getWidth(), gamePanel.getHeight()};
    }
    
    public float[] getPos(){
        return new float[]{xDelta, yDelta};
    }
    
    public int getID(){
        return ID;
    }

    public GamePanel getPanel(){
        return gamePanel;
    }

    // HP Method
    public boolean isAlive(){
        return Health > 0;
    }

    public void takeDamage(int Damage){
        Health -= Damage;
    }

    public void heal(int value){
        if (Health >= MaxHealth) {return;}
        
        Health += value;
        if (Health > MaxHealth) {Health = MaxHealth;}
    }

    // movement
    public void move(){

        /* xDelta, yDelta จะเปลี่ยนแปลงหลังจากการเคลื่อนไหว 
        เพราะ gamePanel.changeyxDelta จะ return ตำแหน่ง x/y หลังการเปลี่ยนแปลงมาให้เลย */
        
        //  เดินลงจากบน->ล่าง
        if(xDelta == 100 && yDelta < 680 && yDelta > 0){
            //xDelta = gamePanel.changexDelta(0);
            yDelta += speed;
        }
        // เดินข้างไปทางขาว->ซ้าย
        if(yDelta == 680 && xDelta < 1000){
            xDelta += speed;
            //gamePanel.changeyDelta(0);
        }
        // เดินขึ้นจากล่าง->บน
        if(xDelta == 1000 && yDelta > 100){
            //gamePanel.changexDelta(0);
            yDelta -= speed;
        }
        // เดินจากซ้าย->ขวา
        if( xDelta > 100 && yDelta == 100){
            xDelta -= speed;
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
        g.drawImage(moveMentAni[aniIndex],(int)xDelta,(int) yDelta, 64, 64, null);
    }
}
