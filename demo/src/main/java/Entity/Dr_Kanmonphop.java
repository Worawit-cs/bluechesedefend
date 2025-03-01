package Entity;

public class Dr_Kanmonphop extends Monster {

    public Dr_Kanmonphop(double multiple){
        super("Boss", 4000*multiple, 0.5f, "Gem_10", "/Assets/Monsters/dr_Kamonphop.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
