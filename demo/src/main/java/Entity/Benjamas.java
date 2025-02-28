package Entity;

public class Benjamas extends Monster {

    public Benjamas(double multiple){
        super("Boss", 100*multiple, 2f, "Gem_2", "/Assets/Monsters/Benjamas.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}

