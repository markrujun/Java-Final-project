package logic.creature.calabashBrothers;

import logic.creature.CreatureImage;

public class BlueBrother extends  CalabashBrother {
    private static BlueBrother obj;
    public BlueBrother() {
        ID = Number++;
        thisCalabashBrother = CalabashBrotherEnum.BLUE;
        maxHealth = 2000;
        attackRange = 1500;
        strength = 300;
        tempHealth = maxHealth;
        creatureImage = new CreatureImage();
        creatureImage.setImage("/select_blue.png");
    }
    public static BlueBrother getInstance() {
        if(obj == null) {
            synchronized (BlueBrother.class) {
                if(obj == null) {
                    obj = new BlueBrother();
                }
            }
        }
        return obj;
    }

}

