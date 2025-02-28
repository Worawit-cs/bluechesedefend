package Entity;

public class Lion extends Monster {

    public Lion(double multiple){
        super("Monster", 100*multiple, 2f, "Coin_25", "/Assets/Monsters/Lion.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}