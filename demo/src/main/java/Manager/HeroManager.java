package Manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.example.GameScreen;

import Entity.Folk;
import Entity.Hero;
import Entity.Mage;
import Entity.New;
import Scenes.Playing;

/* how to add new hero to the game 
 * 1. make new class and make constructer like class Folk New Mage
 * 2. make new code codition in spawn Function in this script (line 52)
 * 3. add hero name in RandomHero Script at any array of hero rate (line 34)
 * 4. finish
*/

public class HeroManager {

    private Playing playing;
    private long tick = 0;

    private int MaxHero = 20;
    private int amountHero = 0;

    private int row = 3;
    private int column = 6;
    private Hero[][] heros = new Hero[row][column];

    private Rectangle[][] boundTable = new Rectangle[row][column];
    private int boundWidth = 78; // ความกว้างของแต่ละ cell
    private int boundHeight = 120; // ความสูงของแต่ละ cell
    private int xStart = 182, yStart = 176; // cell แรกจะเริ่มที่
    private int xEnd = (xStart + (boundWidth*column)), yEnd = (yStart + (boundHeight*row)); // cell สุดท้ายจบที่

    public HeroManager(Playing playing){
        this.playing = playing;
        
        initTable();
    }

    
    public void spawn(String Name){
        if (amountHero >= MaxHero){return;}
        
        amountHero++;
        Hero h = null;
        switch (Name) {
            case "New":
            h = new New(); 
            break;
            
            case "Folk":
            h = new Folk();
            break;

            case "Mage":
            h = new Mage();
            break;

            default:
                System.out.println("Not Found in HeroManager");
            break;
        }
        
        // หาที่ให้ hero อยู่
        findPos(h);
    }
    
    
    private void initTable(){
        // สร้างพื้นที่ของตาราง แต่ละ row column
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                boundTable[i][j] = new Rectangle(xStart + (boundWidth * j),yStart + (boundHeight * i), boundWidth, boundHeight);
            }
        }
    }
    
    // เช็คว่าอยู่ในพื้นที่เวทีของฮีโร่ไหม
    public boolean contains(int x, int y){
        return (x >= xStart && x <= xEnd) && (y >= yStart && y <= yEnd);
    }
    
    private boolean isSameHero(Hero hero, Hero compareHero){
        return compareHero.getName() == hero.getName() && compareHero.getTier() == hero.getTier();
    }
    
    // ย้ายไปที่ cell นั้น
    public void goCell(Hero hero, Rectangle bound){
        hero.getPos().Set((float)bound.getX(), (float)bound.getY()+32);
    }
    
    // เช็คว่าอยู่ในพื้นที่ของ แถวและคอลัมไหน
    public int[] boundContains(int x, int y){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (boundTable[i][j].contains(x, y)){
                    // ถ้าช่องนั้นมีฮีโร่อยู่ ให้แสดงตาราง (เปิด dragging เป็น true) และแสดงระยะของฮีโร่ ณ ช่องนี้
                    if (heros[i][j] != null){playing.dragging = true; heros[i][j].turnRadius(true);}
                    
                    return new int[]{i,j};
                };
            }
        }
        return null; // impossible to be null
    }

    // แลกเปลี่ยน cell
    public void changeCell(int[] heroCell, int[] targetCell){
        Hero draggingHero = heros[heroCell[0]][heroCell[1]];
        if (draggingHero == null){return;} // ช่องที่ลากมาจะต้องมีฮีโร่อยู่

        targetCell = boundContains(targetCell[0], targetCell[1]);
        Hero tgHero = heros[targetCell[0]][targetCell[1]];

        // ปิดการแสดงระยะ
        draggingHero.turnRadius(false);

        Rectangle dragBoundCell = boundTable[heroCell[0]][heroCell[1]];
        Rectangle tgBoundCell = boundTable[targetCell[0]][targetCell[1]];
        // สลับ cell ใน Array
        heros[targetCell[0]][targetCell[1]] = draggingHero;
        heros[heroCell[0]][heroCell[1]] = tgHero;

        // สลับตำแหน่งไปยัง cell นั้นๆ
        goCell(draggingHero, tgBoundCell);
        if (tgHero != null){ goCell(tgHero, dragBoundCell); tgHero.turnRadius(false); }
    }

    private void findPos(Hero hero){

        // หาว่ามีกลุ่มที่ยังว่างของฮีโร่ตัวนี้อยู่แล้วหรือไม่ ถ้ามีก็เพิ่มจำนวนฮีโร่ในกลุ่มนั้นแล้วคืนค่า pos ของ cell นั้น
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (heros[i][j] == null || (!isSameHero(hero, heros[i][j]) || heros[i][j].getAmount() >= heros[i][j].getMaxPerGroup()))
                {continue;}
                
                heros[i][j].increaseAmount();
                return;
            }
        }

        // ถ้าไม่ถูกคืนค่า แสดงว่าไม่มีกลุ่มว่าง หรือไม่มีกลุ่มอยู่แล้วก็ให้มาหาว่ายังมี cell ไหนที่ว่างอยู่บ้าง
        for (int j = 0; j < column; j++){
            for (int i = 0; i < row; i++){
                if (heros[i][j] != null){continue;}

                heros[i][j] = hero;
                hero.setPosition((float)boundTable[i][j].getX(), (float)boundTable[i][j].getY()+32);
                return;
            }
        }
    }
    
    // remove
    public void remove(int[] Cell){
        Hero hero = heros[Cell[0]][Cell[1]];
        if (hero == null){return;}

        String[] reward = hero.getReward();
        int amountLeft = hero.decreaseAmount(); // จำนวนฮีโร่ในกองนั้น
        amountHero--; // ลดจำนวนฮีโร่ทั้งหมดบนเวที
        // reward here

        // ถ้าลดจำนวนฮีโร่ในกองแล้วไม่เหลือตัวละคร ให้ลบฮีโร่ในช่องนั้นทิ้ง (ทำเป็น null)
        if (amountLeft == 0){
            heros[Cell[0]][Cell[1]] = null;
        }

        playing.getPlayer().increaseValue(reward[0], Integer.parseInt(reward[1]));
        hero.turnRadius(false);
    }

    // upgrade (promote)
    public void upgrade(int[] Cell){
        Hero hero = heros[Cell[0]][Cell[1]];
        if (hero != null && hero.getAmount() >= 3 && hero.getTier() != "Legendary"){

            amountHero -= hero.getAmount();
            heros[Cell[0]][Cell[1]] = null;
            playing.getRandomHero().upgrade(hero.getTier());
        }
        hero.turnRadius(false);
    }

    // ถูกเรียกใน Playing class
    public void update(){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (heros[i][j] == null){continue;}

                heros[i][j].attack();
            }
        }

        // force change target every 2s.
        if (System.currentTimeMillis() - tick >= 2000){
            tick = System.currentTimeMillis();

            for (int i = 0; i < row; i++){
                for (int j = 0; j < column; j++){
                    if (heros[i][j] == null){continue;}
    
                    heros[i][j].findTarget();
                }
            }
        }
    }

    // ถูกเรียกใน Playing class
    public void draw(Graphics G, GameScreen screen){
       for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                drawTable(G, i, j);
                if (heros[i][j] == null) {continue;}
                heros[i][j].draw(G);

            }
       }
    }

    // ใช้วาดรูปของตาราง เพื่อแสดงขอบเขตตารางให้ชัดเจน
    public void drawTable(Graphics G, int i, int j){

        //border
        if (playing.dragging){
            G.setColor(Color.gray);
            G.drawRect(xStart + (boundWidth * j),yStart + (boundHeight * i), boundWidth, boundHeight);
        }
    }

    public boolean isHeroFull(){
        return amountHero == MaxHero;
    }
}