package Entity;

public class Mage_epic extends Hero {
    
    public Mage_epic(){
        super("Mage", "Epic", 15, 300, 1f, 3, "Gem_1"
        , "/Assets/Hero/Static_Movement/Mage_idle_epic.png",
        "/Assets/Hero/Dynamic_Motion/Mage_Attack_epic.png");

        super.loadAnimations(2, 6, 64);
    }

    
}
