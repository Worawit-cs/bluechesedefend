package Storage;

import Scenes.Playing;

public class Player {
    private int coin, gem;
    private Playing playing;
    private double commonWeight, rareWeight, epicWeight, legendaryWeight;

    public Player(Playing playing){
        this.playing = playing;

        coin = 1000; // Starter value
        gem = 0;

        commonWeight = 80; // Starter luck weight
        rareWeight = 10;
        epicWeight = 5;
        legendaryWeight = 1.5;
    }

    public double getTotalWeight(){
        return commonWeight + rareWeight + epicWeight + legendaryWeight;
    }

    public double getWeight(String rarity){
        switch (rarity) {
            case "Common":
                return commonWeight;
        
            case "Rare":
                return rareWeight;
            
            case "Epic":
                return epicWeight;

            case "Legendary":
                return legendaryWeight;
        }

        return 0;
    }

    public void increaseWeight(String rarity, int x){
        switch (rarity) {
            case "Common":
                commonWeight += x;
                break;
        
            case "Rare":
                rareWeight += x;
                break;
            
            case "Epic":
                epicWeight += x;
                break;

            case "Legendary":
                legendaryWeight += x;
                break;
        }
    }

    public void increaseValue(String value, int x){
        switch (value) {
            case "Coin":
                coin = this.coin + x; // avoid less than 0
                break;
        
            case "Gem":
                gem = this.gem + x;
                break;
        }
    }

    public void decreaseValue(String value, int x){
        switch (value) {
            case "Coin":
                coin = Math.max(this.coin - x, 0); // avoid less than 0
                break;
        
            case "Gem":
                gem = Math.max(this.gem - x, 0);
                break;
        }
    }

    public boolean isEnough(String value, int x){
        switch (value) {
            case "Coin":
                return coin >= x;
        
            case "Gem":
                return gem >= x;
        }

        return false;
    }

    public int getCoin(){ return coin; }
    public int getGem(){ return gem; }
}
