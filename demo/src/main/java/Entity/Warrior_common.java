package Entity;

public class Warrior_common extends Hero {
    public Warrior_common(){
        super("Warrior","Common", 10, 200, 1f, 3,"Coin_10",
         "/Assets/Hero/Static_Movement/Warrior_idle_common.png", 
         "/Assets/Hero/Dynamic_Motion/Warrior_Attack_common.png");

        super.loadAnimations(2, 4, 64);
    }
}
