package Entity;

public class New extends Hero {

    public New(){
        super("New", "Common", 10, 400, 2.5f, 3,
        "Coin_15", "/Assets/Hero/New_idle.png", "/Assets/Hero/New_idle.png");

        super.loadAnimations(2, 2, 128);
    }
}
