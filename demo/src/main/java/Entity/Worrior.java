package Entity;

public class Worrior extends Hero {
    public Worrior(String tier,String name_idle,String name_attack){
        super("Worrior",tier, 10, 200, 1f, 3,"Coin_10", name_idle, name_attack);
        System.out.println("Debuggung: "+name_idle);
        System.out.println("Debuggung: "+name_attack);

        super.loadAnimations(2, 4, 64);
    }
}
