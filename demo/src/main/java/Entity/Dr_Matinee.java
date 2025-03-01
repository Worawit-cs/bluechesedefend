package Entity;

public class Dr_Matinee extends Monster {

    public Dr_Matinee(double multiple){
        super("Boss", 2000*multiple, 2f, "Gem_2", "/Assets/Monsters/dr_Matinee.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
