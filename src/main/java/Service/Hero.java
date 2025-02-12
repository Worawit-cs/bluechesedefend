package Service;
// package Heroes;


public class Hero {
    private int Rid; // เข้ารหัส Hero
    private int ATK; // พลังโจมตี 222999.1 121000.29
    private float SPA; // ความเร็วในการโจมตี เช่น 10.1 5 8
    private int Radius; // ระยะการโจมตี
    private String []Ingredient; // 
    private int X,Y;
    private int Tier; // 1 Mystic 2 Legend 3 Epic 4 Common
    
    public Hero(int Rid, int ATK, float SPA, int Radius, int X, int Y, String[] Ingredient, int Tier){
        this.Rid = Rid;
        this.ATK = ATK;
        this.SPA = SPA;
        this.Radius = Radius;
        this.X = X;
        this.Y = Y;
        this.Ingredient = Ingredient;
        this.Tier = Tier;
    }

    public int GetRid() { return this.Rid; }
    public int GetATK() { return this.ATK; }
    public float GetSPA() { return this.SPA; }
    public int GetRadius() { return this.Radius; }
    public String[] GetIngredient() { return this.Ingredient; }
    public int GetX() { return this.X; }
    public int GetY() { return this.Y; }
    public int GetTier() { return this.Tier; }

}