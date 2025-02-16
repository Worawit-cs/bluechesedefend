package Entity;
// package Heroes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.ArrayList;

import Manager.MonsterManager;
import Stages.Loader;

public class Hero {
    private static int GlobalID = 0;
    private int ID; // เข้ารหัส Hero
    private HeroInfo info;

    // for cooldown
    private long tick = 0;
    private String Stage = "Idle";

    private int Amount = 0;
    private Vector2D position;
    private Monster target = null;

    // animation info
    private Loader loader;
    private BufferedImage imgIdle, imgAttack;
    private BufferedImage[] idleAnim, attckAnim;
    private int aniTick,aniIndex,aniSpeed = 15;

    public Hero(int X, int Y, String Name, int tier, int ATK, int Radius, float SPA, String idle, String attack){
        this.ID = GlobalID;
        info = new HeroInfo(tier, Name, ATK, Radius, SPA);
        
        position = new Vector2D(X, Y);
        Amount++;
        GlobalID++;

        imgAttack = loader.loadMap(attack);
        imgIdle = loader.loadMap(idle);
        loadAnimations(attckAnim, imgAttack, 2);
        loadAnimations(idleAnim, imgIdle, 2);
    }

    public void increaseAmount(){
        Amount++;
        info.setATK(Amount);
    }

    public void decreaseAmount(){
        Amount--;
        info.setATK(Amount);
    }

    // Getter
    public int getID() { return this.ID; }
    public int getAmount(){ return Amount; }
    public HeroInfo getInfo(){ return info; }
    public Vector2D getPos(){ return position; }
    public Monster getTarget(){ return target; }

    // attack
    public void attack(){
        // check distance first
        if (checkDistance(target) > info.getRadius()){
            findTarget();
        }

        if (target != null){target.takeDamage(info.getATK());}
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
    public void loadAnimations(BufferedImage[] animList, BufferedImage imgAnim, int n){
        animList = new BufferedImage[n];

        for(int i=0;i< animList.length;i++){
            animList[i] = imgAnim.getSubimage(i*128, 0, 128, 128);
        }     
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

            aniIndex = (aniIndex + 1)%idleAnim.length;
        }
    }

    
    public void draw(Graphics g){
        // attack condition
        if (System.currentTimeMillis() - tick >= info.getSPA() && target != null){
            aniTick = 0; // reset tick for perfect timing attack animation
            aniIndex = 0;
            Stage = "Attack";
            tick = System.currentTimeMillis(); // cooldown

            // add attack function
            attack();
        } 

        updateAnimation();
        switch (Stage) {
            case "Idle":
                g.drawImage(idleAnim[aniIndex],(int)position.getX(), (int)position.getY(), 64, 64, null);
                break;
        
            case "Attack":
                g.drawImage(attckAnim[aniIndex],(int)position.getX(), (int)position.getY(), 64, 64, null);
                break;
        }
    }
}

class HeroInfo
{
    public HeroInfo(int tier, String Name, int ATK, int Radius, float SPA){
        this.tier = tier;
        this.Name = Name;
        this.ATK = ATK;
        this.Radius = Radius;
        this.SPA = SPA * 1000; // millisec to sec
    }

    private int ATK, tier, Radius;
    private float SPA;
    private String Name;

    public void setATK(int Amount){
        this.ATK = ATK * Amount;
    }

    public int getRadius(){
        return Radius;
    }
    
    public int getATK(){
        return ATK;
    }
    
    public int getTier(){
        return tier;
    }
    
    public String getName(){
        return Name;
    }

    public float getSPA(){
        return SPA;
    }
}