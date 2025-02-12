package Entity;

import Template.MonsterTemplate;

public class Dr_Parkarn extends MonsterTemplate {
    private String AccessImg = "src/main/resources/Asset/Monster/dr_prakarn.png"; // set image here (All image of this character)
    private int Health = 100;
    private int ID = 1;
    private int tier = 1;
    private float speed = 1.25f;

    public Dr_Parkarn(){
        setInfo(Health, ID, tier, speed, AccessImg);
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
