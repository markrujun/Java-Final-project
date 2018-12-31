package logic.creature.calabashBrothers;

import logic.creature.CreatureImage;

public class PurpleBrother extends  CalabashBrother{
    private static PurpleBrother obj;
    public PurpleBrother() {
        ID = Number++;
        thisCalabashBrother = CalabashBrotherEnum.PURPLE;
        maxHealth = 2000;
        attackRange = 1500;
        strength = 300;
        tempHealth = maxHealth;
        creatureImage = new CreatureImage();
        creatureImage.setImage("/select_purple.png");
    }
    public static PurpleBrother getInstance() {
        if(obj == null) {
            synchronized (PurpleBrother.class) {
                if(obj == null) {
                    obj = new PurpleBrother();
                }
            }
        }
        return obj;
    }

}



