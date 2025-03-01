package Entity;

public class Mage_legendary extends Hero {
    
    public Mage_legendary(){
        super("Mage", "Legendary", 80, 350, 0.6f, 3, "Gem_5"
        , "/Assets/Hero/Static_Movement/Mage_idle.png",
        "/Assets/Hero/Dynamic_Motion/Mage_Attack.png");

        super.loadAnimations(2, 6, 64);
    }

    
}
