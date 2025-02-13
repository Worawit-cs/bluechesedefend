package com.example;

import Entity.Dr_Parkarn;
import Service.Monster;
import Template.MonsterTemplate;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private Thread gameThread;
    private MonsterTemplate monsterPanel = new Dr_Parkarn(); // เราจะทำการเอาpanelของวแต่ละคลาสมาLoopที่นี่เพื่อจะได้ไม่ต้องใช้repaintบ่อยๆ
    private Monster MonsterService;
    private final int FPS_SET = 120;

    public Game(){
        gameWindow = new GameWindow(); // create frame and add squre into
        MonsterService = new Monster(gameWindow);
        startGameLoop();
        
        MonsterService.spawn(monsterPanel);
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
            MonsterService.update(deltaTime);
            
            monsterPanel.getPanel().repaint(); // gamepanelของobj monsterเราก็ไม่ต้องไปrepaint ในคลาสแล้ว
            lastFrame = now;
            frames++;
        }
        
        if (System.currentTimeMillis() - lastCheck >= 1000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
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