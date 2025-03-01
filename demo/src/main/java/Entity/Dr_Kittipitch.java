package Entity;

public class Dr_Kittipitch extends Monster {

    public Dr_Kittipitch(double multiple){
        super("Boss", 10000*multiple, 0.1f, "Gem_20", "/Assets/Monsters/dr_Kittipitch.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}

