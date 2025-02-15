package Scenes;

import java.awt.Graphics;

import com.example.GameScreen;

public interface SceneMethods {
    
    public void render(Graphics G, GameScreen screen);
    public void mouseClicked(int x, int y);
    public void mouseMoved(int x, int y);
}
