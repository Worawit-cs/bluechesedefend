package Entity;

import Template.MonsterTemplate;

public class Dr_Parkarn extends MonsterTemplate {
    private String AccessImg = "src/main/resources/Asset/Monster/dr_prakarn.png"; // set image here (All image of this character)
    private int Health = 100;
    private int tier = 1;
    private float speed = 0.5f;

    public Dr_Parkarn(){
        setInfo(100, 1, 0.5f, AccessImg);
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}


