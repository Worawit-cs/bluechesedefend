package com.example;

import javax.swing.JFrame;

import Scenes.GameOver;
import Scenes.Menu;
import Scenes.Playing;
import Scenes.Settings;
import Scenes.Mode;

public class Game extends JFrame implements Runnable {

    private GameScreen gameScreen;
    private Thread gameThread; // สามาระทำให้รันหลายๆอย่าง โดยไม่ต้องติดขัดได้ โดยใช้หลักการแยก thread

    private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

    

    //Class
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Mode mode;
    private GameOver game_Over;

    public Game(){
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

		initClasses();

		add(gameScreen);
		pack();

		setVisible(true);

    }

    private void initClasses(){
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        settings = new Settings(this);
        mode = new Mode(this);
        game_Over = new GameOver(this);
        playing = new Playing(this);
    }

    private void start(){
        gameThread = new Thread(this) {};
        gameThread.start();
    }

    private void updateGame(){
        switch(GameStates.gameState){
            case EDIT:
                break;
            case GAME_OVER:
                break;
            case MENU:
                break;
            case PLAYING:
                playing.update(); 
                break;
            case SETTINGS:
                break;
            default:
                break;
            
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		long now;

		while (true) {
			now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
				frames++;
			}

			// Update
			if (now - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = now;
				updates++;
			}
			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
        }
    }

    public Menu getMenu(){ return menu; }
    public Mode getMode(){ return mode; }
    public Render getRender(){ return render; }
    public Playing getPlaying(){ return playing; }
    public Settings getSettings(){ return settings; }
    public GameOver getGameOver(){ return game_Over; }

}
