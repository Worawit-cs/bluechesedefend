package Entity;

public class Warrior_epic extends Hero {
    public Warrior_epic(){
        super("Warrior","Epic", 10, 200, 1f, 3,"Gem_1",
         "/Assets/Hero/Static_Movement/Warrior_idle_epic.png", 
         "/Assets/Hero/Dynamic_Motion/Warrior_Attack_epic.png");

        super.loadAnimations(2, 4, 64);
    }
}