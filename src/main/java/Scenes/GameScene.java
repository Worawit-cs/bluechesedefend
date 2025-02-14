package Scenes;

import com.example.Game;

public class GameScene {
    
    private Game game;
    public GameScene(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }
}
