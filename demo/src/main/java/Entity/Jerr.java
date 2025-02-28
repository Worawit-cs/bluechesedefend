package Entity;

public class Jerr extends Monster {

    public Jerr(double multiple){
        super("Monster", 100*multiple, 2f, "Coin_25", "/Assets/Monsters/Jerr.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
