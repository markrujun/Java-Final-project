package logic.creature.calabashBrothers;

import logic.creature.CreatureImage;

public class YellowBrother extends  CalabashBrother {
    private static YellowBrother obj;
    public YellowBrother() {
        ID = Number++;
        thisCalabashBrother = CalabashBrotherEnum.YELLOW;
        maxHealth = 2000;
        attackRange = 1500;
        strength = 300;
        tempHealth = maxHealth;
        creatureImage = new CreatureImage();
        creatureImage.setImage("/select_yellow.png","/yellow_left.png","/yellow_right.png","/yellow_right.png");
    }
    public static YellowBrother getInstance() {
        if(obj == null) {
            synchronized (YellowBrother.class) {
                if(obj == null) {
                    obj = new YellowBrother();
                }
            }
        }
        return obj;
    }

}


