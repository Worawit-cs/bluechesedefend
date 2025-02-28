package Entity;

public class Mage_common extends Hero {
    
    public Mage_common(){
        super("Mage", "Common", 15, 300, 1f, 3, "Coin_25"
        , "/Assets/Hero/Static_Movement/Mage_idle_common.png",
        "/Assets/Hero/Dynamic_Motion/Mage_Attack_common.png");

        super.loadAnimations(2, 6, 64);
    }
}