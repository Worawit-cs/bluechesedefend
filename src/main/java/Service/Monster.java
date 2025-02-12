package Service;
import java.util.ArrayList;

import org.w3c.dom.Entity;

import com.example.*;

import Entity.Dog;
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
        gameWindow.addGamePanel(mons.getPanel());
    }

    public void update(){
        for (int i = 0; i < entity.size(); i++){
            entity.get(i).move();
        }
    }
}
