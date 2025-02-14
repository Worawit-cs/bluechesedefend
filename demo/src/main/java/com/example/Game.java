package Main;

import javax.swing.JFrame;

import Scenes.Menu;
import Scenes.Playing;
import Scenes.Settings;
import inputs.Keyboard;
import inputs.Mouse;

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

    public Game(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		initClasses();

		add(gameScreen);
		pack();

		setVisible(true);

    }

    private void initClasses(){
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
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

    public Render getRender(){ return render; }
    public Menu getMenu(){ return menu; }
    public Playing getPlaying(){ return playing; }
    public Settings getSettings(){ return settings; }
}
