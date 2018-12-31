package logic.creature.calabashBrothers;

import logic.creature.CreatureImage;

public class OrangeBrother extends  CalabashBrother {
    private static OrangeBrother obj;
    public OrangeBrother() {
        ID = Number++;
        thisCalabashBrother = CalabashBrotherEnum.ORANGE;
        maxHealth = 2000;
        attackRange = 1500;
        strength = 300;
        tempHealth = maxHealth;
        creatureImage = new CreatureImage();
        creatureImage.setImage("/select_orange.png");
    }
    public static OrangeBrother getInstance() {
        if(obj == null) {
            synchronized (OrangeBrother.class) {
                if(obj == null) {
                    obj = new OrangeBrother();
                }
            }
        }
        return obj;
    }

}


