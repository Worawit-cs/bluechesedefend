package Entity;

public class Warrior_legendary extends Hero {
    public Warrior_legendary(){
        super("Warrior","Legendary", 10, 200, 1f, 3,"Gem_2",
         "/Assets/Hero/Static_Movement/Warrior_idle_legendary.png", 
         "/Assets/Hero/Dynamic_Motion/Warrior_Attack_legendary.png");

        super.loadAnimations(2, 4, 64);
    }
}