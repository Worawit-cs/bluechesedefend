package Entity;

public class Mage extends Hero {
    
    public Mage(String tier,String name_idle,String name_attack){
        super("Mage", tier, 15, 300, 1f, 3, "Coin_25", name_idle,name_attack);
        System.out.println("Debuggung: "+name_idle);
        System.out.println("Debuggung: "+name_attack);

        super.loadAnimations(2, 6, 64);
    }

    
}