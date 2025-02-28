package Entity;

public class Dr_Kittipitch extends Monster {

    public Dr_Kittipitch(double multiple){
        super("Boss", 100*multiple, 2f, "Gem_2", "/Assets/Monsters/dr_Kittipitch.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}

