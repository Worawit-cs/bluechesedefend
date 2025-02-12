package Entity;

import Template.MonsterTemplate;

public class Dog extends MonsterTemplate {

    public Dog(){
        super();
        setInfo(100, 1, 1);
    }

    //@Override   -- modify for each Entity.
    //public void move(){}
}
