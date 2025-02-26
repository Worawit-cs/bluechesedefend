package Entity;

public class Dr_Parkarn extends Monster {

    public Dr_Parkarn(double multiple){
        super("Monster", 100*multiple, 2f, "Coin_10", "/Assets/Monsters/dr_prakarn.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
