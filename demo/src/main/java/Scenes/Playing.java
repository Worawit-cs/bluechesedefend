package Scenes;

import static com.example.GameStates.MENU;
import static com.example.GameStates.SetGameState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.example.Game;
import com.example.GameScreen;

import Entity.Hero;
import Manager.HeroManager;
import Manager.MonsterManager;
import Stages.Loader;
import ui.Action;

public class Playing extends GameScene implements SceneMethods{

    private Loader loader;
    private BufferedImage Img;  
    private MonsterManager monstermanager;
    private Action action;

    private int[] draggingCell = null;
    private HeroManager heromanager;
    private long tick = 0;

    public Playing(Game game) {
        super(game);
        loader = new Loader();
        Img = loader.loadMap("/Assets/BG.png");

        heromanager = new HeroManager(this);
        monstermanager = new MonsterManager(this);
        action = new Action(this);
        //TODO Auto-generated constructor stub
    }

    // ถูกเรียกใน loop ของ Game class
    public void update(){
        monstermanager.update();
        heromanager.update();

        // Test (Game over)
        if (System.currentTimeMillis() - tick >= 500){
            if (monstermanager.getAmount() < 100){
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
    }

    @Override
    public void mouseClicked(int x, int y) {
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (heromanager.contains(x, y)){
            draggingCell = heromanager.boundContains(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (heromanager.contains(x, y) && draggingCell != null){
            System.out.println(draggingCell[0] + " " + draggingCell[1]);
            heromanager.changeCell(draggingCell, new int[]{x,y});
        }
        
        draggingCell = null;
    }

    @Override
    public void mousePressed(int x, int y) {
    }
    
    public Action getAction(){
        return action;
    }
}
