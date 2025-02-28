package Entity;

public class Slime extends Monster {

    public Slime(double multiple){
        super("Monster", 100*multiple, 2f, "Coin_25", "/Assets/Monsters/Slime.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
