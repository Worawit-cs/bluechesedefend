package Entity;

public class Toi extends Monster {

    public Toi(double multiple){
        super("Monster", 100*multiple, 2f, "Coin_25", "/Assets/Monsters/Toi.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
