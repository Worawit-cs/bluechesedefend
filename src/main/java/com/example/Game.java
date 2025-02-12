package com.example;

import Entity.Dog;
import Service.Monster;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private Thread gameThread;
    private Monster MonsterService;
    private final int FPS_SET = 120;

    public Game(){
        gameWindow = new GameWindow(); // create frame and add squre into
        MonsterService = new Monster(gameWindow);
        startGameLoop();
        
        MonsterService.spawn(new Dog());
        //MouseInputs mouse = new MouseInputs(gamepanel); // add mouse event for that squre
        //KeyBoardInputs keyboard = new KeyBoardInputs(gamepanel); // add kb event for that squre
    }
    private void startGameLoop(){
        gameThread = new Thread(this); //(this) mean Runable target
        gameThread.start(); // Start the Gmae Loop
    }

    // GameLoop(Override Method from Runable)
    @Override
    public void run() {
        double timePerFrame = 1000000000.0/FPS_SET; // Im set time in Nano time
        long lastFrame = System.nanoTime();
        long now = System.nanoTime(),lastCheck =System.currentTimeMillis();
        int frames = 0;
        // Loop ตัวนี้ทำไดเละเอียดกว่าการloopเพียงแค่milli sec
        while(true){
            now = System.nanoTime(); // Recently Time
            if(System.nanoTime()-lastFrame >= timePerFrame){
                // gamepanel.repaint(); // Loop Panel infinite (GameLoop) มันจะสร้างตัวpanelใหม่ไปเรื่อยๆ
                lastFrame = now;
                frames++;
            }
            // check การ แสดงผลของFrame
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
           
            // delay loop
            try {
                Thread.sleep(10); // Delay for 1 second (1000 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            MonsterService.update();
        }
    }
}