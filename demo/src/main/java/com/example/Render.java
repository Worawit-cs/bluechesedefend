package com.example;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Render {
    private Game game;

    public Render (Game game){
        this.game = game;
    }
    
    // ถูกเรียกใช้ที่ GameScreen
    public void render(Graphics G, GameScreen screen){
        switch (GameStates.gameState) {
            case MODE:
                game.getMode().render(G, screen);
                break;
            case MENU:
                game.getMenu().render(G, screen);
                break;
            case PLAYING:
                game.getPlaying().render(G, screen);
                break;
            case SETTINGS:
                game.getSettings().render(G, screen);
                break;
            default:
                break;
        }
    }
}
