package Storage;

import Scenes.Playing;

public class Player {
    private int coin, gem;
    private Playing playing;
    private double commonWeight, rareWeight, epicWeight, legendaryWeight;
    private int UpgradeNormal = 0, UpgradeEpic = 0, UpgradeLegendary = 0,
        normalCost = 50, epicCost = 50, legendaryCost = 50;    // starter cost;
        // increase cost rate at line 57.

    public Player(Playing playing){
        this.playing = playing;

        coin = 100; // Starter value
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

    public int getCost(String Tier){
        switch (Tier) {
            case "Epic":
                return epicCost;
            
            case "Legendary":
                return legendaryCost;

            default:
                return normalCost;
        }
    }

    public void UpgradeHero(String Tier){
        switch (Tier) {
            case "Common":
                UpgradeNormal++;
                decreaseValue("Coin", normalCost);
                normalCost = (int) Math.floor((normalCost * 1.5)); // increase cost rate
                break;

            case "Epic":
                UpgradeEpic++;
                decreaseValue("Coin", epicCost);
                epicCost = (int) Math.floor(epicCost * 1.5); // increase cost rate
                break;
            
            case "Legendary":
                UpgradeLegendary++;
                decreaseValue("Coin", legendaryCost);
                legendaryCost = (int) Math.floor(legendaryCost * 1.5); // increase cost rate
                break;
        }
    }

    public double getTotalWeight(String type){
        switch (type) {
            case "Coin":
                return commonWeight + rareWeight;
        
            case "Gem":
                return epicWeight + legendaryWeight;
        }

        return 0;
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
                coin = this.coin + x;
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
