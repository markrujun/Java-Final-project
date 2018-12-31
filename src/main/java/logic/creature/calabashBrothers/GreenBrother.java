package logic.creature.calabashBrothers;

import logic.creature.CreatureImage;

public class GreenBrother extends  CalabashBrother {
    private static GreenBrother obj;
    public GreenBrother() {
        ID = Number++;
        thisCalabashBrother = CalabashBrotherEnum.GREEN;
        maxHealth = 2000;
        attackRange = 1500;
        strength = 300;
        tempHealth = maxHealth;
        creatureImage = new CreatureImage();
        creatureImage.setImage("/select_green.png");
    }
    public static GreenBrother getInstance() {
        if(obj == null) {
            synchronized (GreenBrother.class) {
                if(obj == null) {
                    obj = new GreenBrother();
                }
            }
        }
        return obj;
    }

}



