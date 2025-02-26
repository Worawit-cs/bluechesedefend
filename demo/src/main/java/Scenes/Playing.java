package Scenes;

import static com.example.GameStates.GAME_OVER;
import static com.example.GameStates.SetGameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.example.Game;
import com.example.GameScreen;

import Manager.HeroManager;
import Manager.MonsterManager;
import Stages.Loader;
import Stages.Wave;
import Storage.Player;
import Storage.RandomHero;
import ui.Action;

public class Playing extends GameScene implements SceneMethods{

    public boolean dragging = false;
    private boolean win = false;
    
    private String Mode;
    private Loader loader;
    private BufferedImage Img;
    private Player player;
    private RandomHero randomHero;

    private Wave wave;
    private MonsterManager monstermanager;

    private Action action;

    private int[] draggingCell = null;
    private HeroManager heromanager;

    public Playing(Game game) {
        super(game);
        loader = new Loader();
        Img = loader.loadMap("/Assets/BG.png");
    }

    public void start(String Mode){
        MonsterManager.resetMonsters();

        this.Mode = Mode;
        this.dragging = false;
        this.win = false;
        this.draggingCell = null;

        player = new Player(this);
        heromanager = new HeroManager(this);
        monstermanager = new MonsterManager(this);
        randomHero = new RandomHero(this);
        action = new Action(this);

        wave = new Wave(this);
    }

    public void end(String Stage){
        if (Stage == "Victory"){ win = true; }
        SetGameState(GAME_OVER);
    }

    // ถูกเรียกใน loop ของ Game class
    public void update(){
        monstermanager.update();
        heromanager.update();
        wave.update();
    }


    // ถูกเรียกใน Render class
    @Override
    public void render(Graphics G, GameScreen screen){
        G.drawImage(Img, 0, 0, screen.getWidth(), screen.getHeight(), screen);
        monstermanager.draw(G, screen);
        heromanager.draw(G, screen);
        action.draw(G, screen);
        randomHero.draw(G, screen);
        wave.draw(G, screen);
    }

    @Override
    public void mouseClicked(int x, int y) {
        action.mouseClicked(x, y);
    }

    @Override
    public void mouseMoved(int x, int y) {
        action.mouseMoved(x, y);
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (heromanager.contains(x, y) && draggingCell != null){
            System.out.println(draggingCell[0] + " " + draggingCell[1]);
            heromanager.changeCell(draggingCell, new int[]{x,y});
        }
       
        dragging = false;
        draggingCell = null;
    }

    @Override
    public void mousePressed(int x, int y) {
        if (heromanager.contains(x, y)){
            int[] Cell = heromanager.boundContains(x, y);
            if (!dragging){action.clearStage(); return;} // ถ้า cell นั้นไม่มีฮีโร่ให้รีเทนกลับ ไม่ทำอะไร

            switch (action.getStage()) {
                case "Normal":
                    draggingCell = Cell;
                    break;
            
                case "Bin":
                    dragging = false; // ปิดการแสดงตาราง
                    heromanager.remove(Cell);
                    break;

                case "Upgrade":
                    dragging = false;
                    heromanager.upgrade(Cell);
                    break;
            }
        }
    }

    public void keyPressed(KeyEvent e){
        action.keyPressed(e);
    }
    
    public Wave getWave(){ return wave; }
    public boolean isWin(){ return win; }
    public String getMode(){ return Mode; }
    
    public Player getPlayer(){ return player; }
    public Action getAction(){ return action; }
    public RandomHero getRandomHero(){ return randomHero; }
    public HeroManager getHeroManager(){ return heromanager; }
    public MonsterManager getMonsterManager(){ return monstermanager; }
}
