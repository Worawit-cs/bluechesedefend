package com.example;

import Service.Monster;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private Thread gameThread;
    private Monster monsterService;
    private final int FPS_SET = 120;

    /*  การทำงานของ monsterservice
    1. สร้าง monsterService (Panel) แค่ครั้งเดียวตลอดทั้งเกม (line 31)
    2. add Panel monsterService ใส่ใน gameWindow
    3. gameLoop (ลูปหลัก) จะคอยอัพเดท MonsterService (การเดิน และเช็คว่าตายยัง)
    4. หากจะเพิ่มมอนใหม่ใช้ monsterService เดิม .spawn(string)

       การทำงาน ของ มอนแต่ละตัว
    1. หลักจากถูกสร้างจาก .spawn ใน class Monster.java แล้ว object ของมอนจะถูกเก็บไว้ใน Arraylist<MonsterTemplate>
    [MonsterTemplate มีไว้ครอบมอนทุกตัว เพื่อให้ง่ายต่อการจัดการและเข้าถึง]
    2. ArrayList นั้น จะถูกเรียกใช้จากลูปหลัก และลูป monsterservice (paintcomponent method)
    2.1 ลูปหลักจะคอยขยับตำแหน่งของทุกๆ ตัว (ใช้ method move ของ Monstemp) และ เช็คว่าตายยัง ถ้าตายจะลบ object ตัวนั้นออกจาก ArrayList
         ^  ทุกตัว repaint พร้อมกันทั้งหมดทีเดียวในลูปนี้ เพื่อ performance
    2.2 ลูป mon จะคอยวาดรูป เล่น animation (ใช้ method draw ของ MonsterTemp)

    ตอนนี้ไม่แน่ใจว่าจะใช้ GamePanel ทำอะไรเพราะ monsterservice ก็ extends JPanel โดยตรงได้เลย และผมคิดว่า method ก่อนหน้าที่เขียนไว้
    มันไม่จำเป็น หรือไม่ควรจะอยู่ในนั้น (เช่น พวก animation ย้ายไปใน Monstemp) ก็เลยลบไปบ้าง ย้ายไปที่อื่นบ้าง
    */

    public Game(){
        gameWindow = new GameWindow(); // create Pannel into it.
        monsterService = new Monster(gameWindow);
        monsterService.spawn("Dr_Parkarn");
        
        startGameLoop();
        
        //MouseInputs mouse = new MouseInputs(gamepanel); // add mouse event for that squre
        //KeyBoardInputs keyboard = new KeyBoardInputs(gamepanel); // add kb event for that squre
    }
    private void startGameLoop(){
        gameThread = new Thread(this); //(this) mean Runable target
        gameThread.start(); // Start the Game Loop
    }

    // GameLoop(Override Method from Runable)
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET; // เวลา per frame ในหน่วย nano
        long lastFrame = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        int frames = 0;

        while (true) {
            long now = System.nanoTime();
            double deltaTime = (now - lastFrame) / 1e9; // เปลี่ยนเป็นวินาที
            
            if (now - lastFrame >= timePerFrame) {
                // ส่งค่า deltaTime ไปให้ update() เพื่อปรับความเร็วการเคลื่อนที่
                monsterService.update();
                // repaint เกิดเมื่อ monster ขยับใน update.
                
                lastFrame = now;
                frames++;
            }
            
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                monsterService.spawn("Dr_Parkarn");
                frames = 0;
            }
            
            // Optional: ให้ thread หยุดสักเล็กน้อยเพื่อประหยัด CPU
            // try {
            //     Thread.sleep(1);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            //     }
            // }
        }
    }
}