package Entity;

public class Warrior_rare extends Hero {
    public Warrior_rare(){
        super("Warrior","Rare", 20, 200, 0.95f, 3,"Gem_1",
         "/Assets/Hero/Static_Movement/Warrior_idle_rare.png", 
         "/Assets/Hero/Dynamic_Motion/Warrior_Attack_rare.png");

        super.loadAnimations(2, 4, 64);
    }
}
