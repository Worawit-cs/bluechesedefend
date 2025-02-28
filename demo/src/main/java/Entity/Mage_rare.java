package Entity;

public class Mage_rare extends Hero {
    
    public Mage_rare(){
        super("Mage_rare", "Rare", 15, 300, 1f, 3, "Coin_25"
        , "/Assets/Hero/Static_Movement/Mage_idle_rare.png",
        "/Assets/Hero/Dynamic_Motion/Mage_Attack_rare.png");

        super.loadAnimations(2, 6, 64);
    }

    
}
