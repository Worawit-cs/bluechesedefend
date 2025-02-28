package Entity;

public class Dr_Samerkae extends Monster {

    public Dr_Samerkae(double multiple){
        super("Boss", 100*multiple, 2f, "Gem_2", "/Assets/Monsters/dr_Samerkae.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
