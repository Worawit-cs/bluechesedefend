package Service;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.example.*;

import Entity.Dr_Parkarn;
import Template.MonsterTemplate;

public class Monster extends JPanel{
    
    /*  นี่คือคลาส MonsterService ที่จะคอยคุมทุกอย่างของ monster 
      ทำให้คลาสนี้คือหนึ่งหน้าต่าง (JPannel) ที่มีแค่ object monster ทุกตัวเก็บไว้ที่นี่ที่เดียว */
    private GameWindow gameWindow;
    private ArrayList<MonsterTemplate> entity = new ArrayList<MonsterTemplate>();

    public Monster(GameWindow gameWindow){
        gameWindow.add(this);
    }

    // ใช้ลบ monster หลังพบว่าตายแล้ว
    public void remove(int index){
        entity.remove(index);
    }
    
    // spawn function ใส่ชื่อแล้วจะไปสร้างคลาสของมอนตัวนั้น (extends MonsterTemplate ทำให้เข้าถึงง่ายขึ้น) แล้วมาเพิ่มใน entity
    public void spawn(String name){
        // นำ Entity ตัวใหม่ไปใส่ใน ArrayList เพื่อใช้ใน method update
        MonsterTemplate mon = null;
        switch (name) {
            case "Dr_Parkarn":
                mon = new Dr_Parkarn();
                break;

            // make another name case for create new monster object
            default:
                break;
        }

        entity.add(mon);

        // ใช้เมื่อยามจำเป็น
        //gameWindow.revalidate();
        //gameWindow.repaint();
    }
    
    // function นี้จะถูกเรียกจาก loop หลักของเกม (Game.java)
 
    public void update(){
        // update พฤติกรรมมอนทุกตัวที่ยังมีชีวิต
         for (int i = 0; i < entity.size(); i++){

            // หากตรวจแล้วพบว่าตายแล้ว ก็ลบออก แล้ว continue ไปรันตัวต่อไป
            if (!entity.get(i).isAlive()){ remove(i); continue;}
            entity.get(i).move();
        }
        repaint();
    }

    // function นี้จะถูกเรียกเรื่อยๆ เหมือนเป็นลูปของ JPanel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // สั่งให้วาดรูปมอนทุกตัวโดยใช้ graphics (เป็นเหมือนกระดาษ) ของ JPanel นี้
        for (int i = 0; i < entity.size(); i++){
            entity.get(i).draw(g);
        }
    }
}
