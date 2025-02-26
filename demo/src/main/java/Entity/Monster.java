package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Stages.Loader;

// This is Monster Template.


public class Monster {
    private static int GlobalID = 0;
    private int ID;
    private EntityInfo info;
    private Vector2D position;

    // animation info
    private String Stage = "Walk";
    private Loader loader;
    private BufferedImage img;
    private BufferedImage[] moveMentAni;
    private int aniTick,aniIndex,aniSpeed = 15;

    public Monster(String Type, int health, float speed, String reward, String AccessImg){
        this.ID = GlobalID;
        GlobalID++;

        info = new EntityInfo(Type, health, speed, reward);

        position = new Vector2D(38, 50);
        
        loader = new Loader();
        img = loader.loadMap(AccessImg);
        loadAnimations();
    }

    public void move(){
        
        // xDelta, yDelta จะเปลี่ยนแปลงหลังจากการเคลื่อนไหว
        
        //  เดินลงจากบน->ล่าง
        if(position.getX() == 38 && position.getY() < 600 && position.getY() > 0){
            //xDelta = gamePanel.changexDelta(0);
            position.MoveYDelta(info.GetSpeed());
        }
        // เดินข้างไปทางขาว->ซ้าย
        if(position.getY() == 600 && position.getX() < 726){
            position.MoveXDelta(info.GetSpeed());
            //gamePanel.changeyDelta(0);
        }
        // เดินขึ้นจากล่าง->บน
        if(position.getX() == 726 && position.getY() > 20){
            //gamePanel.changexDelta(0);
            position.MoveYDelta(-info.GetSpeed());
        }
        // เดินจากซ้าย->ขวา
        if( position.getX() > 38 && position.getY() == 20){
            position.MoveXDelta(-info.GetSpeed());
            //gamePanel.changeyDelta(0);
        }
        
    }

    public String[] getReward(){ return info.GetReward(); }
    public String getType(){ return info.GetType(); }
    public Vector2D getPos(){ return position; }
    public int getID(){ return ID; }

    // HP Method
    public boolean isAlive(){
        return info.GetHealth() > 0;
    }

    public void takeDamage(int Damage){
       info.DecreaseHealth(Damage);
       Stage = "Damaged";
    }

    public void heal(int value){
        info.AddHealth(value);
    }

    // Animation
    public void loadAnimations(){
        moveMentAni = new BufferedImage[4];

        for(int i=0;i<moveMentAni.length;i++){
            moveMentAni[i] = img.getSubimage(i*128, 0, 128, 128);
        }
    }

    public void updateAnimation(){
        aniTick++;
        if(aniTick>=aniSpeed){
            Stage = "Walk";
            aniTick=0;
            aniIndex = (aniIndex + 1)%moveMentAni.length;
        }
    }

    public void draw(Graphics g){
        updateAnimation();
        switch (Stage) {
            case "Walk":
                g.drawImage(moveMentAni[aniIndex],(int)position.getX(), (int)position.getY(), 64, 64, null);
                break;
        
            case "Damaged":
                damaged(g);
                break;
        }
    }

    private void damaged(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(moveMentAni[aniIndex],(int)position.getX(), (int)position.getY(), 64, 64, null);

        // Overlay สีแดง 50% บนภาพ
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));
        g2d.setColor(new Color(255, 0, 0, 128)); // สีแดงโปร่งแสง
        g2d.fillRect((int)position.getX(), (int)position.getY(), 64, 64);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
    }
} // end Monster Class




class EntityInfo
{
    public EntityInfo(String Type, int MaxHealth, float speed, String reward){
        this.reward = reward.split("_");
        this.MaxHealth = MaxHealth;
        this.Health = MaxHealth;
        this.Type = Type;
        this.speed = speed;
    }

    private int MaxHealth;
    private int Health;
    private String Type;
    private float speed;
    private String[] reward;

    public int GetMaxHealth(){
        return MaxHealth;
    }

    public int GetHealth(){
        return Health;
    }
    public String GetType(){
        return Type;
    }
    public float GetSpeed(){
        return speed;
    }

    public String[] GetReward(){ return reward; }

    public void AddHealth(int value){
        Health += value;
        Health = Math.min(Health, MaxHealth);
    }

    public void DecreaseHealth(int value){
        Health -= value;
        Health = Math.max(Health, 0);
    }
}