package Entity;

public class Warrior_rare extends Hero {
    public Warrior_rare(){
        super("Warrior","Rare", 10, 200, 1f, 3,"Coin_10",
         "/Assets/Hero/Static_Movement/Warrior_idle_rare.png", 
         "/Assets/Hero/Dynamic_Motion/Warrior_Attack_rare.png");

        super.loadAnimations(2, 4, 64);
    }
}
