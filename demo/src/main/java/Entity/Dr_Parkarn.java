package Entity;

public class Dr_Parkarn extends Monster {

    public Dr_Parkarn(double multiple){
        super("Boss", 100*multiple, 2f, "Gem_2", "/Assets/Monsters/dr_prakarn.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
