package Scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.example.Game;
import com.example.GameScreen;

import static com.example.GameStates.*;
import ui.Button;

public class Menu extends GameScene implements SceneMethods{

    private BufferedImage Img;
    private ArrayList<BufferedImage> Assets = new ArrayList<>(); //เก็บไฟล์รูปภาพหลายๆอันได้

    private Button PlayButton, SettingButton, QuitButton;
    public Menu(Game game) {
        super(game);
        importImg("/Assets/BG.png");
        initButtons();
    }

    public void initButtons(){
        int w = 200;
        int h = 75;
        PlayButton = new Button("Play",640 - (int)w/2,210 - (int)h/2,w,h);
        SettingButton = new Button("Setting",640 - (int)w/2,360 - (int)h/2,w,h);
        QuitButton = new Button("Quit", 640 - (int)w/2,510 - (int)h/2 , w ,h);

    }   

    @Override
    public void render(Graphics G, GameScreen screen){
        //G.drawImage(Img ,0 ,0 ,null); // background for menu
        drawButtons(G);
    }

    private void drawButtons(Graphics G){
        PlayButton.draw(G);
        SettingButton.draw(G);
        QuitButton.draw(G);
    }


    private void importImg(String path){

        InputStream is = getClass().getResourceAsStream(path);

        try {
            // โหลดภาพจากไฟล์
            Img = ImageIO.read(is); 
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Cannot load map image.");
        }
    
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(PlayButton.getBounds().contains(x,y)){
            SetGameState(MODE);
        }
        else if(SettingButton.getBounds().contains(x,y)){
            SetGameState(SETTINGS);
        }
        else if(QuitButton.getBounds().contains(x,y)){
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        PlayButton.setMouseOver(false);
        SettingButton.setMouseOver(false);
        QuitButton.setMouseOver(false);
        if(PlayButton.getBounds().contains(x,y)){
            PlayButton.setMouseOver(true);
        }
        else if(SettingButton.getBounds().contains(x,y)){
            SettingButton.setMouseOver(true);
        }
        else if(QuitButton.getBounds().contains(x,y)){
            QuitButton.setMouseOver(true);
        }

    }
    
    @Override
    public void mouseReleased(int x, int y) {
    }

    @Override
    public void mousePressed(int x, int y) {
    }
}
