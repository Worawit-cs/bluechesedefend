package Storage;

import Scenes.Playing;

public class Player {
    private int coin, gem, wave;
    private Playing playing;
    private double commonWeight, rareWeight, epicWeight, legendaryWeight;
    private int UpgradeNormal = 1, UpgradeEpic = 1,UpgradeLegendary = 1;

    public Player(Playing playing){
        this.playing = playing;

        coin = 1000; // Starter value
        gem = 0;

        commonWeight = 80; // Starter luck weight
        rareWeight = 10;
        epicWeight = 5;
        legendaryWeight = 1.5;
    }

    public int getUpgrade(String Tier){
        switch (Tier) {
            case "Epic":
                return UpgradeEpic;
            
            case "Legendary":
                return UpgradeLegendary;

            default:
                return UpgradeNormal;
        }

    }

    public void UpgradeHero(String Tier){
        switch (Tier) {
            case "Common":
                UpgradeNormal++;
                decreaseValue("Coin", UpgradeNormal * 50);
                break;

            case "Epic":
                UpgradeEpic++;
                decreaseValue("Coin", UpgradeEpic * 50);
                break;
            
            case "Legendary":
                UpgradeLegendary++;
                decreaseValue("Coin", UpgradeLegendary * 50);
                break;
        }
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
