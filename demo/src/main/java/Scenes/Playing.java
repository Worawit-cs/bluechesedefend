package Scenes;

import static com.example.GameStates.MENU;
import static com.example.GameStates.SetGameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.example.Game;
import com.example.GameScreen;

import Entity.Hero;
import Manager.HeroManager;
import Manager.MonsterManager;
import Stages.Loader;
import Storage.Player;
import Storage.RandomHero;
import ui.Action;

public class Playing extends GameScene implements SceneMethods{

    public boolean dragging = false;
    private Loader loader;
    private BufferedImage Img;
    private Player player;
    private RandomHero randomHero;

    private MonsterManager monstermanager;
    private int maxMons = 100;

    private Action action;

    private int[] draggingCell = null;
    private HeroManager heromanager;
    private long tick = 0;

    public Playing(Game game) {
        super(game);
        loader = new Loader();
        Img = loader.loadMap("/Assets/BG.png");

        player = new Player(this);
        heromanager = new HeroManager(this);
        monstermanager = new MonsterManager(this);
        randomHero = new RandomHero(this);
        action = new Action(this);
    }

    // ถูกเรียกใน loop ของ Game class
    public void update(){
        monstermanager.update();
        heromanager.update();

        // Test (Game over)
        if (System.currentTimeMillis() - tick >= 1000){
            if (monstermanager.getAmount() < maxMons){
                // Test Spawn hero
                // heromanager.spawn("New");
                monstermanager.spawn("Dr_Parkarn");
                
                tick = System.currentTimeMillis();
            } else {
                // เพิ่ม clear ข้อมูลออก
                SetGameState(MENU);
            }
        }
    }


    // ถูกเรียกใน Render class
    @Override
    public void render(Graphics G, GameScreen screen){
        G.drawImage(Img, 0, 0, screen.getWidth(), screen.getHeight(), screen);
        monstermanager.draw(G, screen);
        heromanager.draw(G, screen);
        action.draw(G, screen);
        randomHero.draw(G, screen);
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
    
    public int getmaxMons(){ return maxMons; } 

    public RandomHero getRandomHero(){ return randomHero; }
    public Player getPlayer(){ return player; }

    public Action getAction(){
        return action;
    }

    public HeroManager getHeroManager(){
        return heromanager;
    }

    public MonsterManager getMonsterManager(){
        return monstermanager;
    }
}
