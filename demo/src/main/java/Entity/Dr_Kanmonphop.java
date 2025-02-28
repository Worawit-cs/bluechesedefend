package Entity;

public class Dr_Kanmonphop extends Monster {

    public Dr_Kanmonphop(double multiple){
        super("Boss", 100*multiple, 2f, "Gem_2", "/Assets/Monsters/dr_Kamonphop.png");
    }

    //@Override   -- modify for each Entity (attack method or other).
    //public void move(){}
}
