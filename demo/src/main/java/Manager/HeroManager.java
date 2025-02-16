package Manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.example.GameScreen;

import Entity.Hero;
import Scenes.Playing;
import Stages.Loader;

public class HeroManager {
    private Loader loader;
    private Playing playing;
    private int heroamount = 0;

    private int row = 3;
    private int column = 6;
    private Hero[][] heros = new Hero[row][column];
    private Rectangle[][] boundTable = new Rectangle[row][column];
    private int boundWidth = 78; // ความกว้างของแต่ละ cell
    private int boundHeight = 120; // ความสูงของแต่ละ cell
    private int xStart = 182, yStart = 176; // cell แรกจะเริ่มที่
    private int xEnd = (xStart*column), yEnd = (yStart*row); // cell สุดท้ายจบที่

    public HeroManager(Playing playing){
        this.playing = playing;
        
        initTable();
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
        return (x >= xStart && x <= xEnd + boundWidth) && (y >= yStart && y <= yEnd + boundHeight);
    }

    // เช็คว่าอยู่ในพื้นที่ของ แถวและคอลัมไหน
    public int[] boundContains(int x, int y){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                if (boundTable[i][j].contains(x, y)){
                    if (heros[i][j] == null){break;}
                    
                    return new int[]{i,j};
                };
            }
        }
        return null;
    }

    // แลกเปลี่ยน cell
    public void changeCell(int[] heroCell, int[] targetCell){
        Hero draggingCell = heros[heroCell[0]][heroCell[1]];
        Hero tgCell = heros[targetCell[0]][targetCell[1]];

        // ถ้า cell ที่อยากเปลี่ยนมีฮีโร่อยู่ ให้ทำการแลกกัน
        if (tgCell != null){
            heros[targetCell[0]][targetCell[1]] = draggingCell;
            heros[heroCell[0]][heroCell[1]] = tgCell;
        } else {
            // ถ้า cell ที่อยากเปลี่ยนไม่มีฮีโร่อยู่ ก็แค่เปลี่ยนแล้วลบอันเก่าออก
            heros[targetCell[0]][targetCell[1]] = draggingCell;
            heros[heroCell[0]][heroCell[1]] = null;
        }
    }
    
    // ถูกเรียกใน Playing class
    public void update(){
        
    }

    // ถูกเรียกใน Playing class
    public void draw(Graphics G, GameScreen screen){
       for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                drawTable(G, i, j);
                if (heros[i][j] == null) {continue;}


            }
       }
    }

    // ใช้วาดรูปของตาราง เพื่อแสดงขอบเขตตารางให้ชัดเจน
    public void drawTable(Graphics G, int i, int j){
        
        //border
        G.setColor(Color.gray);
        G.drawRect(xStart + (boundWidth * j),yStart + (boundHeight * i), boundWidth, boundHeight);
    }
}