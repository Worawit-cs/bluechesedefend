package Entity;
// package Heroes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Manager.MonsterManager;
import Stages.Loader;

public class Hero {
    private static int GlobalID = 0;
    private int ID; // เข้ารหัส Hero
    private String tier, Name;
    private HeroInfo info;
    private Boolean showRadius = false;

    // for cooldown
    private long tick = 0;
    private String Stage = "Idle";

    private int Amount = 0;
    private int MaxPerGroup = 0;

    private Vector2D position;
    private Monster target = null;

    // animation info
    private Loader loader = new Loader();
    private BufferedImage imgIdle, imgAttack;
    private BufferedImage[] idleAnim, attckAnim;
    private int aniTick,aniIndex;
    private double aniSpeed = 15;

    public Hero(String Name, String tier, int ATK, int Radius, float SPA, int MaxPerGroup,
     String Reward, String idle, String attack){

        this.ID = GlobalID;
        this.tier = tier;
        this.Name = Name;
        this.MaxPerGroup = MaxPerGroup;
        info = new HeroInfo(ATK, Radius, SPA, Reward);
        
        Amount++;
        GlobalID++;

        imgAttack = loader.loadMap(attack);
        imgIdle = loader.loadMap(idle);
    }

    public void setPosition(float x, float y){
        position = new Vector2D(x, y);
    }

    public int getMaxPerGroup(){
        return MaxPerGroup;
    }

    public void increaseAmount(){
        Amount++;
        info.setATK(Amount);
    }

    public int decreaseAmount(){
        Amount--;
        info.setATK(Amount);
        return Amount;
    }

    public void turnRadius(boolean OnOff){
        showRadius = OnOff;
    }

    // Getter
    public int getID() { return ID; }
    public int getAmount(){ return Amount; }
    public String getTier(){ return tier; }
    public String getName(){ return Name; }
    public HeroInfo getInfo(){ return info; }
    public Vector2D getPos(){ return position; }
    public Monster getTarget(){ return target; }
    public String[] getReward(){ return info.getReward(); }

    // attack
    public void attack(){
        // attack condition
        if (System.currentTimeMillis() - tick < info.getSPA()){return;}

        // animation
        aniTick = 0; // reset tick for perfect timing attack animation
        aniIndex = 0;

        // attack function
        tick = System.currentTimeMillis(); // cooldown

        // check distance first
        if (target == null || !target.isAlive() || checkDistance(target) > info.getRadius()){
            findTarget();
            //System.out.println("Change " + Name );
        }

        if (target != null){target.takeDamage(info.getATK()); Stage = "Attack";}
    }

    private double checkDistance(Monster target){
        Vector2D targetPos = target.getPos();
        Vector2D heroPos = getPos();

        double xDistance = Math.abs(targetPos.getX() - heroPos.getX());
        double yDistance = Math.abs(targetPos.getY() - heroPos.getY());

        return Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
    }

    public void findTarget(){
        ArrayList<Monster> monsters = MonsterManager.getMonsters();
        double currentDis = -1;
        target = null;

        for (int i = 0; i < monsters.size(); i++){
            Monster mon = monsters.get(i);
            double dis = checkDistance(mon);
            // มอนตัวนั้นจะต้องมีระยะอยู่ในขอบเขต และ มีระยะมากกว่า target ปัจจุบัน (เพราะจะเอาตัวที่ไกลสุดในระยะ) หรือ ระยะก่อนหน้าเป็น -1 (ไม่มี target)
            if (dis <= info.getRadius() && (currentDis == -1 || dis > currentDis)){
                target = mon;
                currentDis = dis;
            }
        }
    }

    // Animation
    public void loadAnimations(int N_idle, int N_attack, int Size){
        idleAnim = new BufferedImage[N_idle];
        attckAnim = new BufferedImage[N_attack];

        for(int i=0;i< idleAnim.length;i++){
            idleAnim[i] = imgIdle.getSubimage(i*Size, 0, Size, Size);
        }  
        
        for(int i=0;i< attckAnim.length;i++){
            attckAnim[i] = imgAttack.getSubimage(i*Size, 0, Size, Size);
        }

        // adjust animSpeed for perfect Attack animation
        aniSpeed = (info.getSPA()/80);
    }

    public void updateAnimation(){
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            
            if (Stage == "Attack" && aniIndex + 1 >= attckAnim.length){
                Stage = "Idle"; // if attack anim end then back to Idle stage.
                aniIndex = 0; // reset index for idle anim
                return;
            }

            switch (Stage) {
                case "Attack":
                    aniIndex = (aniIndex + 1)%attckAnim.length;
                    break;
            
                case "Idle":
                    aniIndex = (aniIndex + 1)%idleAnim.length;
                    break;
            }
        }
    }

    
    public void draw(Graphics g){

        updateAnimation();
        int xPos = (int)position.getX();
        int yPos = (int)position.getY();
        int r = (int)info.getRadius();

        if (showRadius){ g.drawOval(xPos - r+ 32, yPos - r +32, r*2, r*2); }

        switch (Stage) {
            case "Idle":
                for (int i = 0; i < Amount; i++){
                    switch (i) {
                        case 0:
                            g.drawImage(idleAnim[aniIndex],xPos, yPos, 64, 64, null);
                            break;
                    
                        case 1:
                            g.drawImage(idleAnim[aniIndex],xPos -20, yPos +10, 64, 64, null);
                            break;

                        case 2:
                            g.drawImage(idleAnim[aniIndex],xPos +20, yPos +10, 64, 64, null);
                            break;
                    }
                }
                break;
        
            case "Attack":
                for (int i = 0; i < Amount; i++){
                    switch (i) {
                        case 0:
                            g.drawImage(attckAnim[aniIndex],xPos, yPos, 64, 64, null);
                            break;
                    
                        case 1:
                            g.drawImage(attckAnim[aniIndex],xPos -20, yPos +10, 64, 64, null);
                            break;

                        case 2:
                            g.drawImage(attckAnim[aniIndex],xPos +20, yPos +10, 64, 64, null);
                            break;
                    }
                }
                break;
        }
    }
}

class HeroInfo
{
    public HeroInfo(int ATK, int Radius, float SPA, String reward){
        this.ATK = ATK;
        this.Radius = Radius;
        this.reward = reward.split("_");
        this.SPA = SPA * 1000; // millisec to sec
    }

    private String[] reward;
    private int ATK, Radius;
    private float SPA;

    public void setATK(int Amount){
        this.ATK = ATK * Amount;
    }

    public String[] getReward(){
        return reward;
    }

    public int getRadius(){
        return Radius;
    }
    
    public int getATK(){
        return ATK;
    }

    public float getSPA(){
        return SPA;
    }
}