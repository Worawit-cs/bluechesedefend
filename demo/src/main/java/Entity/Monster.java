package Entity;

import java.awt.Rectangle;

public class Monster {
    private float x,y;
    private Rectangle bounds;
    private int health;
    private int ID;
    private int Tier;
    private int speed;

    public Monster(float x, float y, int id, int Tier,int speed){
        this.x = x;
        this.y = y;
        this.ID = id;
        this.Tier = Tier;
        this.speed = speed;
        bounds = new Rectangle((int)x,(int)y,32,32);
    }

    public void move(){
        if(x < 1050 &&  y == 30){
            x += speed;
        }
        else if(x == 1050 && y < 850){
            y += speed;
        }
        else if(x > 30 && y == 850){
            x -= speed;
        }
        else{
            y -= speed;
        }
        System.out.println(x+" "+y);
    }

    public float getX(){ return x; }
    public float getY(){ return y; }
    public int getHealth(){ return health; }
    public int getID(){ return ID; }
    public int getTier(){ return Tier; }

}
