package Entity;

public class Dr_Parkarn extends Monster {

    public Dr_Parkarn(double multiple){
        super("Boss", 3000*multiple, 1f, "Gem_4", "/Assets/Monsters/dr_prakarn.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
