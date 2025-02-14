package Scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.example.Game;
import static com.example.GameStates.*;
import ui.Button;

public class Menu extends GameScene implements SceneMethods{

    private BufferedImage Img;
    private ArrayList<BufferedImage> Assets = new ArrayList<>(); //เก็บไฟล์รูปภาพหลายๆอันได้

    private Button PlayButton, SettingButton, QuitButton;
    public Menu(Game game) {
        super(game);
        importImg("/Assets/BG/Map_demo.png");
        initButtons();
    }

    public void initButtons(){
        PlayButton = new Button("Play",250,150,150,50);
        SettingButton = new Button("Setting",250,250,150,50);
        QuitButton = new Button("Quit", 250 , 350 , 150 ,50);
    }   

    @Override
    public void render(Graphics G){
        // G.drawImage(Img ,0 ,0 ,null);
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
            SetGameState(PLAYING);
            importImg("/src/Assets/BG/Map_demo.png");
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
    
}
