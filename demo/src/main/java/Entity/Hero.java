package Entity;
// package Heroes;


public class Hero {
    private int ID; // เข้ารหัส Hero
    private int ATK; // พลังโจมตี 222999.1 121000.29
    private float SPA; // ความเร็วในการโจมตี เช่น 10.1 5 8
    private int Radius; // ระยะการโจมตี
    private int X,Y;
    private int Tier; // 1 Mystic 2 Legend 3 Epic 4 Common
    private int Code; // เอาไว้ดูว่ามันคือตัวไหน
    
    public static final int FOLK = 0;
	public static final int NMAKE = 1;
	public static final int ETC = 2;// มีตัวอะไรเพิ่มก้เพิ่มเข้ามาเลย
    
    public Hero(int ID,int X, int Y, int Code){
        this.ID = ID;
        this.X = X;
        this.Y = Y;
        this.Code = Code;
        switch(Code){
            case FOLK:
                this.ATK = 10;
                this.SPA = 10;
                this.Radius = 10;
                this.Tier = 1;
                break;
            case NMAKE:
                this.ATK = 10;
                this.SPA = 10;
                this.Radius = 10;
                this.Tier = 1;
                break;
        }
    }

    // Getter
    public int GetID() { return this.ID; }
    public int GetATK() { return this.ATK; }
    public float GetSPA() { return this.SPA; }
    public int GetRadius() { return this.Radius; }
    public int GetX() { return this.X; }
    public int GetY() { return this.Y; }
    public int GetTier() { return this.Tier; }
    public int GetCode() { return this.Code; }


    //

}