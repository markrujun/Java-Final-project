package logic.creature.calabashBrothers;

import logic.creature.CreatureImage;


public class CyanBrother extends  CalabashBrother{
    private static CyanBrother obj;
    public CyanBrother() {
        ID = Number++;
        thisCalabashBrother = CalabashBrotherEnum.CYAN;
        maxHealth = 2000;
        attackRange = 1500;
        strength = 300;
        tempHealth = maxHealth;
        creatureImage = new CreatureImage();
        creatureImage.setImage("/select_cyan.png");
    }
    public static CyanBrother getInstance() {
        if(obj == null) {
            synchronized (CyanBrother.class) {
                if(obj == null) {
                    obj = new CyanBrother();
                }
            }
        }
        return obj;
    }

}



