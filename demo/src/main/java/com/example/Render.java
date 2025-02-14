package Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Render {
    private Game game;

    public Render (Game game){
        this.game = game;
    }

    public void render(Graphics G){
        switch (GameStates.gameState) {
            case MENU:
                game.getMenu().render(G);
                break;
            case PLAYING:
                game.getPlaying().render(G);
                break;
            case SETTINGS:
                game.getSettings().render(G);
                break;
            default:
                break;
        }
    }
}
