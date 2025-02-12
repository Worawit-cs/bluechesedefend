package Template;
import com.example.*;;

public class MonsterTemplate {
    private GamePanel gamePanel;
    private int MaxHealth;
    private int Health;
    private int ID;
    private int tier;
    private float speed;
    private int xDelta = 100, yDelta = 100;
    // reward amountReward ...
    public GamePanel getPanel(){
        return gamePanel;
    }

    public int[] getWh(){
        return new int[]{gamePanel.getWidth(), gamePanel.getHeight()};
    }
    
    public int[] getPos(){
        return gamePanel.getPos();
    }
    
    public void takeDamage(int Damage){
        Health -= Damage;

        // if Health <= 0 do something.
    }

    public void heal(int value){
        if (Health >= MaxHealth) {return;}

        Health += value;
        if (Health > MaxHealth) {Health = MaxHealth;}
    }

    public void setInfo(int health, int ID, int tier, float speed, String AccessImg){
        this.MaxHealth = health;
        this.Health = health;
        this.ID = ID;
        this.tier = tier;
        this.speed = speed;
        
        // create this entity panel
        gamePanel = new GamePanel(64, 64, 100, 100, AccessImg); // xpos, ypos is entity spawnpoint.
    }

    public void move(){

        /* xDelta, yDelta จะเปลี่ยนแปลงหลังจากการเคลื่อนไหว 
        เพราะ gamePanel.changeyxDelta จะ return ตำแหน่ง x/y หลังการเปลี่ยนแปลงมาให้เลย */

        //System.out.println(xDelta+" "+ yDelta); //checking Position
        //  เดินลงจากบน->ล่าง
        if(xDelta == 100 && yDelta < 680 && yDelta > 0){
            //xDelta = gamePanel.changexDelta(0);
            yDelta = gamePanel.changeyDelta((int)speed);
        }
        // เดินข้างไปทางขาว->ซ้าย
        if(yDelta == 680 && xDelta < 1000){
            xDelta = gamePanel.changexDelta((int)speed);
            //gamePanel.changeyDelta(0);
        }
        // เดินขึ้นจากล่าง->บน
        if(xDelta == 1000 && yDelta > 100){
            //gamePanel.changexDelta(0);
            yDelta = gamePanel.changeyDelta((int)-speed);
        }
        // เดินจากซ้าย->ขวา
        if( xDelta > 100 && yDelta == 100){
            xDelta = gamePanel.changexDelta((int)-speed);
            //gamePanel.changeyDelta(0);
        }
        
    }
}
