package Service;
import java.util.ArrayList;

import com.example.*;

import Template.MonsterTemplate;

public class Monster{
    
    private GameWindow gameWindow;
    private ArrayList<MonsterTemplate> entity = new ArrayList<MonsterTemplate>();
    
    public Monster(GameWindow gameWindow){
        this.gameWindow = gameWindow;

        //this.bounds = new Rectangle((int) x, (int) y, 32, 32);     
    }
    
    public <t extends MonsterTemplate> void spawn(t mons){
        entity.add(mons);
        gameWindow.add(mons.getPanel());
        
        // ใช้เมื่อยามจำเป็น
        // gameWindow.revalidate();
        // gameWindow.repaint();
    }

    public void update(double deltaTime){
        for (MonsterTemplate monster:entity){
            monster.move();
        }
    }
}
