package Entity;

public class Folk extends Hero {
    public Folk(){
        super("Folk", "Common", 10, 50, 1f, 3,
        "Coin_10", "/Assets/Hero/folk_idle.png", "/Assets/Hero/folk_idle.png");

        super.loadAnimations(2, 2, 128);
    }
}
