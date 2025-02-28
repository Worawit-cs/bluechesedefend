package Storage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.example.GameScreen;

import Manager.HeroManager;
import Scenes.Playing;
import Stages.Loader;

public class RandomHero {
    private Random random = new Random();

    private String[] rarityList = new String[]{"Common", "Rare", "Epic", "Legendary"};
    private String[] CommonHero, RareHero, EpicHero, LegendaryHero;
    private HeroManager heroManager;
    private Playing playing;
    private Player player;

    // show Icon
    private boolean isShowIcon = false;
    private BufferedImage[] Icons;
    private BufferedImage img;
    private Loader loader;
    private long tick = 0;

    public RandomHero(Playing playing){
        this.playing = playing;
        this.player = playing.getPlayer();
        this.heroManager = playing.getHeroManager();

        // add new Hero here
        CommonHero = new String[]{"Mage_common", "Worrior_common"};
        RareHero = new String[]{"Mage_rare","Worrior_rare"};
        EpicHero = new String[]{"Mage_epic", "Worrior_epic"};
        LegendaryHero = new String[]{"Mage_legend", "Worrior_legend"};

        initImg();
    }

    private void initImg(){
        loader = new Loader();
        Icons = new BufferedImage[4];

        Icons[0] = loader.loadMap("/Assets/Icon/icon_common.png");
        Icons[1] = loader.loadMap("/Assets/Icon/icon_rare.png");
        Icons[2] = loader.loadMap("/Assets/Icon/icon_epic.png");
        Icons[3] = loader.loadMap("/Assets/Icon/icon_legendary.png");
    }

    public void randomAllWeight(String type){
        double rate = random.nextDouble((player.getTotalWeight(type)+1) * 100) / 100; // คูณ หาร 100 เพื่อทำให้เป็นทศนิยม
        double weight = 0;
        System.out.println(rate);

        int i, maxI;
        switch (type) {
            case "Coin":
                i = 0;
                maxI = 2;
                break;
        
            default:
            // Gem case
                i = 2;
                maxI = 4;
                break;
        }

        for (int x = i; x < maxI; x++){
            weight += player.getWeight(rarityList[x]);

            if (rate <= weight){randomHero(rarityList[x]); break;}
        }
    }

    public void randomSuccess(String rarity){
        double rate = random.nextDouble(100 * 100) / 100; // คูณ หาร 100 เพื่อทำให้เป็นทศนิยม
        double weight = player.getWeight(rarity);

        if (rate <= weight){
            randomHero(rarity);
        }
    }

    public void upgrade(String rarity){
        int promoteIndex = getRarityIndex(rarity) + 1;
        randomHero(rarityList[promoteIndex]);
    }

    public void randomHero(String rarity){
        String[] HeroList = CommonHero;
        System.out.println(rarity);
        switch (rarity) {
            case "Common":
                HeroList = CommonHero;
                break;
        
            case "Rare":
                HeroList = RareHero; // change this
                break;

            case "Epic":
                HeroList = EpicHero; // change this
                break;

            case "Legendary":
                HeroList = LegendaryHero; // change this
                break;
        }

        showIcon(rarity);
        int randomIndex = random.nextInt(HeroList.length);
        heroManager.spawn(HeroList[randomIndex]);
    }

    private int getRarityIndex(String rarity){
        int index = 0;
        for (int i = 0; i < 4; i++){
            if (rarityList[i] == rarity){index = i; break;}
        }
        return index;
    }

    public void showIcon(String rarity){
        tick = System.currentTimeMillis();
        img = Icons[getRarityIndex(rarity)];
        isShowIcon = true;
    }

    public void draw(Graphics g, GameScreen screen){
        if (!isShowIcon){ return; }
        
        g.drawImage(img, 500, 200, 128, 128, null);
        if (System.currentTimeMillis() - tick >= 1000){
            isShowIcon = false;
        }
    }
}
