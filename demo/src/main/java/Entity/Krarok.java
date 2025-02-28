package Entity;

public class Krarok extends Monster {

    public Krarok(double multiple){
        super("Monster", 100*multiple, 2f, "Coin_25", "/Assets/Monsters/Krarok.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
