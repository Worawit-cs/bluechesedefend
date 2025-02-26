package Entity;

public class Mage extends Hero {
    
    public Mage(){
        super("Mage", "Rare", 15, 300, 1f, 3, "Coin_25", 
        "/Assets/Hero/Static_Movement/Mage_idle.png",
        "/Assets/Hero/Dynamic_Motion/Walk_Motion/Mage_Attack.png");

        super.loadAnimations(2, 6, 64);
    }

    public void upgrade(){
        super.getInfo().upgradeATK();
    }
}