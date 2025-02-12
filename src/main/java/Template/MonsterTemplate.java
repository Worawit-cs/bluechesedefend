package Template;
import com.example.*;;

public class MonsterTemplate {
    private GamePanel gamePanel;
    private int health;
    private int ID;
    private int tier;
    private int xpos = 100,ypos = 100;
    private float xDir =1.0f,yDir=1.0f;

    public MonsterTemplate(){ // add image parameter?

        gamePanel = new GamePanel(64, 64, xpos, ypos); // xpos, ypos is entity spawnpoint.
    }

    public GamePanel getPanel(){
        return gamePanel;
    }

    public void setInfo(int health, int ID, int tier){
        this.health = health;
        this.ID = ID;
        this.tier = tier;
    }

    public void move(){
        int[] gamePosition = gamePanel.getPos(); // เก็บlistจากgamepanelมา
        int xDelta = gamePosition[0] , yDelta = gamePosition[1]; // เก็บPositionของobjไว้เพื่อเปลี่ยนแปลงทิศทางการเดินของMonster

        System.out.println(xDelta+" "+ yDelta); //checking Position
        //  เดินลงจากบน->ล่าง
        if(xDelta == 100 && yDelta < 680 && yDelta > 0){
            gamePanel.changexDelta(0);
            gamePanel.changeyDelta((int)yDir);
        }
        // เดินข้างไปทางขาว->ซ้าย
        if(yDelta == 680 && xDelta < 1000){
            gamePanel.changexDelta((int)xDir);
            gamePanel.changeyDelta(0);
        }
        // เดินขึ้นจากล่าง->บน
        if(xDelta == 1000 && yDelta > 100){
            gamePanel.changexDelta(0);
            gamePanel.changeyDelta((int)-yDir);
        }
        // เดินจากซ้าย->ขวา
        if( xDelta > 100 && yDelta == 100){
            gamePanel.changexDelta((int)-xDir);
            gamePanel.changeyDelta(0);
        }
        
    }
}
