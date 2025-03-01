package Entity;

public class Benjamas extends Monster {

    public Benjamas(double multiple){
        super("Boss", 3000*multiple, 1f, "Gem_6", "/Assets/Monsters/Benjamas.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}

